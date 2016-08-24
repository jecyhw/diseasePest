package com.jecyhw.zzbch.html;

import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Created by jecyhw on 16-8-19.
 */
@Component
public class ZzbchPestItems implements Iterable<ZzbchPestItem> {
    int startId = 0;
    int endId = 215;

    @Override
    public Iterator<ZzbchPestItem> iterator() {
        return new ZzbchDiseaseItemIterator();
    }

    class ZzbchDiseaseItemIterator implements Iterator<ZzbchPestItem> {
        int pageId = startId;

        @Override
        public boolean hasNext() {
            return pageId < 215;
        }

        @Override
        public ZzbchPestItem next() {
            pageId++;
            return new ZzbchPestItem(ZzbchConstant.INDEX_URL + "?id=" + pageId);
        }
    }
}
