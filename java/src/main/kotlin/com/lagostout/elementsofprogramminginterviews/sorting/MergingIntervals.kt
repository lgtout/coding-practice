package com.lagostout.elementsofprogramminginterviews.sorting

import org.apache.commons.collections4.iterators.PeekingIterator

/**
 * Problem 14.5 page 246
 */
fun mergeIntervals(intervals: List<IntRange>,
                   newInterval: IntRange): List<IntRange> {
    val mergedIntervals = mutableListOf<IntRange>()
    val intervalsIterator = PeekingIterator(intervals.iterator())
    while (true) {
        if (!intervalsIterator.hasNext()) break
        var interval = intervalsIterator.next()
        if (newInterval.first in interval) {
            interval = IntRange(interval.start, newInterval.last)
            var innerWhileDone = false
            while (!innerWhileDone) {
                intervalsIterator.peek()?.also {
                    when {
                        interval.last in it -> {
                            interval = IntRange(interval.start, it.last)
                            intervalsIterator.next()
                            innerWhileDone = true
                        }
                        interval.last > it.last -> intervalsIterator.next()
                        interval.last < it.first -> innerWhileDone = true
                    }
                } ?: break
            }
        }
        mergedIntervals.add(interval)
    }
    return mergedIntervals
}