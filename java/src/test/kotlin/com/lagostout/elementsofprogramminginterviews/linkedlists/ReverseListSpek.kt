package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.common.takeFrom
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

class ReverseListSpek : Spek({
    describe("reverseSingleSublist()") {
        fun toLinkedList(list: List<Int>): LinkedListNode<Int> {
            val head = LinkedListNode(list.first())
            var tail = head
            list.takeFrom(1).forEach {
                val node = LinkedListNode(it)
                tail.next = node
                tail = node
            }
            return head
        }
        val data = listOf(
                data(toLinkedList(listOf(1)),
                        expected = toLinkedList(listOf(1))),
                data(toLinkedList(listOf(1, 2)),
                        expected = toLinkedList(listOf(2,1))),
                data(toLinkedList(listOf(1, 2, 3)),
                        expected = toLinkedList(listOf(3, 2, 1))),
                null
        ).filterNotNull().toTypedArray()
        on("list: %s", with = *data) { list, expected ->
            it("returns $expected") {
                val head = reverseList(list)
                assertEquals(expected, head)
            }
        }
    }
})
