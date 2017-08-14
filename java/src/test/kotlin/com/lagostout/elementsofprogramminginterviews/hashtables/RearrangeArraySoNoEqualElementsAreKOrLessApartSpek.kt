package com.lagostout.elementsofprogramminginterviews.hashtables

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.TypeSafeDiagnosingMatcher
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xdescribe
import java.util.*
import kotlin.test.assertFailsWith

class RearrangeArraySoNoEqualElementsAreKOrLessApartSpek : Spek({
    describe("rearrangeArraySoNoEqualElementsAreKOrLessApart") {
        testCases.forEach {
            (array, k) ->
            given("an array $array and k $k") {
                it("rearranges the array so no equal " +
                        "elements are $k or less apart") {
//                    val result = rearrangeArraySoNoEqualElementsAreKOrLessApart(array, k)
                    // TODO Remove when done testing exception-throwing cases
                    val result = listOf(1,1)
                    val k = 1
                    assertThat(result, noEqualElementsAreKOrLessApart(k))
                }
            }
        }
        xdescribe("k is negative") {
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
        xdescribe("array doesn't have enough elements to fulfill constraint") {
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
//                    TestCase(listOf(1), 0), // No two consecutive equal numbers
//                    TestCase(listOf(1), 1),
//                    TestCase(listOf(1,2), 0),
//                    TestCase(listOf(1,2), 1),
//                    TestCase(listOf(1,1,2), 1),
//                    TestCase(listOf(2,1,1), 1),
//                    TestCase(listOf(2,1,1), 0),
//                    TestCase(listOf(2,1,1,2), 1),
//                    TestCase(listOf(2,1,1,2,1), 1),
//                    TestCase(listOf(2,1,2,1,2,3,4), 1),
                    null
            ).filterNotNull()
        }

        fun noEqualElementsAreKOrLessApart(k: Int): Matcher<List<Int>> {
            return object : TypeSafeDiagnosingMatcher<List<Int>>() {

                override fun matchesSafely(list: List<Int>?,
                                           mismatchDescription: Description?): Boolean {
                    list?: return false
                    val elementToOccurrences = mutableMapOf<Int, MutableList<Int>>()
                    val indexValueOfViolation = list.withIndex().find { (index, element) ->
//                        println(listOf(index, element))
                        elementToOccurrences.run {
                            (get(element)?.run { index <= last() + k }?: false).also {
                                println(this.javaClass)
                                println(element)
                                elementToOccurrences[element] =
                                        getOrDefault(element, mutableListOf()).apply {
                                            add(index)
                                        }
                                println(elementToOccurrences)
                            }
                        }
                    }
//                    println(elementToOccurrences)
                    // TODO Finish making failure message as informative as possible
                    val matches = indexValueOfViolation == null
                    indexValueOfViolation?.let {
                        val value = indexValueOfViolation.value
                        elementToOccurrences[value]?.run {
                            // TODO Modify get() to accept negative index
                            val occurrence1 = get(index = -1)
                            val occurrence2 = last()
                            val distance = occurrence2 - occurrence1
                            mismatchDescription?.appendText(
                                    "value $value at " +
                                            "index $occurrence1 " +
                                            "and index $occurrence2 is $distance apart")
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

