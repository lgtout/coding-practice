package com.lagostout.elementsofprogramminginterviews.hashtables

import com.lagostout.common.takeNotLast
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class FindSmallestSubarrayCoveringAllValuesSpek : Spek({
    data class TestCase(val words: List<String>,
                        val searchWords:Set<String>,
                        val expected: IntRange?)
    // I think this is sufficient test case coverage,
    // but I'm a little uncertain.
    // I could generate random tests cases and write
    // a brute force solver.  But my instinct says I'll
    // just find out that the current solution is correct.
    // Kicking the can down the road for now.
    val testCases = listOf(
            TestCase(listOf("a"), setOf("a"), (0..0)),
            TestCase(listOf("a"), setOf("b"), null),
            TestCase(listOf("a"), setOf("b","c"), null),
            TestCase(listOf("a"), setOf("a","b"), null),
            TestCase(listOf("a", "b", "c"), setOf("a", "c"), (0..2)),
            TestCase(listOf("a", "b", "c"), setOf("b", "c"), (1..2)),
            TestCase(listOf("a", "b", "c", "a"), setOf("a", "b"), (0..1)),
            TestCase(listOf("a", "b", "c", "a"), setOf("c", "a"), (2..3)),
            TestCase(listOf("a", "b", "c", "a"), setOf("a", "c"), (2..3)),
            TestCase(listOf("a", "b", "c", "d"), setOf("d", "b", "c", "a"), (0..3)),
            TestCase(listOf("a", "b", "c", "d"), setOf("d", "b", "c", "a"), (0..3)),
            TestCase(listOf("a", "b", "a", "c", "d"), setOf("d", "b", "c", "a"), (1..4)),
            TestCase(listOf("a", "c", "b", "d", "c", "a", "b", "a", "d", "b"),
                    setOf("a", "b"), (5..6)),
            TestCase(listOf("a", "c", "b", "d", "c", "a", "b", "a", "d", "b"),
                    setOf("c", "d"), (3..4)),
            TestCase(listOf(), setOf(), null))
    describe("smallestSubarrayCoveringSearchWords") {
        testCases.takeNotLast().forEach {
            (words, searchWords, expected) ->
            given("words $words and search words $searchWords") {
                it("finds $expected as the index range of the " +
                        "smallest subarray covering the search words within " +
                        "the words") {
                    assertEquals(expected,
                            smallestSubarrayCoveringSearchWords(
                                    words, searchWords))
                }
            }
        }
    }
})