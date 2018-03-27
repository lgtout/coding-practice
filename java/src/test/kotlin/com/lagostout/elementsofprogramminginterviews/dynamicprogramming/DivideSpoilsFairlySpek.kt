package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.common.nextInt
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.DivideSpoilsFairly.Spoils
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.DivideSpoilsFairly.computeBottomUpWithMemoization
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.DivideSpoilsFairly.computeWithRecursionAndBruteForce
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.DivideSpoilsFairly.computeWithRecursionAndMemoization
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator
import kotlin.math.absoluteValue

object DivideSpoilsFairlySpek : Spek({

    val randomData by memoized {
        val caseCount = 100
        val itemCountRange = (0..10)
        val valueRange = (0..20)
        val random = RandomDataGenerator().apply {
            reSeed(1)
        }
        (1..caseCount).map {
            (1..random.nextInt(itemCountRange)).map {
                random.nextInt(valueRange)
            }.withIndex().toList().let { items ->
                Generator.subset(items).simple().map {
                    Pair(it, items - it)
                }.sortedBy {
                    (it.first.sumBy { it.value } -
                            it.second.sumBy { it.value }).absoluteValue
                }.first().let {
                    data(items.map { it.value },
                        Pair(Spoils(it.first.map { it.value }),
                            Spoils(it.second.map { it.value })))
                }
            }
        }.toTypedArray()
    }

    describe("computeWithRecursionAndBruteForce") {
        on("items: %s", with = *randomData) { items, expected ->
            it("should return $expected") {
                assertThat(computeWithRecursionAndBruteForce(items)
                        .toList().map { it.value })
                        .containsOnlyElementsOf(
                            expected.toList().map { it.value })
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        on("items: %s", with = *randomData) { items, expected ->
            it("should return $expected") {
                assertThat(computeWithRecursionAndMemoization(items)
                        .toList().map { it.value })
                        .containsOnlyElementsOf(
                            expected.toList().map { it.value })
            }
        }
    }

    describe("computeBottomUpWithMemoization") {
        on("items: %s", with = *randomData) { items, expected ->
            it("should return $expected") {
                assertThat(computeBottomUpWithMemoization(items)
                        .toList().map { it.value })
                        .containsOnlyElementsOf(
                            expected.toList().map { it.value })
            }
        }
    }
})