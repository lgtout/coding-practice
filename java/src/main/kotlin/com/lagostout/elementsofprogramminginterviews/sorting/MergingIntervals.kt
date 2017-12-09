package com.lagostout.elementsofprogramminginterviews.sorting

import org.apache.commons.collections4.iterators.PeekingIterator

/**
 * Problem 14.5 page 246
 */
@Suppress("NAME_SHADOWING")
fun mergeIntervals(intervals: List<IntRange>,
                   newInterval: IntRange): List<IntRange> {
    val mergedIntervals = mutableListOf<IntRange>()
    val intervalsIterator = PeekingIterator(intervals.iterator())
    var newInterval = newInterval
    var merged = false
    while (true) {
        if (!intervalsIterator.hasNext()) break
        val interval = intervalsIterator.next()
        when {
            newInterval.first in interval ->
                newInterval = IntRange(interval.first,
                    maxOf(newInterval.last, interval.last))
            newInterval.last in interval -> {
                newInterval = IntRange(newInterval.first, interval.last)
                mergedIntervals.add(newInterval)
                merged = true
            }
            newInterval.last < interval.first -> {
                mergedIntervals.addAll(listOf(newInterval, interval))
                merged = true
            }
            else -> mergedIntervals.add(interval)
        }
    }
    if (!merged) {
        mergedIntervals.add(newInterval)
    }
    return mergedIntervals
}