package com.lagostout.elementsofprogramminginterviews.sorting

import com.lagostout.elementsofprogramminginterviews.sorting.UnionOfIntervals.EndPoint.Companion.c
import com.lagostout.elementsofprogramminginterviews.sorting.UnionOfIntervals.EndPoint.Companion.o
import com.lagostout.elementsofprogramminginterviews.sorting.UnionOfIntervals.Interval.Companion.i
import com.lagostout.elementsofprogramminginterviews.sorting.UnionOfIntervals.computeUnionOfIntervals
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object UnionOfIntervalsSpek : Spek({
    describe("computeUnionOfIntervals") {
        val data = listOfNotNull(
//                data(listOf(i(o(1),o(1))), listOf(i(o(1),o(1)))),
//                data(listOf(i(c(1),c(1))), listOf(i(c(1),c(1)))),
//                data(listOf(i(o(1),c(1))), listOf(i(o(1),c(1)))),
//                data(listOf(i(o(1),c(1)), i(o(2),c(2))),
//                        listOf(i(o(1),c(1)), i(o(2),c(2)))),
//                data(listOf(i(o(1),c(1)), i(o(1),c(1))),
//                        listOf(i(o(1),c(1)))),
                data(listOf(i(o(1),o(1)), i(o(1),c(1))),
                        listOf(i(o(1),c(1)))),
                null
        ).toTypedArray()
        on("intervals: %s", with = *data) { intervals, expected ->
            it("returns $expected") {
                assertThat(computeUnionOfIntervals(intervals))
                        .containsExactlyElementsOf(expected)
            }
        }
    }
})