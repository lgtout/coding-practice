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
                /* If an Interval's EndPoints both have the
                same value, they must either be both closed or open.
                One can't be closed and the other open. */
                data(listOf(i(o(1),o(1))), listOf(i(o(1),o(1)))),
                data(listOf(i(c(1),c(1))), listOf(i(c(1),c(1)))),
                data(listOf(i(c(1),c(2)), i(c(3),c(4))),
                        listOf(i(c(1),c(2)), i(c(3),c(4)))),
                data(listOf(i(c(1),c(2)), i(c(2),c(3))),
                        listOf(i(c(1),c(3)))),
                data(listOf(i(c(1),o(2)), i(c(2),c(3))),
                        listOf(i(c(1),c(3)))),
                data(listOf(i(c(1),o(2)), i(o(2),c(3))),
                        listOf(i(c(1),o(2)), i(o(2),c(3)))),
                data(listOf(i(c(1),o(3)), i(o(2),c(4))),
                        listOf(i(c(1),c(4)))),
                data(listOf(i(c(1),c(3)), i(o(2),c(4))),
                        listOf(i(c(1),c(4)))),
                data(listOf(i(o(1),c(3)), i(c(2),o(4))),
                        listOf(i(o(1),o(4)))),
                data(listOf(i(c(1),o(3)), i(o(2),c(3))),
                        listOf(i(c(1),c(3)))),
                data(listOf(i(o(0),o(3)), i(c(1),c(1)), i(c(2),c(4)),
                        i(c(3),o(4)), i(c(5),o(7)), i(c(7),o(8)),
                        i(c(8),o(11)), i(o(9),c(11)), i(c(12),c(14)),
                        i(o(12),c(16)), i(o(13),o(15)), i(o(16),o(17))),
                        listOf(i(o(0),c(4)), i(c(5),c(11)), i(c(12),o(17)))),
                data(listOf(i(o(0),o(3)), i(c(1),c(1)), i(c(2),c(4)),
                        i(c(3),o(4)), i(c(5),o(7)), i(c(7),o(8)),
                        i(c(8),o(11)), i(o(9),c(11)), i(o(12),c(14)),
                        i(c(12),c(16)), i(o(13),o(15)), i(o(16),o(17))),
                        listOf(i(o(0),c(4)), i(c(5),c(11)), i(c(12),o(17)))),
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