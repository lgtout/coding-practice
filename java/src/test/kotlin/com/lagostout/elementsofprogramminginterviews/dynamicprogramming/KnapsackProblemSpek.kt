package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.common.nextInt
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.KnapsackProblem.Clock
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.KnapsackProblem.computeBottomUpWithMemoization
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.KnapsackProblem.computeWithRecursionAndBruteForce
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.KnapsackProblem.computeWithRecursionAndMemoization
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.paukov.combinatorics3.Generator

object KnapsackProblemSpek : Spek({

    val randomData by memoized  {
        val caseCount = 100
        val itemCountRange = (0..5)
        val valueRange = (0..5)
        val weightRange = (0..10)
        val random = RandomDataGenerator().apply { reSeed(1) }
        (0..caseCount).map {
            (0..random.nextInt(itemCountRange)).map {
                Clock(random.nextInt(valueRange),
                    random.nextInt(weightRange))
            }.let {
                val maxWeight = random.nextInt(0,
                    (it.sumBy { it.weight } * 1.25).toInt())
                Triple(it, maxWeight,
                    Generator.subset(it).simple().flatMap {
                        Generator.permutation(it).simple()
                    }.filter {
                        it.sumBy { it.weight } <= maxWeight
                    }.map {
                        it.sumBy { it.value }
                    }.max() ?: 0)
            }
        }.map {
            data(it.first, it.second, it.third)
        }.toTypedArray()
    }

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
