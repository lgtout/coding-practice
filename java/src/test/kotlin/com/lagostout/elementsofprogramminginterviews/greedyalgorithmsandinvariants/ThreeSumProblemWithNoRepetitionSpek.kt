package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.paukov.combinatorics3.Generator
import kotlin.test.assertEquals

class ThreeSumProblemWithNoRepetitionSpek : Spek({
    describe("canPickThreeWithRepetitionAllowedThatAddUpToSum") {
        testCases.forEach {
            (numbers, sum, expected) ->
            given("numbers $numbers, sum $sum") {
                it("returns $expected") {
                    assertEquals(expected,
                            canPickThreeWithNoRepetitionThatAddUpToSum(numbers, sum))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val numbers: List<Int> = emptyList(), val sum: Int) {
            val expected = bruteForceCanPickThreeWithNoRepetitionThatAddUpToSum(
                    numbers, sum)
            operator fun component3() = expected
        }
        val testCases: List<TestCase> = listOf(
                TestCase(sum = 0),
                TestCase(listOf(-1,0,1), sum = 0),
                TestCase(listOf(5,2,3,4,3), sum = 9),
                TestCase(listOf(5,2,3,4,3), sum = 3),
                null
        ).filterNotNull()
        fun bruteForceCanPickThreeWithNoRepetitionThatAddUpToSum(
                numbers: List<Int>, sum: Int): Boolean {
            val combinationsThatAddUpToSum = Generator.combination(numbers.toSet())
                    .simple(3)
                    .filter { !it.isEmpty() && it.sum() == sum }
            println(combinationsThatAddUpToSum)
            return combinationsThatAddUpToSum.isNotEmpty()
        }
    }
}