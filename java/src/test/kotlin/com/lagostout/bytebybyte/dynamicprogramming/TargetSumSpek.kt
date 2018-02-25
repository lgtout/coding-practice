package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.common.nextBoolean
import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xdescribe
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator
import kotlin.math.absoluteValue

object TargetSumSpek : Spek({

    val data by memoized {
        val random = RandomDataGenerator().apply { reSeed(1) }
        val caseCount = 100
        val numberRange = (1..10)
        val cases = (1..caseCount).map {
            val numberCount = random.nextInt(0,5)
            val case = mutableListOf<Int>()
            // Allow repeats
            while (case.size < numberCount) {
                random.nextInt(numberRange).let {
                    case.add(it)
                }
            }
            case
        }
//                // Otherwise, return a manually specified case.
                .let {
                    listOf(listOf(5,3))
                }
        cases.map { case ->
            case.flatMap {
                listOf(it, -it)
            }.let {
                Generator.combination(it).simple(case.size).mapNotNull {
                    it.toList()
                }
            }.filter {
                case.zip(it).let {
                    it.all { (a, b) -> a.absoluteValue == b.absoluteValue } &&
                            it.size == case.size
                }
            }.distinct().groupBy {
                it.sum()
            }.let { map ->
                // T:F frequency is 4:1
                if (random.nextBoolean(1f)) map.keys.toList().let { // A reachable target.
                    if (it.isEmpty()) Pair(0, listOf(emptyList())) // There's one way to reach 0 with 0 numbers.
                    else {
                        val keyIndex = random.nextInt(0 until it.size)
                        val key = it[keyIndex]
                        Pair(key, map[key]!!).also { it }
                    }
                } else { // An unreachable target.
                    var target: Int
                    val unreachableSum = case.sum() + 1
                    do {
                        target = random.nextInt(-unreachableSum..unreachableSum)
                    } while (map.keys.contains(target))
                    Pair(target, emptyList())
                }
            }.let { (target, expected) -> data(case.toList(), target, expected) }
        }.toTypedArray()
    }

    xdescribe("computeWithBruteForceAndRecursion") {
        on("numbers: %s, target: %s", with = *data) { numbers, target, expected ->
            it("should return $expected") {
                assertThat(TargetSum.computeWithBruteForceAndRecursion(numbers, target))
                        .isEqualTo(expected.size)
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        on("numbers: %s, target: %s", with = *data) { numbers, target, expected ->
            it("should return $expected") {
                assertThat(TargetSum.computeWithRecursionAndMemoization(numbers, target))
                        .isEqualTo(expected.size)
            }
        }
    }

    xdescribe("computeWithMemoizationBottomUp") {
        on("numbers: %s, target: %s", with = *data) { numbers, target, expected ->
            it("should return $expected") {
                assertThat(TargetSum.computeWithMemoizationBottomUp(numbers, target))
                        .isEqualTo(expected.size)
            }
        }
    }
})

