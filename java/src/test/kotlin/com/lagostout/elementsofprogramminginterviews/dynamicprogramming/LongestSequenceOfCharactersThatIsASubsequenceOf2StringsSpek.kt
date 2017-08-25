package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class LongestSequenceOfCharactersThatIsASubsequenceOf2StringsSpek: Spek({
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

    }
}
