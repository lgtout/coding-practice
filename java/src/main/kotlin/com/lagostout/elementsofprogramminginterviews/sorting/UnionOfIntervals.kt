package com.lagostout.elementsofprogramminginterviews.sorting

import com.google.common.base.MoreObjects
import com.lagostout.elementsofprogramminginterviews.sorting.UnionOfIntervals.EndPoint.Closed
import com.lagostout.elementsofprogramminginterviews.sorting.UnionOfIntervals.EndPoint.Open
import org.apache.commons.collections4.iterators.PeekingIterator

/**
 * Problem 14.6 page 248
 */
object UnionOfIntervals {

    sealed class EndPoint(val value: Int) {
        class Open(value: Int): EndPoint(value)
        class Closed(value: Int): EndPoint(value)
        operator fun compareTo(other: EndPoint): Int {
            return value - other.value
        }

        override fun toString(): String {
            return MoreObjects.toStringHelper(
                    this.javaClass.canonicalName.split(".").last())
                    .add("value", value).toString()
        }
        companion object {
            fun o(value: Int) = Open(value)
            fun c(value: Int) = Closed(value)
        }
    }

    data class Interval(val start: EndPoint, val end: EndPoint) {
        companion object {
            fun i(start: EndPoint, end: EndPoint) = Interval(start, end)
        }
    }

    fun computeUnionOfIntervals(intervals: List<Interval>): List<Interval> {
        val mergedIntervals = mutableListOf<Interval>()
        if (intervals.isEmpty()) return mergedIntervals
        intervals.sortedBy { it.start.value }
        val iterator = PeekingIterator(intervals.iterator())
        var interval = iterator.next()
        var end = interval.end
        while (iterator.hasNext()) {
            val noOverlapWithNextInterval = run {
                iterator.peek()?.let {
                    interval.end < it.start ||
                            (interval.end == it.start &&
                                    listOf(interval.end, it.start)
                                            .all { it is Open })
                } ?: true
            }
            if (noOverlapWithNextInterval) {
                mergedIntervals.add(interval.copy(end = end))
                iterator.peek()?.let {
                    interval = it
                    end = it.end
                }
            } else {
                end = iterator.next().end.let {
                    when {
                        it.value < interval.end.value -> interval.end
                        it.value > interval.end.value -> it
                        else -> {
                            (if (listOf(interval.end, it).any { it is Closed })
                                ::Closed else ::Open)
                                    .call("value" to it.value)
                        }
                    }
                }
            }
        }
        return mergedIntervals
    }

}
