package com.lagostout.elementsofprogramminginterviews.hashtables

import com.lagostout.common.offsetFromLast
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.TypeSafeDiagnosingMatcher
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import java.util.*
import kotlin.test.assertFailsWith

class RearrangeArraySoNoEqualElementsAreKOrLessApartSpek : Spek({
    describe("rearrangeArraySoNoEqualElementsAreKOrLessApart") {
        testCases.forEach {
            (array, k) ->
            given("an array $array and k $k") {
                it("rearranges the array so no equal " +
                        "elements are $k or less apart") {
                    val result = rearrangeArraySoNoEqualElementsAreKOrLessApart(array, k)
                    println(result)
                    println()
                    assertThat(result, noEqualElementsAreKOrLessApart(k))
                }
            }
        }
        describe("k is negative") {
            val k = -1
            val array = listOf(1)
            given("k $k") {
                it("throws an exception") {
                    assertFailsWith<IllegalArgumentException> {
                        rearrangeArraySoNoEqualElementsAreKOrLessApart(array, k)
                    }
                }
            }
        }
        describe("array doesn't have enough elements to fulfill constraint") {
            val k = 1
            val array = listOf(1,1)
            given("k $k, array $array") {
                it("throws an exception") {
                    assertFailsWith<IllegalArgumentException> {
                        rearrangeArraySoNoEqualElementsAreKOrLessApart(array, k)
                    }
                }
            }
        }
    }
}) {
    companion object {

        class TestCase(rawList: List<Int> = emptyList(), val k: Int = 0) {
            val list = rawList.toMutableList().apply {
                Collections.shuffle(this)
            }
            operator fun component1() = list
            operator fun component2() = k
        }

        val testCases: List<TestCase> = run {
            listOf(
                    TestCase(listOf()),
                    TestCase(listOf(1), 0), // No two consecutive equal numbers
                    TestCase(listOf(1), 1),
                    TestCase(listOf(1,2), 0),
                    TestCase(listOf(1,2), 1),
                    TestCase(listOf(1,1,2), 1),
                    TestCase(listOf(2,1,1), 1),
                    TestCase(listOf(2,1,1), 0),
                    TestCase(listOf(2,1,1,2), 1),
                    TestCase(listOf(2,1,1,2,1), 1),
                    TestCase(listOf(2,1,2,1,2,3,4), 1),
                    null
            ).filterNotNull()
        }

        fun noEqualElementsAreKOrLessApart(k: Int): Matcher<List<Int>> {
            return object : TypeSafeDiagnosingMatcher<List<Int>>() {

                override fun matchesSafely(list: List<Int>?,
                                           mismatchDescription: Description?): Boolean {
                    list?: return false
                    val elementToOccurrences = mutableMapOf<Int, MutableList<Int>>()
                    val indexedValueOfViolation = list.withIndex().find { (index, element) ->
                        elementToOccurrences.run {
                            ((get(element)?.run { index <= last() + k }) ?: false).also {
                                elementToOccurrences[element] =
                                        getOrDefault(element, mutableListOf()).apply {
                                            add(index)
                                        }
                            }
                        }
                    }
                    val matches = indexedValueOfViolation == null
                    indexedValueOfViolation?.apply {
                        elementToOccurrences[value]?.run {
                            val occurrence1 = offsetFromLast(1)
                            val occurrence2 = last()
                            val distance = occurrence2 - occurrence1
                            mismatchDescription?.appendText(
                                    "value $value at " +
                                            "indices $occurrence1 " +
                                            "and $occurrence2 are $distance apart")
                        }
                    }
                    return matches
                }

                override fun describeTo(description: Description?) {
                    description?.appendText("no equal elements $k or less apart")
                }

            }
        }
    }

}

