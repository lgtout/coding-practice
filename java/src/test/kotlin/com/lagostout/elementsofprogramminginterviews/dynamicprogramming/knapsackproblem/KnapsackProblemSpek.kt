package com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem

import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem.KnapsackProblem.computeBottomUpWithMemoization
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem.KnapsackProblem.computeWithRecursionAndBruteForce
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem.KnapsackProblem.computeWithRecursionAndMemoization
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

object KnapsackProblemSpek : Spek({

    describe("computeWithRecursionAndBruteForce") {
        randomData.forEach { (clocks, capacity, expected) ->
            given("clocks $clocks, capacity: $capacity") {
                it("should be $expected") {
                    assertThat(computeWithRecursionAndBruteForce(
                        capacity, clocks))
                            .isEqualTo(expected)
                }
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        randomData.forEach { (clocks, capacity, expected) ->
            given("clocks $clocks, capacity: $capacity") {
                it("should be $expected") {
                    assertThat(computeWithRecursionAndMemoization(
                        capacity, clocks))
                            .isEqualTo(expected)
                }
            }
        }
    }

    describe("computeBottomUpWithMemoization") {
        randomData.forEach { (clocks, capacity, expected) ->
            given("clocks $clocks, capacity: $capacity") {
                it("should be $expected") {
                    assertThat(computeBottomUpWithMemoization(
                        capacity, clocks))
                            .isEqualTo(expected)
                }
            }
        }
    }

})
