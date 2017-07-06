package com.lagostout.elementsofprogramminginterviews.heaps

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class SortIncreasingDecreasingArraysSpek : Spek({
    describe("sort") {
        testCases.forEach {
            (array, expected) ->
            given("array $array") {
                it("sorts the array as $expected") {
                    assertEquals(expected,
                            SortIncreasingDecreasingArray.sort(array))
                }
            }
        }
    }
}) {
    private companion object {
        private data class TestCase(val array: List<Int>) {
            val expected = array.sorted()
            operator fun component2() = expected
        }
        private val testCases = listOf(
                TestCase(listOf()),
                TestCase(listOf(1)),
                TestCase(listOf(1,2,1)),
                TestCase(listOf(1,2,1,4,5,2,2)),
                TestCase(listOf(1,1,1,1,1,1)),
                null).filterNotNull()
    }
}