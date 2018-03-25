package com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem

import com.lagostout.common.nextInt
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem.FractionalKnapsackProblem.Item
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem.FractionalKnapsackProblem.computeBottomUpWithMemoization
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem.FractionalKnapsackProblem.computeByValueDensity
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem.FractionalKnapsackProblem.computeWithRecursionAndBruteForce
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem.FractionalKnapsackProblem.computeWithRecursionAndMemoization
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator
import java.math.RoundingMode

object FractionalKnapsackProblemSpek : Spek({

    val randomData by memoized {
        val caseCount = 10
        val weightRange = (0..10)
        val valueRange = (0..10)
        val capacityRange = (0..25)
        val itemCountRange = (0..5)
        val random = RandomDataGenerator().apply {
            reSeed(1)
        }
        (1..caseCount).map {
            Pair(random.nextInt(capacityRange),
                (0..random.nextInt(itemCountRange)).map {
                    Item(random.nextInt(weightRange),
                        random.nextInt(valueRange))
                })
        }.toSet().let {
            it
//            listOf(Pair(5, listOf(Item(0,1), Item(2,2), Item(10,2), Item(5,5))))
        }.map { (capacity, items) ->
            Generator.subset(items).simple().flatMap {
                Generator.permutation(it).simple()
            }.map {
//                println(it)
                it.fold(Pair(0.0, 0.0)) { (weight, value), item ->
                    if (item.weight == 0 || (weight + item.weight <= capacity))
                        Pair(weight + item.weight, value + item.value)
                    else if (weight < capacity) {
                        Pair(capacity.toDouble(),
                            value + ((capacity - weight) / item.weight) *
                                    item.value)
                    } else Pair(weight, value)
                }.also {
//                    println(it)
                }
            }.maxBy {
                it.second
            }.let {
                data(capacity, items, (it?.second ?: 0.0)
                        .toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble())
            }
        }.toTypedArray()
    }

    describe("computeWithRecursionAndBruteForce") {
        on("capacity: %s, items: %s", with = *randomData) {
                capacity: Int, items: List<Item>, expected: Double ->
            it("should return $expected") {
                assertThat(computeWithRecursionAndBruteForce(capacity, items))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeByValueDensity") {
        on("capacity: %s, items: %s", with = *randomData) {
                capacity: Int, items: List<Item>, expected: Double ->
            it("should return $expected") {
                assertThat(computeByValueDensity(capacity, items))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        on("capacity: %s, items: %s", with = *randomData) {
                capacity: Int, items: List<Item>, expected: Double ->
            it("should return $expected") {
                assertThat(computeWithRecursionAndMemoization(capacity, items))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeBottomUpWithMemoization") {
        on("capacity: %s, items: %s", with = *randomData) {
                capacity: Int, items: List<Item>, expected: Double ->
            it("should return $expected") {
                assertThat(computeBottomUpWithMemoization(capacity, items))
                        .isEqualTo(expected)
            }
        }
    }

})