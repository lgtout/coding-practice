package com.lagostout.elementsofprogramminginterviews.arrays

import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data

object DeleteDuplicatesFromArraySpek : Spek({
    describe("deleteDuplicatesFromArray") {
        fun bruteForceDeleteDuplicatesFromArray(array: IntArray): Pair<IntArray, Int> {
            return array.distinct().let { list ->
                Pair(IntArray(array.size) {
                    if (it < list.size) list[it]
                    else 0
                }, list.size)
            }
        }
        fun randomData(): List<IntArray> {
            val count = 1000
            val numberRange = (0..20)
            val arraySizeRange = (0..100)
            val data = mutableSetOf<IntArray>()
            val random = RandomDataGenerator().apply { reSeed(1) }
            (1..count).forEach {
                val arraySize = random.nextInt(arraySizeRange)
                val array = IntArray(arraySize)
                (0 until arraySize).forEach {
                    array[it] = random.nextInt(numberRange)
                }
                array.sort()
                data.add(array)
            }
            return data.toList()
        }
        val data = randomData().map {
            data(it, bruteForceDeleteDuplicatesFromArray(it))
        }
        data.forEach { (array, expected) ->
            given("array: ${array.toList()}") {
                it("returns array: ${expected.first.toList()}, " +
                        "valid element count: ${expected.second}") {
                    val distinctNumberCount = deleteDuplicatesFromArray(array)
                    assertThat(array).isEqualTo(expected.first)
                    assertThat(distinctNumberCount)
                            .isEqualTo(expected.second)
                }
            }
        }
    }
})