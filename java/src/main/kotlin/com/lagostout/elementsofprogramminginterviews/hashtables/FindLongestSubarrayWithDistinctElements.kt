package com.lagostout.elementsofprogramminginterviews.hashtables

import com.google.common.collect.Range
import com.lagostout.common.length

fun <T : Comparable<T>> longestSubarrayWithDistinctElements(list: List<T>): List<T> {
    val distinctElementsToIndicesMap = mutableMapOf<T, Int>()
    var rangeOfCurrentDistinctSubarray = Range.closed(0,0)
    var rangeOfLongestDistinctSubarray = rangeOfCurrentDistinctSubarray
    list.forEachIndexed {
        index, value ->
        distinctElementsToIndicesMap[value]?.let { lastOccurrenceIndex ->
            var first = rangeOfCurrentDistinctSubarray.lowerEndpoint()
            while (first <= lastOccurrenceIndex) {
                distinctElementsToIndicesMap.remove(value)
                ++first
            }
            rangeOfCurrentDistinctSubarray =
                Range.closed(first, rangeOfCurrentDistinctSubarray.upperEndpoint())
        }
        distinctElementsToIndicesMap[value] = index
        rangeOfCurrentDistinctSubarray =
            Range.closed(rangeOfCurrentDistinctSubarray.lowerEndpoint(), index)
        if (rangeOfCurrentDistinctSubarray.length >
                rangeOfLongestDistinctSubarray.length)
            rangeOfLongestDistinctSubarray = rangeOfCurrentDistinctSubarray
    }
    return emptyList()
}
