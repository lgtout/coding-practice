package com.lagostout.elementsofprogramminginterviews.arrays

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object AdvancingThroughArrayWithMinimumStepsSpek : Spek({
    describe("advanceThroughArrayWithMinimumSteps()") {
        val data = listOfNotNull(
                data(listOf(0), 0),
                data(listOf(1), 0),
                data(listOf(1,0), 1),
                data(listOf(2,0), 1),
                data(listOf(2,0,0), 1),
                data(listOf(2,1,0), 1),
                data(listOf(1,2,0), 2),
                data(listOf(1,3,0), 2),
                data(listOf(2,0,3,1,0,1,0), 3),
                data(listOf(2,0,3,1,0,2,0), 3),
                data(listOf(2,0,3,1,0,1,3), 3),
                null
        ).toTypedArray()
        on("array: %s", with = *data) { array, expected ->
            it("returns $expected") {
                assertThat(advanceThroughArrayWithMinimumSteps(array))
                        .isEqualTo(expected)
            }
        }
    }
})
