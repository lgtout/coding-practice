package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.SearchForSequenceIn2DArray.computeBottomUpWithMemoization
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.SearchForSequenceIn2DArray.computeWithRecursionAndBruteForce
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.SearchForSequenceIn2DArray.computeWithRecursionAndMemoization
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object SearchForSequenceIn2DArraySpek : Spek({

    val data = listOfNotNull(
        data(emptyList<List<Int>>(), emptyList(), true),
        data(emptyList<List<Int>>(), listOf(1), false),
        data(listOf(listOf(0)), emptyList(), true),
        data(listOf(listOf(0)), listOf(0), true),
        data(listOf(listOf(0)), listOf(0,1), false),
        data(listOf(listOf(0,1)), listOf(0,1), true),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(0,1), true),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(0,0), true),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(1,1), true),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(1,0), true),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(1,1,0), true),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(1,0,0), true),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(0,0,1), true),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(1,1,1), true),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(0,1,0), true),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(0,1,0,1,1), true),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(0,1,1,0,0,1,1), true),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(0,1,0,1,1,2), false),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(0,1,0,2,1,1), false),
        data(listOf(listOf(0,1), listOf(1,0)), listOf(0,1), true),
        data(listOf(listOf(1,0,1), listOf(1,0,1), listOf(0,1,0)), listOf(1,0,1), true),
        null
    ).toTypedArray()

    describe("computeWithRecursionAndBruteForce") {
        on("grid: %s, pattern: %s", with = *data) { grid, pattern, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndBruteForce(grid, pattern))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        on("grid: %s, pattern: %s", with = *data) { grid, pattern, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndMemoization(grid, pattern))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeBottomUpWithMemoization") {
        on("grid: %s, pattern: %s", with = *data) { grid, pattern, expected ->
            it("returns $expected") {
                assertThat(computeBottomUpWithMemoization(grid, pattern))
                        .isEqualTo(expected)
            }
        }
    }

})