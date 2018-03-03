package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.bytebybyte.dynamicprogramming.RodCutting.computeBottomUpWithMemoization
import com.lagostout.bytebybyte.dynamicprogramming.RodCutting.computeWithRecursionAndBruteForce
import com.lagostout.bytebybyte.dynamicprogramming.RodCutting.computeWithRecursionAndMemoization
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object RodCuttingSpek : Spek({

    val data = listOfNotNull(
        listOf(2, 5),
        listOf(4, 10),
        listOf(5, 13),
        null
    ).map { (rodLen, expected) ->
        listOf(1, 5, 8, 9, 10).let {
            data(rodLen, it, expected)
        }
    }.toTypedArray()

    describe("computeWithRecursionAndBruteForce") {
        on("rodLen: %s, prices: %s", with = *data) { rodLen, prices, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndBruteForce(rodLen, prices))
                        .isEqualTo(expected)
            }
        }
        println()
    }

    describe("computeWithRecursionAndMemoization") {
        on("rodLen: %s, prices: %s", with = *data) { rodLen, prices, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndMemoization(rodLen, prices))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeBottomUpWithMemoization") {
        on("rodLen: %s, prices: %s", with = *data) { rodLen, prices, expected ->
            it("returns $expected") {
                assertThat(computeBottomUpWithMemoization(rodLen, prices))
                        .isEqualTo(expected)
            }
        }
    }

})
