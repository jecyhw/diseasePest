package com.jecyhw.fjsnw.html;

import com.jecyhw.shared.request.Page;
import com.jecyhw.shared.request.Request;
import com.jecyhw.shared.request.exception.RequestFailedException;
import org.apache.log4j.Logger;

/**
 * Created by jecyhw on 16-8-19.
 */
public class FjsnwDiseaseItem implements Page {
    static final Logger logger = Logger.getLogger(FjsnwDiseaseItem.class);

    final String itemUrl;

    public FjsnwDiseaseItem(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    @Override
    public String html() {
        logger.info("当前抓取的链接:" + itemUrl);
        try {
            String html = Request.get(itemUrl);
            logger.info(html);
            return html;
        } catch (RequestFailedException e) {
            logger.error(e.getMessage());
            return "";
        }
    }
}
