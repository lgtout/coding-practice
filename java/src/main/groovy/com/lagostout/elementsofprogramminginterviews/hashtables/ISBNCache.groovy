package com.lagostout.elementsofprogramminginterviews.hashtables

import com.google.common.annotations.VisibleForTesting

class ISBNCache {

    @VisibleForTesting
    LinkedHashMap<String, Integer> map

    ISBNCache(final int capacity) {
        map = new LinkedHashMap<String, Integer>(
                capacity, 0.75F, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity
            }
        }
    }

    void put(String isbn, int price) {
        if (map.get(isbn) == null)
            map.put(isbn, price)
    }

    void remove(String isbn) {
        map.remove(isbn)
    }

    Integer get(String isbn) {
        return map.get(isbn)
    }

}
