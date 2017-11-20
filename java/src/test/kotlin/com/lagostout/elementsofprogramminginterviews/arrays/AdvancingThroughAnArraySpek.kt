package com.lagostout.elementsofprogramminginterviews.arrays

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object AdvancingThroughAnArraySpek : Spek({
    describe("advancingToEndOfArrayIsPossible()") {
        val data = listOfNotNull(
                data(listOf(1), true),
                data(listOf(0), true),
                data(listOf(1,0), true),
                data(listOf(1,1), true),
                data(listOf(0,1), false),
                data(listOf(1,0,1), false),
                data(listOf(1,1,1), true),
                data(listOf(1,0,0,1), false),
                data(listOf(2,0,0,1), false),
                data(listOf(3,0,0,1), true),
                data(listOf(2,0,2,0,0), true),
                data(listOf(2,4,1,1,0), true),
                data(listOf(1,4,1,1,0), true),
                data(listOf(2,0,3,1,0), true),
                data(listOf(1,2,0,3,1,0), true),
                data(listOf(1,2,0,1,1,0), true),
                data(listOf(1,2,2,0,1,0), true),
                data(listOf(1,0,4,0,1), false),
                data(listOf(3,3,1,0,2,0,1), true),
                data(listOf(3,2,0,0,2,0,1), false),
                null
        ).toTypedArray()
        on("array: %s", with = *data) { array, expected ->
            it("returns: $expected") {
                assertThat(advancingToEndOfArrayIsPossible(array))
                        .isEqualTo(expected)
            }
        }
    }
})
