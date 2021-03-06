package com.jecyhw.zzbch.service;

import com.jecyhw.shared.request.MongoGridFS;
import com.jecyhw.shared.request.Request;
import com.jecyhw.shared.response.filter.Filter;
import com.jecyhw.zzbch.bean.ZzbchBean;
import com.jecyhw.zzbch.html.ZzbchConstant;
import com.jecyhw.zzbch.html.ZzbchPestItem;
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

    static final  Pattern imageUrlPattern = Pattern.compile("UploadFiles[^']*\\.jpg");

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

    public void add(ZzbchPestItem item) {
        ZzbchBean bean = new ZzbchDiseaseItemFilter(item).filter();
        if (bean != null) {
            this.add(bean);
        }
    }

    private class ZzbchDiseaseItemFilter implements Filter<ZzbchBean> {
        final ZzbchPestItem item;

        public ZzbchDiseaseItemFilter(ZzbchPestItem item) {
            this.item = item;
        }

        @Override
        public ZzbchBean filter() {
            ZzbchBean bean = new ZzbchBean();
            try {
                Document doc = Jsoup.parse(item.html());
                bean.setChineseName(doc.getElementById("EnglishName").val());
                bean.setChineseCommonName(doc.getElementById("ly").val());
                bean.setEnglishName(doc.getElementById("born").val());
                bean.setScientificName(doc.getElementById("high").val());
                bean.setSubject(doc.getElementById("width").val());
                bean.setHost(doc.getElementById("kbx").val());
                bean.setDamageFeature(doc.getElementById("kcx").val());
                bean.setMorphological(doc.getElementById("zpsq").val());
                bean.setLifeHabit(doc.getElementById("bzq").val());
                bean.setCureMethod(doc.getElementById("dzq").val());

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

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("showpic\\.asp[^']+\\.jpg");
        Matcher matcher = pattern.matcher("javascript:window.open('showpic.asp?url=UploadFiles/20082141023426167.jpg', ");

        System.out.println(matcher.find());
        System.out.println(matcher.group());
    }
}
