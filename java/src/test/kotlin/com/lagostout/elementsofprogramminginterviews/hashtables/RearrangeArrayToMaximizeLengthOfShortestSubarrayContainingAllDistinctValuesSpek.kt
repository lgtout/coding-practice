package com.lagostout.elementsofprogramminginterviews.hashtables

import org.apache.commons.collections4.iterators.PermutationIterator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.util.*
import kotlin.test.assertEquals

@RunWith(JUnitPlatform::class)
class RearrangeArrayToMaximizeLengthOfShortestSubarrayContainingAllDistinctValuesSpek : Spek({
    describe("maximumLengthOfShortestSubarrayContainingAllDistinctValues") {
        testCases.forEach {
            (array, subarrayLength) ->
            given("array $array") {
                it("computes $subarrayLength as the maximum length " +
                        "of shortest subarray covering all values") {
                    assertEquals(subarrayLength,
                            maximumLengthOfShortestSubarrayContainingAllDistinctValues(array))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val values: List<Int>) {
            val subarrayLength: Int
            init {
                Collections.shuffle(values)
                subarrayLength = if (values.isEmpty()) 0
                else {
                    var longestSubarrayLength: Int = 0
                    PermutationIterator(values).forEach {
                        val length = rangeOfShortestSubarrayOfDistinctValues(it)!!.count()
                        longestSubarrayLength = if (length > longestSubarrayLength)
                            length else longestSubarrayLength
                    }
                    longestSubarrayLength
                }
            }
            operator fun component2() = subarrayLength
        }
        val testCases = listOf(
                TestCase(listOf()),
                TestCase(listOf(1)),
                TestCase(listOf(1,1,2,3)),
                TestCase(listOf(1,1,2,3,4,5)),
                TestCase(listOf(1,1,2,2,3,4,5)),
                TestCase(listOf(1,1,2,2,2,4,4,5)),
                TestCase(listOf(1,1,2,2,2,4,4,5)),
                TestCase(listOf(1,1,2,2,2,4,4,5)),
                null
        ).filterNotNull()
    }
}