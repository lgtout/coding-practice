package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.bytebybyte.dynamicprogramming.MinimumNumberOfCoinsToMakeChange.computeWithMemoizationBottomUp
import com.lagostout.bytebybyte.dynamicprogramming.MinimumNumberOfCoinsToMakeChange.computeWithRecursion
import com.lagostout.bytebybyte.dynamicprogramming.MinimumNumberOfCoinsToMakeChange.computeWithRecursionAndMemoization
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object MakingChangeSpek : Spek({
    describe("computeMinimumNumberOfCoinsToMakeChange") {
        val data = listOfNotNull(
                data(1, 1),
                data(6, 2),
                data(49, 7),
                null
        ).toTypedArray()
        describe("computeWithRecursion") {
            on("amount: %s", with = *data) { amount, expected ->
                it("returns $expected") {
                    assertThat(computeWithRecursion(amount))
                            .isEqualTo(expected)
                }
            }
        }
        describe("computeWithRecursionAndMemoization") {
            on("amount: %s", with = *data) { amount, expected ->
                it("returns $expected") {
                    assertThat(computeWithRecursionAndMemoization(amount))
                            .isEqualTo(expected)
                }
            }
        }
        describe("computeWithMemoizationBottomUp") {
            on("amount: %s", with = *data) { amount, expected ->
                it("returns $expected") {
                    assertThat(computeWithMemoizationBottomUp(amount))
                            .isEqualTo(expected)
                }
            }
        }
    }
})