package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.bytebybyte.dynamicprogramming.KnightProbability.computeBottomUpWithMemoization
import com.lagostout.bytebybyte.dynamicprogramming.KnightProbability.computeWithRecursionAndBruteForce
import com.lagostout.bytebybyte.dynamicprogramming.KnightProbability.computeWithRecursionAndMemoization
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data

object KnightProbabilitySpek : Spek({

    val data = listOfNotNull(
        data(1, 1, Pair(0,0), 1, 0f),
        data(1, 1, Pair(0,0), 0, 1f),
        data(3, 3, Pair(0,0), 1, 0.25f),
        data(3, 3, Pair(0,0), 2, 0.0625f),
        data(3, 3, Pair(0,0), 3, 0.015625f),
        data(2, 3, Pair(0,0), 2, 0.015625f),
        data(3, 3, Pair(0,0), 5, 0.0009765625f),
        null
    ).toTypedArray()

    describe("computeWithRecursionAndBruteForce") {
        data.forEach { (height, width, start, moves, expected) ->
            given("height: $height, width: $width, " +
                    "start: $start, moves: $moves") {
                it("returns $expected") {
                    assertThat(computeWithRecursionAndBruteForce(
                        height, width, start, moves)).isEqualTo(expected)
                }
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        data.forEach { (height, width, start, moves, expected) ->
            given("height: $height, width: $width, " +
                    "start: $start, moves: $moves") {
                it("returns $expected") {
                    assertThat(computeWithRecursionAndMemoization(
                        height, width, start, moves)).isEqualTo(expected)
                }
            }
        }
    }

    describe("computeBottomUpWithMemoization") {
        data.forEach { (height, width, start, moves, expected) ->
            given("height: $height, width: $width, " +
                    "start: $start, moves: $moves") {
                it("returns $expected") {
                    assertThat(computeBottomUpWithMemoization(
                        height, width, start, moves)).isEqualTo(expected)
                }
            }
        }
    }
})

