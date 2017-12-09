package com.lagostout.elementsofprogramminginterviews.searching

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data2
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object Search2DSortedArraySpek : Spek({
    describe("findInTwoDimensionSortedArray()") {
        val data = listOfNotNull<Data2<List<List<Int>>, Int, Pair<Int, Int>?>>(
                data(emptyList(), 0, null),
                data(listOf(emptyList()), 0, null),
                data(listOf(listOf(1)), 0, null),
                data(listOf(listOf(1)), 1, Pair(0,0)),
                data(listOf(listOf(1)), 2, null),
                data(listOf(listOf(1,2)), 2, Pair(0,1)),
                data(listOf(listOf(1,2)), 1, Pair(0,0)),
                data(listOf(listOf(1,2,3)), 2, Pair(0,1)),
                data(listOf(listOf(1,2,3)), 0, null),
                data(listOf(listOf(-3,-2,-1,2,3)), -2, Pair(0,1)),
                data(listOf(listOf(-3,-2,-1,2,3)), -4, null),
                data(listOf(
                        listOf(-3,-2,-1,2,3),
                        listOf(-3, 1, 2,2,3)), -4, null),
                data(listOf(
                        listOf(-4,-3,-1,2,3),
                        listOf(-3,-2,-1,2,3),
                        listOf( 2, 1, 2,4,5)), -2, Pair(1,1)),
                data(listOf(
                        listOf(-4,-3,-1,2,7),
                        listOf(-3,-2,-1,3,7),
                        listOf( 2, 4, 6,7,8),
                        listOf( 4, 5, 6,8,9)), 4, Pair(2,1)),
                data(listOf(
                        listOf(1,1,1),
                        listOf(1,1,1),
                        listOf(1,1,2)), 2, Pair(2,2)),
                data(listOf(
                        listOf(1,1,1),
                        listOf(1,1,1),
                        listOf(1,1,1)), 2, null),
                data(listOf(
                        listOf(1,1,1),
                        listOf(1,1,1),
                        listOf(1,1,1)), 0, null),
                data(listOf(
                        listOf(1,2,3),
                        listOf(2,3,4),
                        listOf(6,7,8)), 5, null),
                null
        ).toTypedArray()
        on("array: %s, value: %d", with = *data) { array, value, expected ->
            it("returns $expected") {
                assertThat(findInTwoDimensionSortedArray(value, array))
                        .isEqualTo(expected)
            }
        }
    }
})
