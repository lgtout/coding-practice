package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.isIn
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class LongestSequenceOfCharactersThatIsASubsequenceOf2StringsSpek: Spek({
    describe("longestCommonSubsequence()") {
        testCases.forEach {
            (string1, string2, expected) ->
            given("string1: \"$string1\" string2: \"$string2\"") {
                it("returns one of $expected") {
                    val result = longestCommonSubsequence(string1, string2)
                    assertEquals(expected.first, result.length)
                    MatcherAssert.assertThat(result, isIn(expected.second) )
                }
            }
        }
    }
}) {
    companion object {

        fun bruteForceLongestCommonSubsequences(
                string1: String, string2: String,
                string1Start: Int = 0, string2Start: Int = 0):
                Pair<Int, List<String>> {
            val noMatches = Pair(0, listOf(""))
            if (string1Start > string1.lastIndex ||
                    string2Start > string2.lastIndex)
               return noMatches
            val right = bruteForceLongestCommonSubsequences(
                    string1, string2, string1Start + 1, string2Start)
            val below = bruteForceLongestCommonSubsequences(
                    string1, string2, string1Start, string2Start + 1)
            val belowRight = bruteForceLongestCommonSubsequences(
                    string1, string2, string1Start + 1, string2Start + 1).let {
                if (string1[string1Start] == string2[string2Start]) {
                    Pair(it.first + 1, it.second.map {
                        string1[string1Start] + it })
                } else it
            }
            return listOf(right, below, belowRight).maxBy { it.first } ?: noMatches
        }

        data class TestCase(val string1: String, val string2: String) {
            val expected = bruteForceLongestCommonSubsequences(string1, string2)
            operator fun component3() = expected
        }

        val testCases: List<TestCase> = run {
            listOf(
                    TestCase("", ""),
                    TestCase("", "a"),
                    TestCase("a", ""),
                    TestCase("a", "a"),
                    TestCase("a", "b"),
                    TestCase("ab", "bb"),
                    TestCase("ab", "bbab"),
                    TestCase("saturday", "sundays"),
                    TestCase("carthorse", "orchestra"),
                    null
            ).filterNotNull()
        }

    }
}
