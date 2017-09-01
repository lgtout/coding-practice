package com.lagostout.elementsofprogramminginterviews.heaps

import com.lagostout.elementsofprogramminginterviews.heaps.StackUsingHeap.PriorityQueueItem
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ImplementStackUsingHeapSpek : SubjectSpek<StackUsingHeap<Int>>({
    subject {
        StackUsingHeap()
    }
    describe("StackUsingHeap") {
        describe("empty stack") {
            describe("push") {
                val item = 1
                beforeEachTest {
                    subject.push(item)
                }
                it("adds $item to the top of the heap") {
                    assertEquals(item, subject.heap.element()?.value)
                    assertEquals(1, subject.heap.size)
                }
            }
            describe("pop") {
                it("throws an exception") {
                    assertFailsWith<NoSuchElementException> {
                        subject.pop()
                    }
                }
            }
        }
        val items = listOf(1,2,3)
        describe("stack with multiple items ($items) pushed") {
            beforeEachTest {
                items.forEach { subject.push(it) }
            }
            it("contains items in LIFO order") {
                val heapItems = drainAsOrderedList(subject.heap)
                assertEquals(items.reversed(), heapItems)
            }
            describe("pop once and push value 5") {
                it("contains items in LIFO order") {
                    subject.pop()
                    subject.push(5)
                    val heapItems = drainAsOrderedList(subject.heap)
                    assertEquals(listOf(5,2,1), heapItems)
                }
            }
        }
    }
}) {
    companion object {
        fun drainAsOrderedList(queue: PriorityQueue<PriorityQueueItem<Int>>): List<Int> {
            val items = mutableListOf<Int>()
            with(queue) {
                while (isNotEmpty()) {
                    items.add(remove().value)
                }
            }
            return items
        }
    }
}