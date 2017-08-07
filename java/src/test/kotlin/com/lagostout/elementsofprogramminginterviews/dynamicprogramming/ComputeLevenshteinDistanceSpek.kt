package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class ComputeLevenshteinDistanceSpek : Spek({
    describe("levenshteinDistance") {
        testCases.forEach {
            (from, to, expected) ->
            given("from: $from to: $to") {
                it("returns $expected") {
                    assertEquals(expected, levenshteinDistance(from, to))
                }
            }
        }
    }
}) {
    companion object {

        data class TestCase(val from: String, val to: String) {
            val expected: Int = bruteForceRightToLeftLevenshteinDistance(from, to)
            operator fun component3() = expected
        }

        val testCases: List<TestCase> = run {
            listOf(
                    TestCase("saturday", "sundays"), // distance: 4
                    TestCase("carthorse", "orchestra"), // distance: 8
                    TestCase("", ""),
                    TestCase("", "a"),
                    TestCase("a", ""),
                    TestCase("a", "a"),
                    TestCase("a", "b"),
                    null
            ).filterNotNull()
        }

        // Recursion in either direction - comparing the strings from left-right,
        // or from right-left - are equally valid solutions.  However, the non-recursive
        // dynamic programming solution is modeled on the right-left recursive solution.

        fun bruteForceLeftToRightLevenshteinDistance(from: String, to: String): Int {
            if (from.isEmpty()) return to.length
            if (to.isEmpty()) return from.length
            val substituteOrNoActionCost = bruteForceLeftToRightLevenshteinDistance(
                    from.substring(1), to.substring(1)) +
                    (if (from.first() == to.first()) 0 else 1)
            val deleteCost = bruteForceLeftToRightLevenshteinDistance(from.substring(1), to) + 1
            val insertCost = bruteForceLeftToRightLevenshteinDistance(from, to.substring(1)) + 1
            return listOf(substituteOrNoActionCost, deleteCost, insertCost).min()!!
        }

        fun bruteForceRightToLeftLevenshteinDistance(from: String, to: String): Int {
            if (from.isEmpty()) return to.length
            if (to.isEmpty()) return from.length
            val substituteOrNoActionCost = bruteForceRightToLeftLevenshteinDistance(
                    from.dropLast(1), to.dropLast(1)) +
                    (if (from.last() == to.last()) 0 else 1)
            val deleteCost = bruteForceRightToLeftLevenshteinDistance(from.dropLast(1), to) + 1
            val insertCost = bruteForceRightToLeftLevenshteinDistance(from, to.dropLast(1)) + 1
            return listOf(substituteOrNoActionCost, deleteCost, insertCost).min()!!
        }
    }
}
