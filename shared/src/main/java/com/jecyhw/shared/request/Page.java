package com.jecyhw.shared.request;

/**
 * Created by jecyhw on 16-8-18.
 * 爬取一张网页内容的接口
 */
public interface Page {
    /**
     * @return 返回抓取网页的html文本
     */
    String html();
}
