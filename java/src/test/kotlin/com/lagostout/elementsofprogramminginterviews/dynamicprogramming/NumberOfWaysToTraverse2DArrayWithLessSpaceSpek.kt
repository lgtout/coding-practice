package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object NumberOfWaysToTraverse2DArrayWithLessSpaceSpek: Spek({
    describe("numberOfWaysToTraverse2DArray()") {
        val data = listOfNotNull(
                data(1,1,1),
                data(1,2,1),
                data(2,1,1),
                data(2,2,2),
                data(2,3,3),
                data(4,4,20),
                data(5,5,70),
                null
        ).toTypedArray()
        on("array: %sx%s", with = *data) {
            rowCount, columnCount, expected ->
            it("returns $expected") {
                assertThat(numberOfWaysToTraverse2DArrayWithLessSpace(
                        rowCount, columnCount)).isEqualTo(expected)
            }
        }
    }
})
