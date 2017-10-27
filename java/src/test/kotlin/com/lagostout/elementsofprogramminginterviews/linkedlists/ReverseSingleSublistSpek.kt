package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.common.takeFrom
import com.lagostout.elementsofprogramminginterviews.linkedlists.ReverseSingleSublist.reverseSingleSublist
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

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
        fun toLinkedList(list: List<Int>): ListNode<Int> {
            val head = ListNode(list.first())
            var tail = head
            list.takeFrom(1).forEach {
                val node = ListNode(it)
                tail.next = node
                tail = node
            }
            return head
        }
        describe("valid start and end positions") {
            val data = listOf(
                    data(1, 1, toLinkedList(listOf(1)),
                            expected = toLinkedList(listOf(1))),
                    data(2, 2, toLinkedList(listOf(1, 2, 3)),
                            expected = toLinkedList(listOf(1, 2, 3))),
                    data(1, 2, toLinkedList(listOf(1, 2, 3)),
                            expected = toLinkedList(listOf(2, 1, 3))),
                    data(2, 3, toLinkedList(listOf(1, 2, 3)),
                            expected = toLinkedList(listOf(1, 3, 2))),
                    data(3, 2, toLinkedList(listOf(1, 2, 3)),
                            expected = toLinkedList(listOf(1, 3, 2))),
                    data(2, 4, toLinkedList(listOf(1, 2, 3, 4, 5)),
                            expected = toLinkedList(listOf(1, 4, 3, 2, 5))),
                    null
            ).filterNotNull().toTypedArray()
            on("start: %s end: %s list: %s", with = *data) { start, end, list, expected ->
                it("returns $expected") {
                    val head = ReverseSingleSublist.reverseSingleSublist(list, start, end)
                    assertEquals(expected, head)
                }
            }
        }
        describe("invalid start position") {
            val start = 0
            val end = 1
            val list = toLinkedList(listOf(1))
            given("start: $start, end: $end, list: $list") {
                it("throws exception") {
                    assertFailsWith<IllegalArgumentException> {
                        reverseSingleSublist(list, start, end)
                    }
                }
            }
        }
        describe("invalid end position") {
            val start = 1
            val end = 2
            val list = toLinkedList(listOf(1))
            given("start: $start, end: $end, list: $list") {
                it("throws exception") {
                    assertFailsWith<IllegalArgumentException> {
                        reverseSingleSublist(list, start, end)
                    }
                }
            }

        }
    }
})
