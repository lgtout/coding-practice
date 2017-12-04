package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.NumberOfWaysToTraverse2DArrayWithObstacles.numberOfWaysToTraverse2DArrayWithObstacles
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object NumberOfWaysToTraverse2DArrayWithObstaclesSpek : Spek({
    describe("numberOfWaysToTraverse2DArrayWithObstacles()") {
        fun bruteForceNumberOfWays(row: Int, column: Int,
                                   obstacles: List<List<Boolean>>): Int {
            return when {
                listOf(row, column).any { it < 0} -> 0
                obstacles[row][column] -> 0
                listOf(row, column).all { it == 0} -> 1
                else -> bruteForceNumberOfWays(row - 1, column, obstacles) +
                        bruteForceNumberOfWays(row, column - 1, obstacles)
            }
        }
        val data = listOfNotNull(
                listOf(listOf(true)),
                listOf(listOf(true, true)),
                listOf(listOf(true, true, true)),
                listOf(listOf(true, false, true)),
                listOf(listOf(true, false, false)),
                listOf(listOf(false, false, true)),
                listOf(listOf(false, false, false)),
                listOf(listOf(false, false, false),
                        listOf(false, false, false)),
                listOf(listOf(true, false, false),
                        listOf(false, false, false)),
                listOf(listOf(false, true, false),
                        listOf(false, false, false)),
                listOf(listOf(false, false, true),
                        listOf(false, false, false)),
                listOf(listOf(false, false, false),
                        listOf(true, false, false)),
                listOf(listOf(false, false, false),
                        listOf(false, true, false)),
                listOf(listOf(true, false, false),
                        listOf(false, true, false)),
                listOf(listOf(true, false, true),
                        listOf(false, true, false)),
                listOf(listOf(true, false, true),
                        listOf(false, true, false),
                        listOf(false, false, false)),
                listOf(listOf(true, false, true),
                        listOf(false, true, false),
                        listOf(true, false, true)),
                null
        ).map {
            data(it, bruteForceNumberOfWays(it.lastIndex, it.first().lastIndex, it))
        }.toTypedArray()
        on("array: %s", with = *data) {
            obstacles, expected ->
            it("returns $expected") {
                assertThat(numberOfWaysToTraverse2DArrayWithObstacles(obstacles))
                        .isEqualTo(expected)
            }
        }
    }
})
