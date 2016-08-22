package com.jecyhw.fjsnw.html;

import com.jecyhw.shared.request.Page;
import com.jecyhw.shared.request.Request;
import com.jecyhw.shared.request.exception.RequestFailedException;
import com.jecyhw.shared.request.params.Post;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.apache.log4j.Logger;

/**
 * Created by jecyhw on 16-8-18.
 */
public class FjsnwDisease implements Page {
    static final Logger logger = Logger.getLogger(FjsnwDisease.class);
    static final String indexUrl = FjsnwConstant.INDEX_URL;

    int pageNumber;

    public FjsnwDisease(int pageNumber) {
        this.pageNumber = pageNumber;
        //logger.info("当前爬取的病害数据网址为:" + indexUrl);
    }

    @Override
    public String html() {
        Post post = new FjsnwListPostParams(pageNumber);
        logger.info("当前抓取的链接:" + indexUrl + " " + post.toString());
        try {
            String html = Request.post(indexUrl, post);
            logger.info(html);
            return html;
        } catch (RequestFailedException e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    static class FjsnwListPostParams implements Post{
        final String diseaseName;
        final String cause;
        final String method;
        final int pageNum;
        final int isManage;
        final String inputPn;

        public FjsnwListPostParams(int pageNum) {
            this.diseaseName = "";
            this.cause = "";
            this.method = "list";
            this.pageNum = pageNum;
            this.isManage = 0;
            this.inputPn = "";
        }

        @Override
        public RequestBody requestBody() {
            return new FormBody.Builder().add("diseasename", this.diseaseName)
                    .add("cause", this.cause)
                    .add("method", this.method)
                    .add("pageNum", String.valueOf(this.pageNum))
                    .add("isManage", String.valueOf(this.isManage))
                    .add("inputpn", this.inputPn)
                    .build();
        }

        @Override
        public String toString() {
            return "FjsnwListPostParams{" +
                    "diseaseName='" + diseaseName + '\'' +
                    ", cause='" + cause + '\'' +
                    ", method='" + method + '\'' +
                    ", pageNum=" + pageNum +
                    ", isManage=" + isManage +
                    ", inputPn='" + inputPn + '\'' +
                    '}';
        }
    }
}
