package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.common.takeFrom
import org.jetbrains.spek.api.Spek
import com.lagostout.elementsofprogramminginterviews.linkedlists.ReverseSingleSublist.ListNode
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

// Cases:
// Empty list
// Single item list
// Descending range
// Start/end outside range of list
// Single item lists
// Multiple items, single item sublist
// Sublist includes first or last node

class ReverseSingleSublistSpek : Spek({
    describe("reverseSingleSublist") {
        testCases.forEach { (start, end, list, expected) ->
            // TODO Will value output for list be pre or post reversal?
            given("start: $start end: $end list: $list") {
                it("returns $expected") {
                    ReverseSingleSublist.reverseSingleSublist(list, start, end)
                    assertEquals(expected, list)
                }
            }
        }
    }
}) {
    companion object {
        class TestCase(list: List<Int> = emptyList(),
                       private val start: Int = 1,
                       private val end: Int = 1,
                       expected: List<Int> = emptyList()) {
            private fun toLinkedList(list: List<Int>): ListNode<Int> {
                val head = ListNode(list.first())
                var tail = head
                list.takeFrom(1).forEach {
                    val node = ListNode(it)
                    tail.next = node
                    tail = node
                }
                return head
            }
            private val linkedList: ListNode<Int> = toLinkedList(list)
            private val expectedLinkedList: ListNode<Int> = toLinkedList(expected)
            operator fun component1() = start
            operator fun component2() = end
            operator fun component3() = linkedList
            operator fun component4() = expectedLinkedList
        }
        val testCases = listOf(
                TestCase(),
                null
        ).filterNotNull()
    }
}