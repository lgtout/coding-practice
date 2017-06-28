package com.lagostout.elementsofprogramminginterviews.heaps

import com.lagostout.common.takeNotLast
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class MergeSortedFilesSpek : Spek({
    data class TestCase(val lists: List<List<Int>>) {
        val expected: List<Int> = lists.flatten().sorted()
        operator fun component2() = expected
    }
    describe("mergedSortedLists") {
        val testCases = listOf(
                TestCase(listOf(listOf(), listOf())),
                TestCase(listOf(listOf(1), listOf())),
                TestCase(listOf(listOf(1), listOf(1))),
                TestCase(listOf(listOf(1), listOf(2))),
                TestCase(listOf(listOf(1,2), listOf(1))),
                TestCase(listOf(listOf(2), listOf(1))),
                TestCase(listOf(listOf(2), listOf(1,2))),
                TestCase(listOf(listOf(2,4), listOf(3,5))),
                TestCase(listOf(listOf(2,4), listOf(3,5), listOf(3,7))),
                TestCase(listOf(listOf(2,7), listOf(3,4), listOf(5,6))),
                TestCase(listOf(listOf(), listOf()))
        )
        testCases.takeNotLast().forEach {
            (lists, expected) ->
            given("lists $lists") {
                it("merges the list as $expected") {
                    assertEquals(expected, mergeSortedLists(lists))
                }
            }

        }
    }
})

