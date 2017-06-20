package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

/**
 * Problem 18.3.1 p342
 */

data class Interval(val start:Int, val end:Int) : Comparable<Interval> {
    override fun compareTo(other: Interval): Int {
        return start - other.start
    }
}

fun minimumSizedSetOfNumbersThatCoverAllIntervals(
        intervals: List<Interval>): List<Int> {
    val cover = mutableListOf<Interval>()
    val sortedIntervals = intervals.sorted()
    cover.add(sortedIntervals[0])
    sortedIntervals.takeLast(sortedIntervals.size - 1).forEach {
        var coverInterval = cover.last().copy()
        if (it.start <= coverInterval.end) {
            coverInterval = coverInterval.copy(
                    start = it.start,
                    end = minOf(coverInterval.end, it.end))
            cover[cover.lastIndex] = coverInterval
        } else {
            cover.add(it)
        }
    }
    return cover.map { it.start }
}

