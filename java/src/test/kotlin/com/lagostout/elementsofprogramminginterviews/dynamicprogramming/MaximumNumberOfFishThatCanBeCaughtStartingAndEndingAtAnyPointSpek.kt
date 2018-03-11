package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.common.nextInt
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.MaximumNumberOfFishThatCanBeCaughtStartingAndEndingAtAnyPoint.computeBottomUpWithMemoization
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.MaximumNumberOfFishThatCanBeCaughtStartingAndEndingAtAnyPoint.computeWithRecursionAndBruteForce
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.MaximumNumberOfFishThatCanBeCaughtStartingAndEndingAtAnyPoint.computeWithRecursionAndMemoization
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object MaximumNumberOfFishThatCanBeCaughtStartingAndEndingAtAnyPointSpek : Spek({

    // Uses computeWithRecursionAndBruteForce() for computing expected solution,
    // so only use randomData to test the other functions.
    val randomData by memoized {
        val dimensionRange = (0..5)
        val valueRange = (-5..5)
        val caseCount = 100
        val random = RandomDataGenerator().apply { reSeed(1) }
        (1..caseCount).map {
            val rows = random.nextInt(dimensionRange)
            val cols = random.nextInt(dimensionRange)
            List(rows) {
                List(cols) {
                    random.nextInt(valueRange)
                }
            }.let {
                data(it, computeWithRecursionAndBruteForce(it))
            }
        }.toSet().toTypedArray()
    }

    val data = listOfNotNull(
        data(listOf(listOf(0)), 0),
        data(listOf(listOf(1)), 1),
        data(listOf(listOf(-1)), -1),
        data(listOf(listOf(0,1)), 1),
        data(listOf(listOf(1,0)), 1),
        data(listOf(listOf(1,-1)), 1),
        data(listOf(listOf(-1,1)), 1),
        data(listOf(listOf(-1,-1)), -1),
        data(listOf(listOf(-2,-1)), -1),
        data(listOf(listOf(-1,-2)), -1),
        data(listOf(
            listOf(-1,-2),
            listOf(-1, 2)), 2),
        data(listOf(
            listOf( 3,-2),
            listOf(-1, 2)), 4),
        data(listOf(
            listOf(-1,2),
            listOf(-1,2)), 4),
        data(listOf(
            listOf( 1,2),
            listOf(-1,2)), 5),
        data(listOf(
            listOf(-1,-1,-1),
            listOf(-1, 1,-1),
            listOf(-1,-1,-1)), 1),
        data(listOf(
            listOf(-1,-1,-1),
            listOf(-1, 3,-2),
            listOf(-1,-1, 2)), 4),
        null
    ).toTypedArray()

    describe("computeWithRecursionAndBruteForce") {
        on("sea: %s", with = *data) { sea, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndBruteForce(sea))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        on("sea: %s", with = *randomData) { sea, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndMemoization(sea))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeBottomUpWithMemoization") {
        on("sea: %s", with = *randomData) { sea, expected ->
            it("returns $expected") {
                assertThat(computeBottomUpWithMemoization(sea))
                        .isEqualTo(expected)
            }
        }
    }

})
