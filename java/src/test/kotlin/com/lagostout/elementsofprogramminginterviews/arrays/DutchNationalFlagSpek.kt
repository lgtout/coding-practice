package com.lagostout.elementsofprogramminginterviews.arrays

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class DutchNationalFlagSpek : Spek({
    describe("rearrangeAsDutchNationalFlag") {
        testCases.forEach { (array, pivotIndex, expected) ->
            given("array: $array, pivot $pivotIndex") {
                it("rearranges array as $expected") {
                    val mutableArray = array.toMutableList()
                    rearrangeAsDutchNationalFlag(mutableArray, pivotIndex)
                    assertEquals(expected, mutableArray)
                }
            }
        }
    }
}) {
    companion object {
        class TestCase(private val array: List<Int> = emptyList(),
                       private val pivotIndex: Int = 0) {
            private val expected: List<Int> = run {
                val lessThan = mutableListOf<Int>()
                val equalTo = mutableListOf<Int>()
                val greaterThan = mutableListOf<Int>()
                array.forEach {
                    (when {
                        it < array[pivotIndex] -> lessThan
                        it > array[pivotIndex] -> greaterThan
                        else -> equalTo
                    }).add(it)
                }
                (lessThan + equalTo + greaterThan)
            }
            operator fun component1() = array
            operator fun component2() = pivotIndex
            operator fun component3() = expected
        }
        val testCases = listOf(
                TestCase(),
                TestCase(listOf(1), 0),
                TestCase(listOf(1,1), 0),
                TestCase(listOf(1,1), 1),
                TestCase(listOf(1,1,0), 0),
                TestCase(listOf(1,1,0), 2),
                null).filterNotNull()
    }
}