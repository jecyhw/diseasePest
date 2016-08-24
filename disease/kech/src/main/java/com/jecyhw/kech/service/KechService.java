package com.jecyhw.kech.service;

import com.jecyhw.kech.bean.KechBean;
import com.jecyhw.kech.html.KechConstant;
import com.jecyhw.kech.html.KechDiseaseItem;
import com.jecyhw.kech.repository.KechRepository;
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
 * Created by jecyhw on 16-8-22.
 */
@Service
public class KechService {

    static final Logger logger = Logger.getLogger(KechService.class);

    @Autowired
    private KechRepository kechRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(KechBean bean) {
        kechRepository.save(bean);
    }

    public void addAll(List<KechBean> beans) {
        kechRepository.save(beans);
    }

    public void add(KechDiseaseItem item) {
        KechBean bean = new KechDiseaseItemFilter(item).filter();
        if (bean != null) {
            this.add(bean);
        }
    }

    private class KechDiseaseItemFilter implements Filter<KechBean> {

        final KechDiseaseItem item;

        public KechDiseaseItemFilter(KechDiseaseItem item) {
            this.item = item;
        }

        @Override
        public KechBean filter() {
            KechBean bean = new KechBean();
            try {
                Document doc = Jsoup.parse(item.html());
                Elements ps = doc.select("p");
                bean.setDiseaseName(ps.get(0).ownText());
                bean.setDiseaseSymptom(ps.get(1).ownText());
                bean.setPathogenesis(ps.get(2).ownText());
                bean.setCureMethod(ps.get(3).ownText());

                String tempImageSrc = ps.get(5).select("img").first().attr("src");
                if (!tempImageSrc.contains("no2.jpg")) {
                    String diseaseImageSrc = KechConstant.ROOT_URL + tempImageSrc;
                    logger.info("抓取的图片地址:" + diseaseImageSrc);
                    String fileName = UUID.randomUUID() + ".jpg";
                    try {
                        InputStream imageInputStream = Request.getResponse(diseaseImageSrc).body().byteStream();
                        MongoGridFS.saveFile(mongoTemplate, imageInputStream, fileName);
                        bean.setDiseaseImageName(fileName);
                    } catch (Exception e) {
                        bean.setDiseaseImageName(diseaseImageSrc);
                        logger.error(e.getMessage());
                    }
                }

                bean.setHost(ps.get(6).ownText());
                bean.setHostFamily(ps.get(7).ownText());
                bean.setClassification(ps.get(8).ownText());
                bean.setDiseaseDescription(ps.get(9).ownText());

                tempImageSrc = ps.get(11).select("img").first().attr("src");
                if (!tempImageSrc.contains("no2.jpg")) {
                    String diseasePathogenImageSrc = KechConstant.ROOT_URL + tempImageSrc;
                    logger.info("抓取的图片地址:" + diseasePathogenImageSrc);
                    String fileName = UUID.randomUUID() + ".jpg";
                    try {
                        InputStream imageInputStream = Request.getResponse(diseasePathogenImageSrc).body().byteStream();
                        MongoGridFS.saveFile(mongoTemplate, imageInputStream, fileName);
                        bean.setDiseasePathogenImageName(fileName);
                    } catch (Exception e) {
                        bean.setDiseaseImageName(diseasePathogenImageSrc);
                        logger.error(e.getMessage());
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                return null;
            }
            return bean;
        }
    }
}
