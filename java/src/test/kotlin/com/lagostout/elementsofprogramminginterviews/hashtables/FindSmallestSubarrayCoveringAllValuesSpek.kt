package com.lagostout.elementsofprogramminginterviews.hashtables

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class FindSmallestSubarrayCoveringAllValuesSpek : Spek({
    data class TestCase(val words: List<String>,
                        val searchWords:Set<String>,
                        val expected: IntRange?)
    describe("smallestSubarrayCoveringSearchWords") {
        listOf(
                TestCase(listOf("a"), setOf("a"), (0..0))
                ,TestCase(listOf("a"), setOf("b"), null)
                ,TestCase(listOf("a"), setOf("b","c"), null)
                ,TestCase(listOf("a"), setOf("a","b"), null)
                ,TestCase(listOf("a", "b", "c"), setOf("a", "c"), (0..2))
                ,TestCase(listOf("a", "b", "c"), setOf("b", "c"), (1..2))
                ,TestCase(listOf("a", "b", "c", "a"), setOf("a", "b"), (0..1))
                ,TestCase(listOf("a", "b", "c", "a"), setOf("c", "a"), (2..3))
                ,TestCase(listOf("a", "b", "c", "a"), setOf("a", "c"), (2..3))
                ,TestCase(listOf("a", "b", "c", "a", "c", "a", "b", "a", "d", "b"),
                        setOf("a", "b"), (5..6))
        ).forEach {
            given("words ${it.words}") {
                it("finds ${it.expected} as index range of the " +
                        "smallest subarray covering search words ${it.searchWords}") {
                    assertEquals(it.expected,
                            smallestSubarrayCoveringSearchWords(it.words, it.searchWords))
                }
            }
        }
    }
})