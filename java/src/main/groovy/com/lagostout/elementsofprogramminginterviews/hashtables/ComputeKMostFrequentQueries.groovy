package com.lagostout.elementsofprogramminginterviews.hashtables

import com.lagostout.common.FixedCapacityHeap
import com.lagostout.common.Heap
import org.jetbrains.annotations.NotNull

import static com.lagostout.common.FixedCapacityHeap.createFixedCapacityMaxHeap

/**
 * Problem 13.5 p220
 */
class ComputeKMostFrequentQueries {

    static class QueryCount implements Comparable<QueryCount> {
        String query
        int count

        @Override
        int compareTo(@NotNull QueryCount o) {
            return count <=> o.count
        }
    }
    static List<String> compute(List<String> strings, int k) {
        if (strings.isEmpty() || k <= 0) return []
        // Eliminate duplicates
        Map<String, QueryCount> stringToQueryCountMap = [] as HashMap
        strings.each {
            def queryCount = stringToQueryCountMap.get(it)
            if (!queryCount) {
                queryCount = new QueryCount(query:it)
                stringToQueryCountMap.put(it, queryCount)
            }
            queryCount.count++
        }
        // Add unique strings to fixed capacity heap
        FixedCapacityHeap<QueryCount> heap = createFixedCapacityMaxHeap(k)
        heap.addAll(stringToQueryCountMap.values())
        heap.asList().collect {
            it.query
        }
    }

}
