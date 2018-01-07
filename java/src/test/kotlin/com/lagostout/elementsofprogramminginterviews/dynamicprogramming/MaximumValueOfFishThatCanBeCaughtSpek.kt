package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.common.nextBoolean
import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object MaximumValueOfFishThatCanBeCaughtSpek : Spek({
    fun maximumValue(sea: List<List<Int>>, row: Int, col: Int): Int {
        if (row > sea.lastIndex || col > sea[0].lastIndex) return 0
        return maxOf(maximumValue(sea, row + 1, col),
                maximumValue(sea, row, col + 1)) + sea[row][col]
    }
    fun expected(sea: List<List<Int>>): Int {
        return if (sea.isEmpty() || sea[0].isEmpty()) 0
        else maximumValue(sea, 0, 0)
    }
    data class TestCase(val sea: List<List<Int>>, val expected: Int)
    fun randomCases(): List<TestCase> {
        val cases = mutableListOf<TestCase>()
        val caseCount = 100
        val fishValueRange = (1..10)
        val seaSideLengthRange = (0..10)
        val random = RandomDataGenerator().apply { reSeed(1) }
        val fishConcentration = 0.5f
        (1..caseCount).forEach {
            val colCount = random.nextInt(seaSideLengthRange)
            val sea = List(size = random.nextInt(seaSideLengthRange)) {
                List(size = colCount) {
                    if (random.nextBoolean(fishConcentration)) 0
                    else random.nextInt(fishValueRange)
                }
            }
            cases.add(TestCase(sea, expected(sea)))
        }
        return cases
    }
    describe("maximumValueOfFishThatCanBeCaught") {
        val data = randomCases()
                .map { data(it.sea, it.expected) }
                .toTypedArray()
        on("sea: %s", with = *data) { sea, expected ->
            it("returns $expected") {
                assertThat(maximumValueOfFishThatCanBeCaught(sea))
                        .isEqualTo(expected)
            }
        }
    }
})

