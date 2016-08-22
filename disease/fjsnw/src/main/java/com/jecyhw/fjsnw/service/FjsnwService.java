package com.jecyhw.fjsnw.service;

import com.jecyhw.fjsnw.bean.FjsnwBean;
import com.jecyhw.fjsnw.html.FjsnwDiseaseItem;
import com.jecyhw.fjsnw.repository.FjsnwRepository;
import com.jecyhw.shared.request.MongoGridFS;
import com.jecyhw.shared.request.Request;
import com.jecyhw.shared.response.filter.Filter;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created by jecyhw on 16-8-20.
 */
@Service
public class FjsnwService {

    static final Logger logger = Logger.getLogger(FjsnwService.class);

    @Autowired
    private FjsnwRepository fjsnwRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(FjsnwBean bean) {
        fjsnwRepository.save(bean);
    }

    public void addAll(List<FjsnwBean> beans) {
        fjsnwRepository.save(beans);
    }

    public void add(FjsnwDiseaseItem item) {
        FjsnwBean bean = new FjsnwDiseaseItemFilter(item).filter();
        if (bean != null) {
            this.add(bean);
        }
    }

    private class FjsnwDiseaseItemFilter implements Filter<FjsnwBean> {

        final FjsnwDiseaseItem item;

        public FjsnwDiseaseItemFilter(FjsnwDiseaseItem item) {
            this.item = item;
        }

        @Override
        public FjsnwBean filter() {
            FjsnwBean bean = new FjsnwBean();
            try {
                Document doc = Jsoup.parse(item.html());
                Elements trs = doc.select("tr");
                bean.setDiseaseName(trs.get(0).select("td:eq(1)").first().text());
                bean.setHarmPlant(trs.get(1).select("td:eq(1)").first().text());
                bean.setDiseaseReason(trs.get(2).select("td:eq(1)").first().text());
                bean.setDiseaseDescription(trs.get(4).select("td:eq(0)").first().text());
                bean.setCureMethod(trs.get(6).select("td:eq(0)").first().text());
                bean.setReference(trs.get(8).select("td:eq(0)").first().text());

                String imageSrc = trs.get(0).select("img").first().attr("src");
                logger.info("抓取的图片地址:" + imageSrc);
                String fileName = UUID.randomUUID() + ".jpg";
                InputStream imageInputStream = Request.getResponse(imageSrc).body().byteStream();
                MongoGridFS.saveFile(mongoTemplate, imageInputStream, fileName);
                bean.setDiseaseImageName(fileName);
            } catch (Exception e) {
                logger.error(e.getMessage());
                return null;
            }
            return bean;
        }
    }
}
