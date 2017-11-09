package com.lagostout.elementsofprogramminginterviews.searching

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data2
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object FindElementInCyclicallySortedArraySpek : Spek({
    describe("findElementInCyclicallySortedArray()") {
        val data = listOf<Data2<List<Int>, Int, Int?>?>(
                data(listOf(1,2,3,4), 5, null),
                data(listOf(1,2,3,4), 0, null),
                data(listOf(1,2,4,5), 3, null),
                data(listOf(1,2,3,4), 2, 1),
                data(listOf(1,2,3,4), 3, 2),
                data(listOf(1,2,3,4), 4, 3),
                data(listOf(1,2,3,4), 1, 0),
                data(listOf(4,5,6,1,2,3), 4, 0),
                data(listOf(4,5,6,1,2,3), 5, 1),
                data(listOf(4,5,6,1,2,3), 6, 2),
                data(listOf(4,5,6,1,2,3), 1, 3),
                data(listOf(4,5,6,1,2,3), 2, 4),
                data(listOf(4,5,6,1,2,3), 3, 5),
                null
        ).filterNotNull().toTypedArray()
        on("array: %s, element: %s", with = *data) {
            array, element, expected ->
            it("returns $expected") {
                findElementInCyclicallySortedArray(array, element).let {
                    assertThat(it).isEqualTo(expected)
                }
            }
        }
    }
})