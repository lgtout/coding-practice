package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.paukov.combinatorics3.Generator
import kotlin.test.assertEquals

class ThreeSumProblemWithRepetitionAllowedSpek : Spek({
    describe("canPickThreeWithRepetitionAllowedThatAddUpToSum") {
        testCases.forEach {
            (numbers, sum, expected) ->
            given("numbers $numbers, sum $sum") {
                it("returns $expected") {
                    assertEquals(expected,
                            canPickThreeWithRepetitionAllowedThatAddUpToSum(numbers, sum))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val numbers: List<Int> = emptyList(), val sum: Int) {
            val expected = bruteForceCanPickThreeWithRepetitionAllowedThatAddUpToSum(numbers, sum)
            operator fun component3() = expected
        }
        val testCases: List<TestCase> = listOf(
                TestCase(sum = 0),
                TestCase(listOf(0), sum = 0),
                TestCase(listOf(0), sum = 1),
                TestCase(listOf(0,1), sum = 1),
                TestCase(listOf(0,1), sum = 2),
                TestCase(listOf(0,1), sum = 4),
                TestCase(listOf(1,2,3), sum = 4),
                TestCase(listOf(1,2,3), sum = 6),
                TestCase(listOf(1,2,4), sum = 7),
                null
        ).filterNotNull()
        fun bruteForceCanPickThreeWithRepetitionAllowedThatAddUpToSum(
                numbers: List<Int>, sum: Int): Boolean {
            val combinationsThatAddUpToSum = Generator.combination(numbers)
                    .multi(3).filter { !it.isEmpty() && it.sum() == sum }
            println(combinationsThatAddUpToSum)
            return combinationsThatAddUpToSum.isNotEmpty()
        }
    }
}