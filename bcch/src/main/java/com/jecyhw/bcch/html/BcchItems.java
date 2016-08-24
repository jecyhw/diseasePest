package com.jecyhw.bcch.html;

import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Created by jecyhw on 16-8-19.
 */
@Component
public class BcchItems implements Iterable<BcchDItem> {
    int startId= 0;
    int endId = 3523;

    @Override
    public Iterator<BcchDItem> iterator() {
        return new KechDiseaseItemIterator();
    }

    class KechDiseaseItemIterator implements Iterator<BcchDItem> {

        int pageId = startId;

        @Override
        public boolean hasNext() {
            return pageId < endId;
        }

        @Override
        public BcchDItem next() {
            pageId++;
            return new BcchDItem(BcchConstant.INDEX_URL + "?id=" + pageId);
        }
    }
}
