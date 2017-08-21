package com.lagostout.elementsofprogramminginterviews.linkedlists

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

class MergeTwoSortedListsSpek : Spek({
    describe("") {
        testCases.forEach {
            given("") {
                it("") {

                }
            }
        }
    }
}) {
    companion object {

        data class TestCase(val rawList1: List<Int> = emptyList(),
                            val rawList2: List<Int> = emptyList(),
                            val rawExpectedMergedList: List<Int> = emptyList()) {
            val list1: ListNode<Int>? = Companion.toListNodes(rawList1)
            val list2: ListNode<Int>? = Companion.toListNodes(rawList2)
            val expectedMergedList: ListNode<Int>? =
                    Companion.toListNodes(rawList1 + rawList2)

            companion object {
                private fun toListNodes(rawList: List<Int>): ListNode<Int> {
                    return rawList.map {
                        ListNode(it)
                    }.also { list ->
                        (0..(list.lastIndex - 1)).forEach {
                            list[it].next = list[it + 1]
                        }
                    }.first()
                }
            }
        }

        val testCases = listOf(
                TestCase(),
                TestCase(),
                null).filterNotNull()
    }
}