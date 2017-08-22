package com.lagostout.elementsofprogramminginterviews.hashtables

import com.google.common.collect.Range
import com.lagostout.common.length

/**
 * Problem 13.7.6 page 225
 */
fun <T : Comparable<T>> longestSubarrayWithDistinctElements(list: List<T>): Range<Int>? {
    if (list.isEmpty()) return null
    val distinctElementsToIndicesMap = mutableMapOf<T, Int>()
    var rangeOfCurrentDistinctSubarray = Range.closed(0,0)
    var rangeOfLongestDistinctSubarray = rangeOfCurrentDistinctSubarray
    list.forEachIndexed {
        index, value ->
        var first = rangeOfCurrentDistinctSubarray.lowerEndpoint()
        distinctElementsToIndicesMap[value]?.let { lastOccurrenceIndex ->
            while (first <= lastOccurrenceIndex) {
                distinctElementsToIndicesMap.remove(value)
                ++first
            }
        }
        distinctElementsToIndicesMap[value] = index
        rangeOfCurrentDistinctSubarray =
            Range.closed(first, index)
        if (rangeOfCurrentDistinctSubarray.length >
                rangeOfLongestDistinctSubarray.length)
            rangeOfLongestDistinctSubarray = rangeOfCurrentDistinctSubarray
    }
    return rangeOfLongestDistinctSubarray
}
