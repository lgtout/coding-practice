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
    var merging = false
    while (true) {
        if (!intervalsIterator.hasNext()) {
            if (!merged) mergedIntervals.add(newInterval)
            break
        }
        val interval = intervalsIterator.next()
        if (!merging) {
            when {
                !merged && newInterval.first in interval -> {
                    newInterval = IntRange(interval.first,
                            maxOf(newInterval.last, interval.last))
                    merging = true
                }
                else -> mergedIntervals.add(interval)
            }
        } else {
            when {
                newInterval.last >= interval.last -> {
                    newInterval = IntRange(newInterval.first,
                            maxOf(newInterval.last, interval.last))
                }
                newInterval.last < interval.first -> {
                    mergedIntervals.addAll(listOf(newInterval, interval))
                    merged = true
                    merging = false
                }
            }
        }
    }
    return mergedIntervals
}