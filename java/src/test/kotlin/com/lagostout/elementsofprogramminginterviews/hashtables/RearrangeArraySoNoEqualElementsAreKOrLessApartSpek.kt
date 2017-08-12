package com.lagostout.elementsofprogramminginterviews.hashtables

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.TypeSafeDiagnosingMatcher
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

class RearrangeArraySoNoEqualElementsAreKOrLessApartSpek : Spek({
    describe("rearrangeArraySoNoEqualElementsAreKOrLessApart") {
        testCases.forEach {
            (array, k) ->
            given("an array $array and k $k") {
                it("rearranges the array so no equal " +
                        "elements are $k or less apart") {
                    assertThat(rearrangeArraySoNoEqualElementsAreKOrLessApart(array, k),
                            noEqualElementsAreKOrLessApart(k))
                }
            }
        }
    }
}) {
    companion object {

        data class TestCase(val array: List<Int> = emptyList(), val k: Int = 0)
        val testCases: List<TestCase> = run {
            listOf(
//                    TestCase(listOf()),
//                    TestCase(listOf(1), 0), // No two consecutive equal numbers
//                    TestCase(listOf(1), 1),
                    TestCase(listOf(1,2), 0),
//                    TestCase(listOf(1,2), 10),
                    null
            ).filterNotNull()
        }

        fun noEqualElementsAreKOrLessApart(k: Int): Matcher<List<Int>> {
            return object : TypeSafeDiagnosingMatcher<List<Int>>() {
                override fun matchesSafely(list: List<Int>?, mismatchDescription: Description?): Boolean {
                    list?: return false
                    val elementToLastOccurrenceIndex = mutableMapOf<Int, Int>()
                    val indexValueOfViolation = list.withIndex().find { (index, element) ->
                        elementToLastOccurrenceIndex.run {
                            val violatesConstraint =
                                    get(element)?.run { index <= this + k }?: false
                            this[element] = index
                            violatesConstraint
                        }
                    }
                    val matches = indexValueOfViolation == null
                    indexValueOfViolation?.let {
                        mismatchDescription?.appendText(
                                "violation found for ${indexValueOfViolation.value} at " +
                                        "index ${indexValueOfViolation.index}")
                    }
                    return matches
                }

                override fun describeTo(description: Description?) {
                    description?.appendText("have no equal elements $k or less apart")
                }

            }
        }
    }

}

