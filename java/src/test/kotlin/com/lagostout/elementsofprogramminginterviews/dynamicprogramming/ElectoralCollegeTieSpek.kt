package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.common.nextInt
import com.lagostout.common.subtractFirstOfEachFrom
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.ElectoralCollegeTie.computeBottomUpWithMemoization
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.ElectoralCollegeTie.computeWithRecursionAndBruteForce
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.ElectoralCollegeTie.computeWithRecursionAndMemoization
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator

object ElectoralCollegeTieSpek : Spek({

    val randomData by memoized {
        val electoralCountRange = 1..11
        // We limit state count to speed up subset generation.
        val statesCount = 11
        val caseCount = 100
        val random = RandomDataGenerator().apply { reSeed(1) }
        (0 until caseCount).map {
            (0 until statesCount).map {
                random.nextInt(electoralCountRange)
            }
        }.map { electoralCounts ->
            Generator.subset(electoralCounts).simple().map {
                Pair(it, it.subtractFirstOfEachFrom(electoralCounts))
            }.filter {
                it.first.sum() == it.second.sum()
            }.let {
                data(electoralCounts, it.isNotEmpty())
            }
        }.toTypedArray()
    }

    describe("computeWithRecursionAndBruteForce") {
        on("electoralCounts: %s", with = *randomData) { electoralCounts, expected ->
            it("should return $expected") {
                computeWithRecursionAndBruteForce(electoralCounts).let {
                    assertThat(it).isEqualTo(expected)
                }
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        on("electoralCounts: %s", with = *randomData) { electoralCounts, expected ->
            it("should return $expected") {
                computeWithRecursionAndMemoization(electoralCounts).let {
                    assertThat(it).isEqualTo(expected)
                }
            }
        }
    }

    describe("computeBottomUpWithMemoization") {
        on("electoralCounts: %s", with = *randomData) { electoralCounts, expected ->
            it("should return $expected") {
                computeBottomUpWithMemoization(electoralCounts).let {
                    assertThat(it).isEqualTo(expected)
                }
            }
        }
    }

})
