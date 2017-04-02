package com.lagostout.util

class HeapUtil {

    /**
     * If there is a list in a test's data, prepend it with
     * an empty element.  This makes heap indexing 1-based,
     * instead of 0-based.
     */
    static public Closure toOneBasedHeap = { List<Object> it ->
        it.collect {
            if (it instanceof List) {
                ((List)it).add(0, null)
            }
            it
        }
    }

}
