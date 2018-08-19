package com.lagostout.elementsofprogramminginterviews.searching

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
        val countIsOdd = list.count() % 2 == 1
        val medianIndices = (list.count() / 2).let {
            if (countIsOdd) listOf(it)
            else listOf(it - 1, it)
        }
        return if (list.isEmpty()) null
        else list.sorted().let { sortedList ->
            medianIndices.sumBy { sortedList[it] }
                    .let { it / (if (countIsOdd) 1.0 else 2.0) }
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

    val data = listOfNotNull(
        data(listOf(-5,-1,1,3), 0.0),
        data(listOf(0,2), 1.0),
        data(listOf(-5,5,-5), -5.0),
        null
    ).toTypedArray()

    describe("findMedian") {
        on("array: %s", with = *randomData) { array, expected ->
            it("should return $expected") {
                assertThat(findMedian(array)).isEqualTo(expected)
            }
        }
    }

})