package com.jecyhw.bcch.service;

import com.jecyhw.bcch.bean.BcchBean;
import com.jecyhw.bcch.html.BcchConstant;
import com.jecyhw.bcch.html.BcchDItem;
import com.jecyhw.bcch.repository.BcchRepository;
import com.jecyhw.shared.request.MongoGridFS;
import com.jecyhw.shared.request.Request;
import com.jecyhw.shared.response.filter.Filter;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jecyhw on 16-8-22.
 */
@Service
public class BcchService {

    static final Logger logger = Logger.getLogger(BcchService.class);

    @Autowired
    private BcchRepository bcchRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(BcchBean bean) {
        bcchRepository.save(bean);
    }

    public void addAll(List<BcchBean> beans) {
        bcchRepository.save(beans);
    }

    public void add(BcchDItem item) {
        BcchBean bean = new KechDiseaseItemFilter(item).filter();
        if (bean != null) {
            this.add(bean);
        }
    }

    private class KechDiseaseItemFilter implements Filter<BcchBean> {

        final BcchDItem item;

        public KechDiseaseItemFilter(BcchDItem item) {
            this.item = item;
        }

        @Override
        public BcchBean filter() {
            BcchBean bean = new BcchBean();
            try {
                Document doc = Jsoup.parse(item.html());
                Elements ps = doc.select("p");

                bean.setClassification(doc.getElementById("lblMap").text());
                bean.setChineseName(doc.getElementById("lblNameZHCN").text());
                bean.setEnglishName(doc.getElementById("lblNameEng").text());
                bean.setSynonym(doc.getElementById("lblSynonyms").text());
                bean.setIntroduction(doc.getElementById("lblIntroduction").text());

                Elements imgs = doc.select("#Thumbnail  td > div > a > img");
                List<String> imageNames = new ArrayList<>();
                for (Element img : imgs) {
                    String imageSrc = BcchConstant.ROOT_URL + img.attr("src");
                    logger.info("抓取的图片地址:" + imageSrc);
                    String fileName = UUID.randomUUID() + ".jpg";
                    try {
                        InputStream imageInputStream = Request.getResponse(imageSrc).body().byteStream();
                        MongoGridFS.saveFile(mongoTemplate, imageInputStream, fileName);
                        imageNames.add(fileName);
                    } catch (Exception e) {
                        imageNames.add(imageSrc);
                        logger.error(e.getMessage());
                    }
                }
                if (imageNames.size() > 0) {
                    bean.setImageNames(imageNames);
                }

                bean.setDamageSymptom(doc.getElementById("lblDamageSym").text());
                bean.setPathogen(doc.getElementById("trPathogen").text());
                bean.setCycle(doc.getElementById("trCycle").text());
                bean.setOccurrenceFactor(doc.getElementById("trOFactor").text());
                bean.setMorphology(doc.getElementById("trMorphology").text());
                bean.setLifeHabit(doc.getElementById("trHabits").text());
                bean.setCureMethod(doc.getElementById("trCMethod").text());
            } catch (Exception e) {
                logger.error(e.getMessage());
                return null;
            }
            return bean;
        }
    }
}
