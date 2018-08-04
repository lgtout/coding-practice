package com.lagostout.elementsofprogramminginterviews.honors

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data1
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object LongestContiguousIncreasingSubarray : Spek({

    val data = listOfNotNull<Data1<List<Int>, Pair<Int, Int>?>>(
        data(emptyList(), null),
        data(listOf(1), Pair(0,0)),
        data(listOf(1,2), Pair(0,1)),
        data(listOf(1,2,0,1,2), Pair(2,4)),
        data(listOf(1,2,2,1,0,5,6,7,1,2), Pair(4,7)),
        null
    ).toTypedArray()

    describe("longestContiguousIncreasingSubarray") {
        on("list %s", with = *data) { list, expected ->
            val result = longestContiguousIncreasingSubarray(list)
            it("returns $expected") {
                assertThat(result).isEqualTo(expected)
            }
        }
    }

})