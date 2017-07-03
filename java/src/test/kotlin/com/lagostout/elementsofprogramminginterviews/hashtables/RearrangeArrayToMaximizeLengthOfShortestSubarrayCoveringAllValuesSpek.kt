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
        data class TestCase(val values: List<Int>) {
            val subarrayLength: Int = if (values.isEmpty()) 0
            else {
                val count = values.size
                PermutationIterator(values)
                        .asSequence().map {
                    it.map {
                        index ->
                        values[index]
                    }
                }.map {
                    // Safe to use !! here because we've
                    // already handled case where array is empty. So
                    // we know rangeOfShortestSubarrayOfDistinctValues()
                    // will not return null.
                    rangeOfShortestSubarrayOfDistinctValues(it)!!.count()
                }.reduce {
                    acc, value ->
                    if (value > acc) value else acc
                }
            }
            operator fun component2() = subarrayLength
        }
        val testCases = listOf(
                // TODO More cases
                TestCase(listOf(1,1,2,3)),
                null
        ).filterNotNull()
    }
}