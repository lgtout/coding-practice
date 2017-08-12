package com.lagostout.elementsofprogramminginterviews.hashtables

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.TypeSafeDiagnosingMatcher
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import java.util.*

class RearrangeArraySoNoEqualElementsAreKOrLessApartSpek : Spek({
    describe("rearrangeArraySoNoEqualElementsAreKOrLessApart") {
        testCases.forEach {
            (array, k) ->
            given("an array $array and k $k") {
                it("rearranges the array so no equal " +
                        "elements are $k or less apart") {
                    var result = rearrangeArraySoNoEqualElementsAreKOrLessApart(array, k)
                    // TODO Remove when done testing exception-throwing cases
                    result = listOf(1,1,2)
                    assertThat(result, noEqualElementsAreKOrLessApart(k))
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
                    // TODO: Test cases that throw exceptions.
                    // Implement as separate test methods. Only need two.
//                    TestCase(listOf()),
//                    TestCase(listOf(1), 0), // No two consecutive equal numbers
//                    TestCase(listOf(1), 1),
//                    TestCase(listOf(1,2), 0),
//                    TestCase(listOf(1,2), 1),
                    TestCase(listOf(1,1,2), 1),
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
                        elementToOccurrences.run {
                            val violatesConstraint =
                                    get(element)?.run { index <= last() + k }?: false
                            this[element]?.add(index)
                            violatesConstraint
                        }
                    }
                    // TODO Finish making failure message as informative as possible
                    val matches = indexValueOfViolation == null
                    indexValueOfViolation?.let {
                        val value = indexValueOfViolation.value
                        elementToOccurrences[value]?.run {
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

