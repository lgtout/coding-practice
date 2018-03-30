package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.common.nextInt
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.DivideSpoilsFairly.Spoils
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.DivideSpoilsFairlyWithSameNumberOfItemsForEachThief.computeBottomUpWithMemoization
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.DivideSpoilsFairlyWithSameNumberOfItemsForEachThief.computeWithRecursionAndBruteForce
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.DivideSpoilsFairlyWithSameNumberOfItemsForEachThief.computeWithRecursionAndMemoization
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator
import kotlin.math.absoluteValue

object DivideSpoilsFairlyWithSameNumberOfItemsForEachThiefSpek : Spek({

    val randomData by memoized {
        val caseCount = 100
        val itemCountRange = (0..10)
        val valueRange = (0..20)
        val random = RandomDataGenerator().apply {
            reSeed(1)
        }
        val items = mutableListOf<List<Int>>()
        while (items.size < caseCount) {
            val itemCount = random.nextInt(itemCountRange)
            if (itemCount % 2 == 1) continue
            else items.add((1..itemCount).map {
                random.nextInt(valueRange)
            })
        }
        val cases = items.map {
            it.withIndex().toList().let { items ->
                    Generator.subset(items).simple().map {
                        Pair(it, items - it)
                    }.filter {
                        it.first.size == it.second.size
                    }.sortedBy {
                        (it.first.sumBy { it.value } -
                                it.second.sumBy { it.value }).absoluteValue
                    }.let {
                        items.map { it.value }.let { itemValues ->
                            if (it.isEmpty()) data(itemValues, emptyList())
                            else
                                it.first().let {
                                    data(itemValues,
                                        listOf(Spoils(it.first.map { it.value }),
                                            Spoils(it.second.map { it.value })))
                                }
                        }
                    }
                }
        }
        cases.toTypedArray()
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