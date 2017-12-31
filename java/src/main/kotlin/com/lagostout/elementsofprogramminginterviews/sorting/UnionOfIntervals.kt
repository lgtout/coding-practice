package com.lagostout.elementsofprogramminginterviews.sorting

import com.lagostout.elementsofprogramminginterviews.sorting.UnionOfIntervals.EndPoint.Closed
import org.apache.commons.collections4.iterators.PeekingIterator

/**
 * Problem 14.6 page 248
 */
class UnionOfIntervals {

    sealed class EndPoint(val value: Int) {
        class Open(value: Int): EndPoint(value)
        class Closed(value: Int): EndPoint(value)
        operator fun compareTo(other: EndPoint): Int {
            return value - other.value
        }
    }

    data class Interval(val start: EndPoint, val end: EndPoint)

    fun computeUnionOfIntervals(intervals: List<Interval>): List<Interval> {
        val mergedIntervals = mutableListOf<Interval>()
        if (intervals.isEmpty()) return mergedIntervals
        intervals.sortedBy { it.start.value }
        val iterator = PeekingIterator(intervals.iterator())
        var interval = iterator.next()
        var start = interval.start
        var end = interval.end
        while (iterator.hasNext()) {
            val nextInterval = iterator.next()
            if (end > nextInterval.start ||
                    (end == nextInterval.start &&
                            (end is Closed || nextInterval.start is Closed))) {
                end = nextInterval.end
            } else {
                mergedIntervals.add(interval.copy(end = end))
                iterator.peek()?.let {
                    interval = it
                    start = it.start
                    end = it.end
                }
            }
        }
        return mergedIntervals
    }

}
