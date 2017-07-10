package com.lagostout.elementsofprogramminginterviews.heaps

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class SortAnAlmostSortedArraySpek : Spek({
    describe("sortAlmostSortedArray") {
        testCases.forEach {
            (array, k, expected) ->
            given("array $array") {
                sortAlmostSortedArray(array, k)
                it("sorts the array as $expected") {
                    assertEquals(expected, array)
                }
            }
        }
    }
}) {
    private companion object {
        private data class TestCase(
                val array: MutableList<Int> = mutableListOf(),
                val k: Int = 0) {
            val expected: List<Int> = array.sorted()
            operator fun component3() = expected
        }
        val testCases = listOf(
                TestCase(),
                // Expected: -1, 2, 3, 4, 5, 6, 8
                TestCase(mutableListOf(2,-1,3,5,4,8,6), k = 1),
                TestCase(mutableListOf(3,-1,8,4,5,2,6), k = 4),
                TestCase(mutableListOf(3,-1,2,6,4,5,8), k = 2),
                null).filterNotNull()
    }
}
