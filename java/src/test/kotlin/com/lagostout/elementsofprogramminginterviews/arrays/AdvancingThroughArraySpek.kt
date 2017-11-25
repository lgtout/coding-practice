package com.lagostout.elementsofprogramminginterviews.arrays

import org.apache.commons.math3.distribution.EnumeratedDistribution
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data1
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

typealias ApachePair = org.apache.commons.math3.util.Pair<Int, Double>

object AdvancingThroughArraySpek : Spek({

    describe("advancingToEndOfArrayIsPossible()") {
        given("static data") {
            val data = listOfNotNull(
                    data(listOf(1), true),
                    data(listOf(0), true),
                    data(listOf(1,0), true),
                    data(listOf(1,1), true),
                    data(listOf(0,1), false),
                    data(listOf(1,0,1), false),
                    data(listOf(1,1,1), true),
                    data(listOf(1,0,0,1), false),
                    data(listOf(2,0,0,1), false),
                    data(listOf(3,0,0,1), true),
                    data(listOf(2,0,2,0,0), true),
                    data(listOf(2,4,1,1,0), true),
                    data(listOf(1,4,1,1,0), true),
                    data(listOf(2,0,3,1,0), true),
                    data(listOf(1,2,0,3,1,0), true),
                    data(listOf(1,2,0,1,1,0), true),
                    data(listOf(1,2,2,0,1,0), true),
                    data(listOf(1,0,4,0,1), false),
                    data(listOf(3,3,1,0,2,0,1), true),
                    data(listOf(3,2,0,0,2,0,1), false),
                    data(listOf(4,4,0,0,2,0,1), false),
                    null
            ).toTypedArray()

            on("array: %s", with = *data) { array, expected ->
                it("returns: $expected") {
                    assertThat(advancingToEndOfArrayIsPossible(array))
                            .isEqualTo(expected)
                }
            }
        }

        given("random data") {
            fun endIsReachableByBruteForce(
                    currentPosition: Int, array: List<Int>): Boolean {
                var endIsReachable = currentPosition >= array.lastIndex
                if (endIsReachable) return endIsReachable
                Pair(currentPosition + 1,
                        currentPosition + array[currentPosition]).let {
                    (start, end) ->
                    (start..end).forEach {
                        if (endIsReachableByBruteForce(it, array)) {
                            endIsReachable = true
                            return@let
                        }
                    }
                }
                return endIsReachable
            }

            fun randomData(): Array<Data1<List<Int>, Boolean>> {
                val random = RandomDataGenerator().apply { reSeed(1) }
                // TODO
                // Continue implementing weighted distribution array entries.
                // Replace random entry generation with weighted generation.
                // Verify this solution algorithm works for large random data
                // with about half of cases expecting result = false.
                val entryWeights = listOf(ApachePair(0, 0.4), ApachePair(1, 0.2),
                        ApachePair(2, 0.2), ApachePair(3, 0.2))
                val entryDistribution = EnumeratedDistribution(entryWeights)
                val dataCount = 100
                val dataRows = mutableListOf<Data1<List<Int>, Boolean>>()
                val arraySizeRange = (0..10)
//                val arrayEntryRange = (0..3)
                val arrayEntryRange = (0..9)
                (1..dataCount).forEach {
                    val array = mutableListOf<Int>()
                    val arraySize = random.nextInt(arraySizeRange.first,
                            arraySizeRange.last)
                    (1..arraySize).forEach {
                        val entry = random.nextInt(arrayEntryRange.first,
                                arrayEntryRange.last).let {
                            when (it) {
                                in (0..6) -> 0
                                7 -> 1
                                8 -> 2
                                else -> 3
                            }
                        }
                        array.add(entry)
                    }
                    dataRows.add(data(array, endIsReachableByBruteForce(0, array)))
                }
                return dataRows.toTypedArray()
            }

            on("array: %s", with = *randomData()) { array, expected ->
                it("returns: $expected") {
                    assertThat(advancingToEndOfArrayIsPossible(array))
                            .isEqualTo(expected)
                }
            }
        }
    }
})
