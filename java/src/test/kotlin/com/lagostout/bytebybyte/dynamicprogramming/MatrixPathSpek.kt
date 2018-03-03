package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.bytebybyte.dynamicprogramming.MatrixPath.computeBottomUpWithMemoization
import com.lagostout.bytebybyte.dynamicprogramming.MatrixPath.computeWithRecursionAndBruteForce_minMax
import com.lagostout.bytebybyte.dynamicprogramming.MatrixPath.computeWithRecursionAndBruteForce_sign
import com.lagostout.bytebybyte.dynamicprogramming.MatrixPath.computeWithRecursionAndMemoization
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object MatrixPathSpek : Spek({
    val data = listOfNotNull(
        data(emptyList(), 0),
        data(listOf(listOf(1)), 1),
        data(listOf(listOf(-3)), -3),
        data(listOf(listOf(2,3)), 6),
        data(listOf(listOf(2), listOf(3)), 6),
        data(listOf(listOf(2), listOf(-3)), -6),
        data(listOf(listOf(2,3), listOf(2,-3)), -12),
        data(listOf(listOf(2,-3), listOf(2,3)), 12),
        data(listOf(
            listOf(-1,2,3),
            listOf(4,5,-6)), 120),
        data(listOf(
            listOf(-1,2),
            listOf(3,-4),
            listOf(5,6)), 72),
        data(listOf(
            listOf(-1,2,3),
            listOf(4,5,-6),
            listOf(7,8,9)), 1080),
        null
    ).toTypedArray()

    describe("computeWithRecursionAndBruteForce_sign") {
        on("matrix %s", with = *data) { matrix, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndBruteForce_sign(matrix))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursionAndBruteForce_minMax") {
        on("matrix %s", with = *data) { matrix, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndBruteForce_minMax(matrix))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        on("matrix %s", with = *data) { matrix, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndMemoization(matrix))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeBottomUpWithMemoization") {
        on("matrix %s", with = *data) { matrix, expected ->
            it("returns $expected") {
                assertThat(computeBottomUpWithMemoization(matrix))
                        .isEqualTo(expected)
            }
        }
    }

})