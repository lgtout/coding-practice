package com.lagostout.elementsofprogramminginterviews.sorting

import com.google.common.base.MoreObjects
import com.lagostout.elementsofprogramminginterviews.sorting.UnionOfIntervals.EndPoint.Companion.c
import com.lagostout.elementsofprogramminginterviews.sorting.UnionOfIntervals.EndPoint.Companion.o
import com.lagostout.elementsofprogramminginterviews.sorting.UnionOfIntervals.PointType.CLOSED
import com.lagostout.elementsofprogramminginterviews.sorting.UnionOfIntervals.PointType.OPEN
import org.apache.commons.collections4.iterators.PeekingIterator

/* Problem 14.6 page 248 */

object UnionOfIntervals {

    enum class PointType(val n: String) {
        OPEN("open"), CLOSED("closed")
    }

    data class EndPoint(val value: Int, val type: PointType) {
        override fun toString(): String {
            return MoreObjects.toStringHelper(
                    this.javaClass.canonicalName.split(".").last())
                    .add("value", value)
                    .add("type", type)
                    .toString()
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
        var (start, end) = iterator.next()
        do {
            val noOverlapWithNextInterval = run {
                iterator.peek()?.let {
                    end.value < it.start.value ||
                            (end.value == it.start.value &&
                                    listOf(end, it.start)
                                            .all { it.type == OPEN })
                } ?: true
            }
            if (noOverlapWithNextInterval) {
                mergedIntervals.add(Interval(start, end))
                if (iterator.hasNext()) {
                    iterator.next()?.let {
                        start = it.start
                        end = it.end
                    }
                } else break
            } else {
                iterator.next().let {
                    if (it.start.value == start.value && it.start.type == CLOSED) {
                        start = it.start
                    }
                    println(start)
                    println(it.start)
                    end = when {
                        it.end.value < end.value -> end
                        it.end.value > end.value -> it.end
                        else -> {
                            (if (listOf(end, it.end)
                                    .any { it.type == CLOSED }) ::c else ::o)
                                    .invoke(it.end.value)
                        }
                    }
                }
            }
        } while (true)
        return mergedIntervals
    }

}
