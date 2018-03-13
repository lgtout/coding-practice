package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.BinomialCoefficient.computeBottomUpWithMemoization
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.BinomialCoefficient.computeWithRecursionAndBruteForce
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.BinomialCoefficient.computeWithRecursionAndMemoization
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object BinomialCoefficientSpek : Spek({

    val data = listOfNotNull(
        data(1, 0, 1),
        data(2, 1, 2),
        data(2, 2, 1),
        data(4, 2, 6),
        data(10, 3, 120),
        data(100, 3, 161700),
        data(6, 3, 20),
        // Fails: algorithm only valid when n >= k
//        data(6, 8, 0.01785714),
        null
    ).toTypedArray()

    describe("computeWithRecursionAndBruteForce") {
        on("n: %s, k: %s", with = *data) { n, k, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndBruteForce(n, k))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        on("n: %s, k: %s", with = *data) { n, k, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndMemoization(n, k))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeBottomUpWithMemoization") {
        on("n: %s, k: %s", with = *data) { n, k, expected ->
            it("returns $expected") {
            assertThat(computeBottomUpWithMemoization(n, k))
                        .isEqualTo(expected)
            }
        }
    }

})

