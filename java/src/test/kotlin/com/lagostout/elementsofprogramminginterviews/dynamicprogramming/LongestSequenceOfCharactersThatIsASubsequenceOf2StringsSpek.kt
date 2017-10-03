package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertTrue

class LongestSequenceOfCharactersThatIsASubsequenceOf2StringsSpek: Spek({
    describe("levenshteinDistance") {
        testCases.forEach {
            (string1, string2, expected) ->
            given("string1: $string1 string2: $string2") {
                it("returns one of $expected") {
                    assertTrue(expected.contains(
                            longestCommonSubsequence(string1, string2)))
                }
            }
        }
    }
}) {
    companion object {

        // Not the most efficient.  But it captures the essential idea
        // of a recursive solution.
        fun bruteForceLongestCommonSubsequence(
                string1: String, string2: String): List<String> {
            val subsequences = mutableListOf<String>()
            bruteForceCommonSubsequences(subsequences, string1, string2)
            return subsequences.apply {
                sortByDescending { it.length }
            }.let {
                it.filter { it.length < subsequences[0].length }
            }
        }

        // TODO
        private fun bruteForceCommonSubsequences(
                commonSubsequences: MutableList<String>,
                string1: String, string2: String,
                string1Start: Int = 0, string2Start: Int = 0) {
            // Could use better names for these cases
//            val lengthWhenFirstCharacterNotIncluded =
//                    bruteForceCommonSubsequences(commonSubsequences)
            val lengthWhenFirstCharacterIncluded = 0
            val lengthWhenFirstCharactersMatch = 0
        }

        data class TestCase(val string1: String, val string2: String) {
            val expected = bruteForceLongestCommonSubsequence(string1, string2)
            operator fun component3() = expected
        }

        val testCases: List<TestCase> = run {
            listOf(
                    TestCase("saturday", "sundays"),
                    TestCase("carthorse", "orchestra"),
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
