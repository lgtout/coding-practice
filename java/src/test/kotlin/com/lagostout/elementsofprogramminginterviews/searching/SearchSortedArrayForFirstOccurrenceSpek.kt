package com.lagostout.elementsofprogramminginterviews.searching

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class SearchSortedArrayForFirstOccurrenceSpek : Spek({
    describe("firstOccurrence") {
        testCases.forEach {
            (k, array, expected) ->
            given("array $array k $k") {
                it("returns $expected") {
                    assertEquals(expected, firstOccurrence(array, k))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val k: Int,
                            val array: List<Int> = emptyList(),
                            val expected: Int? = null)
        val testCases = run {
            listOf(TestCase(0),
                    TestCase(0, listOf(1,2)),
                    TestCase(0, listOf(0,1,2), 0),
                    TestCase(1, listOf(0,1,2), 1),
                    TestCase(2, listOf(0,1,2), 2),
                    TestCase(1, listOf(0,1,1,2), 1),
                    TestCase(1, listOf(0,1,1,2,3), 1),
                    TestCase(1, listOf(0,1,1,2,3,4,5), 1),
                    null).filterNotNull()
        }
    }
}