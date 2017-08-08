package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.paukov.combinatorics3.Generator

// TODO
// What if there is a 0 in the list?
// Random case generator.

class ThreeSumProblemSpek : Spek({
    describe("") {

    }
}) {
    companion object {
        data class TestCase(val numbers: List<Int> = emptyList(), val sum: Int) {
            val expected = bruteForceCanPickThreeWithRepetitionAllowedThatAddUpToSum(numbers, sum)
        }
        val testCases: List<TestCase> = listOf(
                TestCase(sum = 0),
                TestCase(listOf(0), sum = 0),
                null
        ).filterNotNull()
        fun bruteForceCanPickThreeWithRepetitionAllowedThatAddUpToSum(numbers: List<Int>, sum: Int): Boolean =
                Generator.combination(numbers).multi(3).find { it.sum() == sum } != null
    }
}