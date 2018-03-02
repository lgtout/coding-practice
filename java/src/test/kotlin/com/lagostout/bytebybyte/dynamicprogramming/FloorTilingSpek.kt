package com.lagostout.bytebybyte.dynamicprogramming

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object FloorTilingSpek : Spek({

    val data = listOfNotNull(
        data(0, 1),
        data(1, 1),
        data(2, 2),
        data(3, 3),
        data(4, 5),
        data(5, 8),
        null
    ).toTypedArray()

    describe("computeByRecursionAndBruteForce") {
        on("width: %s", with = *data) { width: Int, expected: Int ->
            it("returns $expected") {
                assertThat(FloorTiling.computeWithRecursionAndBruteForce(width))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        on("width: %s", with = *data) { width: Int, expected: Int ->
            it("returns $expected") {
                assertThat(FloorTiling.computeWithRecursionAndMemoization(width))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeBottomUpWithMemoization") {
        on("width: %s", with = *data) { width: Int, expected: Int ->
            it("returns $expected") {
                assertThat(FloorTiling.computeBottomUpWithMemoization(width))
                        .isEqualTo(expected)
            }
        }
    }

})
