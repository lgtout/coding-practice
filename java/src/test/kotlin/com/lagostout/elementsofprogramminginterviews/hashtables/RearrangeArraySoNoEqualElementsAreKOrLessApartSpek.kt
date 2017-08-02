package com.lagostout.elementsofprogramminginterviews.hashtables

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertTrue

class RearrangeArraySoNoEqualElementsAreKOrLessApartSpek : Spek({
    describe("rearrangeArraySoNoEqualElementsAreKOrLessApart") {
        testCases.forEach {
            (array, k) ->
            given("an array $array and k $k") {
                it("rearranges the array so no equal elements are $k or less apart") {
                    assertTrue(noEqualElementsAreKOrLessApart(
                            rearrangeArraySoNoEqualElementsAreKOrLessApart(array, k), k))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val array: List<Int> = emptyList(), val k: Int = 0)
        val testCases: List<TestCase> = run {

            listOf(
                    TestCase(listOf()),
                    null
            ).filterNotNull()
        }
        // TODO Make custom hamcrest matcher.
        fun noEqualElementsAreKOrLessApart(array: List<Int>, k: Int): Boolean {
            val elementToLastOccurrenceIndex = mutableMapOf<Int, Int>()
            return array.withIndex().find { (index, element) ->
                elementToLastOccurrenceIndex.run {
                    val violatesConstraint =
                            get(element)?.run { index <= this + k }?: false
                    this[element] = index
                    violatesConstraint
                }
            } == null
        }
    }
}

