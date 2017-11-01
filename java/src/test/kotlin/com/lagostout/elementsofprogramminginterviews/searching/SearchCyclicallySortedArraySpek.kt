package com.lagostout.elementsofprogramminginterviews.searching

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data1
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object SearchCyclicallySortedArraySpek : Spek({
    describe("findPositionOfSmallestEntryInCyclicallySortedArray()") {
        val data = listOf<Data1<List<Int>, Int?>?>(
                data(emptyList(), null),
                data(listOf(1), 0),
                data(listOf(2,1), 1),
                data(listOf(2,3,4,1), 3),
                data(listOf(2,3,4,0,1), 3),
                data(listOf(3,4,1,2), 2),
                null
        ).filterNotNull().toTypedArray()
        on("list %s", with = *data) { list: List<Int>, expected: Int? ->
            it("returns $expected") {
                assertThat(findPositionOfSmallestEntryInCyclicallySortedArray(list))
                        .isEqualTo(expected)
            }
        }
    }
})