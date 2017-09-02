package com.lagostout.elementsofprogramminginterviews.sorting

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class IntersectionOfTwoSortedArraysSpek : Spek({
    describe("intersectionOfSortedArrays") {
        testCases.forEach {
            (firstArray, secondArray, expected) ->
            given("first array: $firstArray, second array: $secondArray") {
                it("returns $expected") {
                    assertEquals(expected,
                            intersectionOfSortedArrays(firstArray, secondArray))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(
                val firstArray: List<Int> = emptyList(),
                val secondArray: List<Int> = emptyList()) {
            private val expected = run {
                firstArray.intersect(secondArray).toSortedSet().toList()
            }
            operator fun component3() = expected
        }
        val testCases = listOf(
                TestCase(firstArray = listOf(1,2)),
                TestCase(secondArray = listOf(1,2)),
                TestCase(listOf(1), listOf(1)),
                TestCase(listOf(1), listOf(2)),
                TestCase(listOf(1), listOf(1,2)),
                TestCase(listOf(2), listOf(1,2)),
                TestCase(listOf(1,2), listOf(1,1,2)),
                TestCase(listOf(1,2), listOf(2,2,2)),
                TestCase(listOf(1,2,2), listOf(2,2,2)),
                TestCase(listOf(1,2,2,3,4,5), listOf(2,5)),
                TestCase(listOf(1,2,2,3,4,5,6), listOf(2,5,7)),
                TestCase(listOf(2,5,7), listOf(1,2,2,3,4,5,6)),
                null).filterNotNull()
    }
}