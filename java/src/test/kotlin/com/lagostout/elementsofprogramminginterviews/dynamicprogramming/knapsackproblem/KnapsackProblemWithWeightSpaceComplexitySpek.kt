package com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem

import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem.KnapsackProblemWithWeightSpaceComplexity.computeBottomUpWithMemoization
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

object KnapsackProblemWithWeightSpaceComplexitySpek : Spek({

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