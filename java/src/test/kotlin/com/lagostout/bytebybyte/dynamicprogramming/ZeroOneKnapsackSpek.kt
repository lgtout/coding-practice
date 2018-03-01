package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.bytebybyte.dynamicprogramming.ZeroOneKnapsack.Item.Companion.i
import com.lagostout.bytebybyte.dynamicprogramming.ZeroOneKnapsack.computeWithMemoizationBottomUp1
import com.lagostout.bytebybyte.dynamicprogramming.ZeroOneKnapsack.computeWithMemoizationBottomUp2
import com.lagostout.bytebybyte.dynamicprogramming.ZeroOneKnapsack.computeWithMemoizationBottomUp_functional
import com.lagostout.bytebybyte.dynamicprogramming.ZeroOneKnapsack.computeWithRecursion
import com.lagostout.bytebybyte.dynamicprogramming.ZeroOneKnapsack.computeWithRecursion1
import com.lagostout.bytebybyte.dynamicprogramming.ZeroOneKnapsack.computeWithRecursion2
import com.lagostout.bytebybyte.dynamicprogramming.ZeroOneKnapsack.computeWithRecursion3
import com.lagostout.bytebybyte.dynamicprogramming.ZeroOneKnapsack.computeWithRecursion4
import com.lagostout.bytebybyte.dynamicprogramming.ZeroOneKnapsack.computeWithRecursion5
import com.lagostout.bytebybyte.dynamicprogramming.ZeroOneKnapsack.computeWithRecursionAndMemoization
import com.lagostout.bytebybyte.dynamicprogramming.ZeroOneKnapsack.computeWithRecursionAndMemoizationForManualDebugging
import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator

object ZeroOneKnapsackSpek : Spek({

    val data by memoized {
        listOfNotNull(
            data(emptySet(), 0, 0),
            data(emptySet(), 1, 0),
            data(emptySet(), 1, 0),
            data(setOf(i(1, 1)), 0, 0),
            data(setOf(i(1, 1)), 1, 1),
            data(setOf(i(1, 1)), 2, 1),
            data(setOf(i(1, 2)), 1, 0),
            data(setOf(i(1, 1), i(2, 1)), 1, 2),
            data(setOf(i(1, 1), i(1, 1)), 1, 1),
            data(setOf(i(1, 1), i(1, 1)), 2, 2),
            data(setOf(i(1, 1), i(1, 1), i(1, 1)), 3, 3),
            data(setOf(i(1, 1), i(2, 2)), 1, 1),
            data(setOf(i(1, 1), i(3, 2)), 3, 4),
            data(setOf(i(1, 1), i(3, 2), i(4, 1)), 3, 7),
            data(setOf(i(8, 3), i(3, 2), i(2, 1)), 3, 8),
            data(setOf(i(6, 1), i(10, 2), i(12, 3)), 5, 22),
            /* This next case should have lots of cache hits because
            multiple combinations of weights of items reduce the available
            knapsack capacity by the same amount.  For example, any two items
            will reduce the capacity to 2, any 3 to 3, and so on. */
            data(setOf(i(1, 1), i(1, 1), i(1, 1), i(1, 1), i(1, 1)), 4, 4),
            data(setOf(i(1, 1), i(1, 1), i(1, 1), i(1, 1), i(1, 1)), 2, 2),
            null
        ).toTypedArray()
    }

    val randomData by memoized  {
        val caseCount = 100
        val itemCountRange = (0..5)
        val valueRange = (0..5)
        val weightRange = (0..10)
        val random = RandomDataGenerator().apply { reSeed(1) }
        (0..caseCount).map {
            (0..random.nextInt(itemCountRange)).map {
                ZeroOneKnapsack.Item(random.nextInt(valueRange),
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
            data(it.first.toSet(), it.second, it.third)
        }.toTypedArray()
    }

    describe("randomData") {
        randomData.forEach { (items, maxWeight, expected) ->
            given("items $items, maxWeight: $maxWeight") {
                it("should be $expected") {
                    assertThat(computeWithRecursion1(items.toSet(), maxWeight))
                            .isEqualTo(expected)
                }
            }
        }
    }

    describe("computeWithRecursion1") {
        on("items %s, maxWeight: %s", with = *randomData) {
                items, maxWeight, expected ->
            it("should return $expected") {
                assertThat(computeWithRecursion(
                    items, maxWeight, ::computeWithRecursion1)).isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursion2") {
        on("items %s, maxWeight: %s", with = *randomData) {
                items, maxWeight, expected ->
            it("should return $expected") {
                assertThat(computeWithRecursion(
                    items, maxWeight, ::computeWithRecursion2)).isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursion3") {
        on("items %s, maxWeight: %s", with = *randomData) {
                items, maxWeight, expected ->
            it("should return $expected") {
                assertThat(computeWithRecursion(
                    items, maxWeight, ::computeWithRecursion3)).isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursion4") {
        on("items %s, maxWeight: %s", with = *randomData) {
                items, maxWeight, expected ->
            it("should return $expected") {
                assertThat(computeWithRecursion(
                    items, maxWeight, ::computeWithRecursion4)).isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursion5") {
        on("items %s, maxWeight: %s", with = *randomData) {
                items, maxWeight, expected ->
            it("should return $expected") {
                assertThat(computeWithRecursion(
                    items, maxWeight, ::computeWithRecursion5)).isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        on("items %s, maxWeight: %s", with = *randomData) {
                items, maxWeight, expected ->
            it("should return $expected") {
                assertThat(computeWithRecursionAndMemoization(
                    items.toList(), maxWeight)).isEqualTo(expected)
                println()
            }
        }
    }

    describe("computeWithRecursionAndMemoizationForManualDebugging") {
        on("items %s, maxWeight: %s", with = *randomData) {
                items, maxWeight, expected ->
            it("should return $expected") {
                assertThat(computeWithRecursionAndMemoizationForManualDebugging(
                    items.toList(), maxWeight)).isEqualTo(expected)
                println()
            }
        }
    }

    describe("computeWithMemoizationBottomUp1") {
        on("items %s, maxWeight: %s", with = *randomData) {
                items, maxWeight, expected ->
            it("should return $expected") {
                assertThat(computeWithMemoizationBottomUp1(
                    items, maxWeight)).isEqualTo(expected)
            }
        }
    }

    describe("computeWithMemoizationBottomUp2") {
        on("items %s, maxWeight: %s", with = *randomData) {
                items, maxWeight, expected ->
            it("should return $expected") {
                assertThat(computeWithMemoizationBottomUp2(
                    items, maxWeight)).isEqualTo(expected)
            }
        }
    }

    describe("computeWithMemoizationBottomUp_functional") {
        on("items %s, maxWeight: %s", with = *randomData) {
                items, maxWeight, expected ->
            it("should return $expected") {
                assertThat(computeWithMemoizationBottomUp_functional(
                    items.toList(), maxWeight)).isEqualTo(expected)
            }
        }
    }
})