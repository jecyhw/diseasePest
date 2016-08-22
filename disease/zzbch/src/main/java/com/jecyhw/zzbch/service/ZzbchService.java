package com.jecyhw.zzbch.service;

import com.jecyhw.shared.request.MongoGridFS;
import com.jecyhw.shared.request.Request;
import com.jecyhw.shared.response.filter.Filter;
import com.jecyhw.zzbch.bean.ZzbchBean;
import com.jecyhw.zzbch.html.ZzbchConstant;
import com.jecyhw.zzbch.html.ZzbchDiseaseItem;
import com.jecyhw.zzbch.repository.ZzbchRepository;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jecyhw on 16-8-20.
 */
@Service
public class ZzbchService {

    static final Logger logger = Logger.getLogger(ZzbchService.class);

    static final Pattern imageUrlPattern = Pattern.compile("UploadFiles[^']*\\.jpg");

    @Autowired
    private ZzbchRepository zzbchRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(ZzbchBean bean) {
        zzbchRepository.save(bean);
    }

    public void addAll(List<ZzbchBean> beans) {
        zzbchRepository.save(beans);
    }

    public void add(ZzbchDiseaseItem item) {
        ZzbchBean bean = new ZzbchDiseaseItemFilter(item).filter();
        if (bean != null) {
            this.add(bean);
        }
    }

    private class ZzbchDiseaseItemFilter implements Filter<ZzbchBean> {

        final ZzbchDiseaseItem item;

        public ZzbchDiseaseItemFilter(ZzbchDiseaseItem item) {
            this.item = item;
        }

        @Override
        public ZzbchBean filter() {
            ZzbchBean bean = new ZzbchBean();
            try {
                Document doc = Jsoup.parse(item.html());
                bean.setChineseName(doc.getElementById("EnglishName").val());
                bean.setEnglishName(doc.getElementById("born").val());
                bean.setPathogen(doc.getElementById("kbx").val());
                bean.setFeature(doc.getElementById("kcx").val());
                bean.setDamagePosition(doc.getElementById("zpsq").val());
                bean.setDamageSymptom(doc.getElementById("bzq").val());
                Elements textAreas = doc.select("textarea[id=dzq]");
                bean.setTransmission(textAreas.first().val());
                bean.setCureMethod(textAreas.last().val());

                Elements image = doc.select("a[onClick]");
                if (image.size() > 0) {
                    Matcher matcher = imageUrlPattern.matcher(image.first().attr("onClick"));
                    if (matcher.find()) {
                        String imageSrc = ZzbchConstant.ROOT_URL + matcher.group();
                        logger.info("抓取的图片地址:" + imageSrc);
                        String fileName = UUID.randomUUID() + ".jpg";
                        InputStream imageInputStream = Request.getResponse(imageSrc).body().byteStream();
                        MongoGridFS.saveFile(mongoTemplate, imageInputStream, fileName);
                        bean.setImageName(fileName);
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
