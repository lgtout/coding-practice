package com.lagostout.elementsofprogramminginterviews.arrays

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

class DutchNationalFlagSpek : Spek({
    describe("rearrangeAsDutchNationalFlag") {
        testCases.forEach { (array, pivot, expected) ->
            given("") {
                it("") {

                }
            }
        }
    }
}) {
    companion object {
        class TestCase(val array: Array<Int> = emptyArray(), val pivot: Int = 0) {
            private val expected: Array<Int> = run {
                val lessThan = mutableListOf<Int>()
                val equalTo = mutableListOf<Int>()
                val greaterThan = mutableListOf<Int>()
                array.forEach {
                    (when {
                        it < pivot -> lessThan
                        it > pivot -> greaterThan
                        else -> equalTo
                    }).add(it)
                }
                (lessThan + equalTo + greaterThan).toTypedArray()
            }
            operator fun component1() = array
            operator fun component2() = pivot
            operator fun component3() = expected
        }
        val testCases = listOf(
                TestCase(),
                null).filterNotNull()
    }
}