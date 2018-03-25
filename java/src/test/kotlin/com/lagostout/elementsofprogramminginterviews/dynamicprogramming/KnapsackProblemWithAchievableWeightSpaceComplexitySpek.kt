package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.KnapsackProblemSpek.randomData
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.KnapsackProblemWithAchievableWeightSpaceComplexity.computeBottomUpWithMemoization
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

object KnapsackProblemWithAchievableWeightSpaceComplexitySpek : Spek({

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