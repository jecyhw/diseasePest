package com.jecyhw.zzbch.html;

import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Created by jecyhw on 16-8-19.
 */
@Component
public class ZzbchDiseaseItems implements Iterable<ZzbchDiseaseItem> {

    int startId = 0;
    int endId = 352;

    @Override
    public Iterator<ZzbchDiseaseItem> iterator() {
        return new ZzbchDiseaseItemIterator();
    }

    class ZzbchDiseaseItemIterator implements Iterator<ZzbchDiseaseItem> {
        int pageId = startId;

        @Override
        public boolean hasNext() {
            return pageId < endId;
        }

        @Override
        public ZzbchDiseaseItem next() {
            pageId++;
            return new ZzbchDiseaseItem(ZzbchConstant.INDEX_URL + "?id=" + pageId);
        }
    }
}
