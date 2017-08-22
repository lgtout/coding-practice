package com.lagostout.elementsofprogramminginterviews.hashtables

import com.google.common.collect.Range
import com.lagostout.common.length
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class FindLongestSubarrayWithDistinctElementsSpek : Spek({
    describe("longestSubarrayWithDistinctElements") {
        testCases.forEach {
            (list, expectedSubarrayIndexRange) ->
            given("list: $list") {
                it("returns range: $expectedSubarrayIndexRange") {
                    assertEquals(expectedSubarrayIndexRange,
                            longestSubarrayWithDistinctElements(list))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val list: List<Int> = emptyList()) {
            private val expectedSubarrayIndexRange = run {
                if (list.isEmpty()) null
                else {
                    var rangeOfLongestDistinctSubarray: Range<Int>? = null
                    (0..list.lastIndex).forEach { i ->
                        (i..list.lastIndex).forEach { j ->
                            val subSequence = list.subList(i, j+1)
                            if (subSequence.size == subSequence.toSet().size &&
                                    (rangeOfLongestDistinctSubarray == null ||
                                            subSequence.size > rangeOfLongestDistinctSubarray!!.length)) {
                                rangeOfLongestDistinctSubarray = Range.closed(i, j)
                            }
                        }
                    }
                    rangeOfLongestDistinctSubarray
                }
            }
            operator fun component2() = expectedSubarrayIndexRange
        }
        val testCases = listOf(
                TestCase(),
                TestCase(listOf(1)),
                TestCase(listOf(1,1)),
                TestCase(listOf(1,2)),
                TestCase(listOf(1,2,1)),
                TestCase(listOf(1,2,1,2)),
                TestCase(listOf(1,2,1,2,3)),
                TestCase(listOf(1,2,1,2,3,1)),
                null).filterNotNull()
    }
}