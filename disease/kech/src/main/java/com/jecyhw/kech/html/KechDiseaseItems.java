package com.jecyhw.kech.html;

import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Created by jecyhw on 16-8-19.
 */
@Component
public class KechDiseaseItems implements Iterable<KechDiseaseItem> {
    int startId= 0;
    int endId = 123;

    @Override
    public Iterator<KechDiseaseItem> iterator() {
        return new KechDiseaseItemIterator();
    }

    class KechDiseaseItemIterator implements Iterator<KechDiseaseItem> {

        int pageId = startId;

        @Override
        public boolean hasNext() {
            return pageId < endId;
        }

        @Override
        public KechDiseaseItem next() {
            pageId++;
            return new KechDiseaseItem(KechConstant.INDEX_URL + "?page=" + pageId);
        }
    }
}
