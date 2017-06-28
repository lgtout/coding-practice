package com.lagostout.elementsofprogramminginterviews.hashtables

import com.lagostout.common.takeIfNotLast
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

private data class TestCase(val values: List<Int>, val expected: IntRange?)

class ShortestSubarrayOfDistinctValuesSpek : Spek({
    describe("rangeOfShortestSubarrayOfDistinctValues") {
        testCases.forEach {
            (values, expected) ->
            given("values $values") {
                it("computes $expected as the shortest " +
                        "subarray of distinct values") {
                    assertEquals(expected,
                            rangeOfShortestSubarrayOfDistinctValues(values))
                }
            }
        }
    }
}) {
    companion object {
        private val testCases:List<TestCase> = listOf(
                TestCase(listOf(), null),
                TestCase(listOf(1), 0..0),
                TestCase(listOf(1,1), 1..1),
                TestCase(listOf(1,2), 0..1),
                TestCase(listOf(1,2,2), 0..1),
                TestCase(listOf(1,2,2,3,1), 2..4),
                TestCase(listOf(1,2,2,3,3,2,1), 4..6),
                TestCase(listOf(1,2,2,3,3,2,1,2), 4..6),
                TestCase(listOf(1,2,2,3,3,2,1,1), 4..6),
                TestCase(listOf(1,2,3,1,2,3,2,3,3,4), 3..9),
                TestCase(listOf(), null)
        ).takeIfNotLast()
    }
}

