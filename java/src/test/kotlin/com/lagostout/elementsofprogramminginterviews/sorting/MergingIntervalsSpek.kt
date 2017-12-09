package com.lagostout.elementsofprogramminginterviews.sorting

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object MergingIntervalsSpek : Spek({
    describe("mergeIntervals()") {
        fun r(start: Int, end: Int): IntRange {
            return IntRange(start, end)
        }
        val data = listOfNotNull(
//                data(emptyList(), r(0,0), listOf(r(0,0))),
//                data(listOf(r(0,1)), r(0,1), listOf(r(0,1))),
//                data(listOf(r(0,1)), r(2,3), listOf(r(0,1), r(2,3))),
//                data(listOf(r(0,2)), r(1,3), listOf(r(0,3))),
//                data(listOf(r(2,3)), r(0,1), listOf(r(0,1), r(2,3))),
                data(listOf(r(0,1), r(2,3), r(4,5)), r(1,4), listOf(r(0,5))),
//                data(listOf(r(0,1), r(2,3), r(4,5)), r(1,2), listOf(r(0,3), r(4,5))),
                null
        ).toTypedArray()
        on("intervals: %s, interval: %s", with = *data) {
            intervals, interval, expected ->
            it("returns $expected") {
                assertThat(mergeIntervals(intervals, interval))
                        .isEqualTo(expected)
            }
        }
    }
})