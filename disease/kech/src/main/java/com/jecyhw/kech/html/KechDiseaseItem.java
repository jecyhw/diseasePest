package com.jecyhw.kech.html;

import com.jecyhw.shared.request.Page;
import com.jecyhw.shared.request.Request;
import org.apache.log4j.Logger;

import java.nio.charset.Charset;

/**
 * Created by jecyhw on 16-8-22.
 */
public class KechDiseaseItem implements Page {

    static final Logger logger = Logger.getLogger(KechDiseaseItem.class);

    final String itemUrl;

    public KechDiseaseItem(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    @Override
    public String html() {
        logger.info("当前抓取的链接:" + itemUrl);
        try {
            String html = new String(Request.getResponse(itemUrl).body().bytes(), Charset.forName("GB2312"));
            return html;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "";
        }
    }
}
