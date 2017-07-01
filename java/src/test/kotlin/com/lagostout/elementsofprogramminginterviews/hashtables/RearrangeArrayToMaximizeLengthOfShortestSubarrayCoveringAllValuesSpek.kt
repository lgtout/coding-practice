package com.lagostout.elementsofprogramminginterviews.hashtables

import org.apache.commons.math3.util.CombinatoricsUtils.combinationsIterator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class RearrangeArrayToMaximizeLengthOfShortestSubarrayCoveringAllValuesSpek : Spek({
    describe("maximumLengthOfShortestSubarrayCoveringAllValues") {
        testCases.forEach {
            (array, subarrayLength) ->
            given("array $array") {
                it("computes $subarrayLength as the maximum length " +
                        "of shortest subarray covering all values") {
                    assertEquals(subarrayLength,
                            maximumLengthOfShortestSubarrayCoveringAllValues(array))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val array: List<Int>) {
            val subarrayLength: Int = if (array.isEmpty()) 0
            else {
                val count = array.size
                combinationsIterator(count, count)
                        .asSequence().map {
                    it.map {
                        index ->
                        array[index]
                    }
                }.map {
                    // Safe to use !! here because we've
                    // already handled case where array is empty. So
                    // we know rangeOfShortestSubarrayOfDistinctValues()
                    // will not return null.
                    rangeOfShortestSubarrayOfDistinctValues(it)!!.count()
                }.reduce {
                    acc, value ->
                    if (acc < value) acc else value
                }
            }
            operator fun component2() = subarrayLength
        }
        val testCases = listOf(
                TestCase(listOf(1,1,2,3)),
                null
        ).filterNotNull()
    }
}