package com.lagostout.elementsofprogramminginterviews.arrays

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object SpiralOrderingOf2DArraySpek : Spek({

    val data = listOfNotNull(
        data(emptyList(), emptyList()),
        data(listOf(listOf(1)), listOf(1)),
        data(listOf(listOf(1,2)), listOf(1,2)),
        data(listOf(listOf(1,2,3)), listOf(1,2,3)),
        data(listOf(listOf(1), listOf(2)), listOf(1,2)),
        data(listOf(listOf(1), listOf(2), listOf(3)), listOf(1,2,3)),
        data(listOf(listOf(1,2), listOf(3,4)), listOf(1,2,4,3)),
        data(listOf(listOf(1,2,3,4), listOf(5,6,7,8), listOf(9,10,11,12)),
            listOf(1,2,3,4,8,12,11,10,9,5,6,7)),
        null
    ).toTypedArray()

    describe("spiralOrdering") {
        on("array: %s", with = *data) { array, expected ->
            it("should return $expected") {
                assertThat(spiralOrdering(array))
                        .isEqualTo(expected)
            }
        }
    }

})