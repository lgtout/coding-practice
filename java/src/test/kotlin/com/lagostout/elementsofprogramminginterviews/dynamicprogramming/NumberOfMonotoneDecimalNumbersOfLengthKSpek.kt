package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.NumberOfMonotoneDecimalNumbersOfLengthK.computeBottomUpWithMemoization
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.NumberOfMonotoneDecimalNumbersOfLengthK.computeWithRecursionAndBruteForce
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.NumberOfMonotoneDecimalNumbersOfLengthK.computeWithRecursionAndMemoization
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator

object NumberOfMonotoneDecimalNumbersOfLengthKSpek : Spek({

    val randomData by memoized {
        val kRange = (0..5)
        val digits = (1..9)
        kRange.map { k ->
            val expected = Generator.permutation(digits.toList())
                    .withRepetitions(k).filter {
                        it.sorted() == it
                    }.also{
                        if (k==0) println(it)
                    }.run { if (size == 1 && first().isEmpty()) 0 else count() }
            data(k, expected)
        }.toSet()
    }

    val data = (listOfNotNull(
        data(0, 0),
        data(1, 9),
        null
    ) + randomData).toTypedArray()

    describe("computeWithRecursionAndBruteForce") {
        on("k: %s", with = *data) { k, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndBruteForce(k))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        on("k: %s", with = *data) { k, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndMemoization(k))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeBottomUpWithMemoization") {
        on("k: %s", with = *data) { k, expected ->
            it("returns $expected") {
                assertThat(computeBottomUpWithMemoization(k))
                        .isEqualTo(expected)
            }
        }
    }

})