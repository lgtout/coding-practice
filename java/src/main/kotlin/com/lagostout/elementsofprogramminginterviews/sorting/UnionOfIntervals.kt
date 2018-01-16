package com.lagostout.elementsofprogramminginterviews.sorting

import com.google.common.base.MoreObjects
import com.lagostout.elementsofprogramminginterviews.sorting.UnionOfIntervals.EndPoint.Companion.c
import com.lagostout.elementsofprogramminginterviews.sorting.UnionOfIntervals.EndPoint.Companion.o
import com.lagostout.elementsofprogramminginterviews.sorting.UnionOfIntervals.PointType.CLOSED
import com.lagostout.elementsofprogramminginterviews.sorting.UnionOfIntervals.PointType.OPEN
import org.apache.commons.collections4.iterators.PeekingIterator

/**
 * Problem 14.6 page 248
 */
object UnionOfIntervals {

    enum class PointType(val n: String) {
        OPEN("open"), CLOSED("closed")
    }

    data class EndPoint(val value: Int, val type: PointType) {
        override fun toString(): String {
            return MoreObjects.toStringHelper(
                    this.javaClass.canonicalName.split(".").last())
                    .add("value", value).toString()
        }
        companion object {
            fun o(value: Int) = EndPoint(value, OPEN)
            fun c(value: Int) = EndPoint(value, CLOSED)
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
        do {
            val noOverlapWithNextInterval = run {
                iterator.peek()?.let {
                    interval.end.value < it.start.value ||
                            (interval.end.type == it.start.type &&
                                    listOf(interval.end, it.start)
                                            .all { it.type == OPEN })
                } ?: true
            }
            if (noOverlapWithNextInterval) {
                mergedIntervals.add(interval.copy(end = end))
                if (iterator.hasNext()) {
                    iterator.next()?.let {
                        interval = it
                        end = it.end
                    }
                } else break
            } else {
                end = iterator.next().end.let {
                    when {
                        it.value < interval.end.value -> interval.end
                        it.value > interval.end.value -> it
                        else -> {
                            (if (listOf(interval.end, it)
                                    .any { it.type == CLOSED }) ::c else ::o)
                                    .invoke(it.value)
                        }
                    }
                }
            }
        } while (true)
        return mergedIntervals
    }

}
