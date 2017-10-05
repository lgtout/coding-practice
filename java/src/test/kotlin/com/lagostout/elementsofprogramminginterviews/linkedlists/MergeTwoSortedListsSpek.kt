package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.elementsofprogramminginterviews.linkedlists.MergeTwoSortedLists.mergeTwoSortedLists
import com.lagostout.elementsofprogramminginterviews.linkedlists.MergeTwoSortedLists.ListNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class MergeTwoSortedListsSpek : Spek({
    describe("mergeTwoSortedLists") {
        testCases.forEach {
            (list1, list2, expectedMergedList) ->
            given("list1: $list1, list2: $list2") {
                it("returns $expectedMergedList") {
                    assertEquals(expectedMergedList, mergeTwoSortedLists(list1, list2))
                }
            }
        }
    }
}) {
    companion object {

        class TestCase(rawList1: List<Int> = emptyList(),
                       rawList2: List<Int> = emptyList()) {

            private val list1: ListNode<Int>? = toListNodes(rawList1)
            private val list2: ListNode<Int>? = toListNodes(rawList2)
            private val expectedMergedList: ListNode<Int>? =
                    toListNodes((rawList1 + rawList2).sorted())

            operator fun component1() = list1
            operator fun component2() = list2
            operator fun component3() = expectedMergedList

            companion object {
                private fun toListNodes(rawList: List<Int>): ListNode<Int>? {
                    return rawList.map {
                        ListNode(it)
                    }.also { list ->
                        (0..(list.lastIndex - 1)).forEach {
                            list[it].next = list[it + 1]
                        }
                    }.run {
                        if (isNotEmpty()) first() else null
                    }
                }
            }
        }

        val testCases = listOf(
                TestCase(),
                TestCase(rawList1 = listOf(1,2)),
                TestCase(rawList2 = listOf(1,2)),
                TestCase(listOf(1,2), listOf(1)),
                TestCase(listOf(1,3), listOf(2)),
                TestCase(listOf(2,3), listOf(1)),
                TestCase(listOf(1,2), listOf(3)),
                TestCase(listOf(1,2), listOf(1,2)),
                TestCase(listOf(1,2), listOf(3,4)),
                TestCase(listOf(3,4), listOf(1,2)),
                TestCase(listOf(1,4), listOf(2,3)),
                TestCase(listOf(1,3,5), listOf(2,4,6,7,8)),
                TestCase(listOf(1,3,5), listOf(1,2,3,5,5)),
                null).filterNotNull()
    }
}