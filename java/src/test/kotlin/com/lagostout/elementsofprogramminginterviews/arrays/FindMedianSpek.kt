package com.lagostout.elementsofprogramminginterviews.arrays

import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object FindMedianSpek : Spek({

    fun computeExpected(list: List<Int>): Double? {
        fun medianBounds(array: List<Int>): Pair<Int, Int> {
            return (array.count() / 2).let {
                Pair(it, it).let {
                    val countIsOdd = array.count() % 2 == 1
                    if (countIsOdd) it
                    else it.copy(first = it.first - 1)
                }
            }
        }
        return if (list.isEmpty()) null
        else list.sorted().let { sortedList ->
            medianBounds(sortedList).let {
                (sortedList[it.first] + sortedList[it.second]) / 2.0
            }
        }
    }

    val randomData by memoized {
        val caseCount = 10
        val numberRange = Pair(-5, 5)
        val numberCount = Pair(0, 10)
        val random = RandomDataGenerator().apply { reSeed(1) }
        (0 until caseCount).map {
            random.nextInt(numberCount).let { numberCount ->
                (0..numberCount).map {
                    random.nextInt(numberRange)
                }
            }.let {
                data(it, computeExpected(it))
            }
        }.toTypedArray()
    }

    describe("findMedian") {
        on("array: %s", with = *randomData) { array, expected ->
            it("should return $expected") {
                assertThat(findMedian(array)).isEqualTo(expected)
            }
        }
    }

})