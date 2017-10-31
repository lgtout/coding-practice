package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ComputeBuildingsWithSunsetViewWhenBuildingsPresentedEastToWestSpek : Spek({
    describe("computeBuildingsWithSunsetViewWhenBuildingsPresentedEastToWest()") {
        val data = listOf(
                data(emptyList(), emptyList()),
                data(listOf(1), listOf(0)),
                data(listOf(1,2), listOf(1)),
                data(listOf(2,1), listOf(0,1)),
                data(listOf(2,1,4), listOf(2)),
                data(listOf(2,1,2), listOf(2)),
                data(listOf(2,2), listOf(1)),
                data(listOf(4,1,2), listOf(0,2)),
                data(listOf(4,1,2,1), listOf(0,2,3)),
                data(listOf(4,1,2,1,5,2), listOf(4,5)),
                null
        ).filterNotNull().toTypedArray()
        on("buildings %s", with = *data) { buildings, expected ->
            it("returns $expected") {
                assertThat(computeBuildingsWithSunsetViewWhenBuildingsPresentedEastToWest(buildings))
                        .isEqualTo(expected)
            }
        }
    }
})