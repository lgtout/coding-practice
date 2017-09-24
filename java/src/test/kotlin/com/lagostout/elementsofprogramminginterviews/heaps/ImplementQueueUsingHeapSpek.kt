package com.lagostout.elementsofprogramminginterviews.heaps

import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

typealias QueueUsingHeap = ImplementQueueUsingHeap.QueueUsingHeap<Int>

class ImplementQueueUsingHeapSpek : SubjectSpek<QueueUsingHeap>({
    subject {
        QueueUsingHeap()
    }
    describe("QueueUsingHeap") {
        describe("empty queue") {
            describe("add") {
                val item = 1
                beforeEachTest {
                    subject.add(item)
                }
                it("adds $item to the top of the heap") {
                    assertEquals(item, subject.heap.element()?.data)
                    assertEquals(1, subject.heap.size)
                }
            }
            describe("remove") {
                it("throws an exception") {
                    assertFailsWith<NoSuchElementException> {
                        subject.remove()
                    }
                }
            }
        }
        val items = listOf(1,2,3)
        describe("stack with multiple items ($items) added") {
            beforeEachTest {
                items.forEach { subject.add(it) }
            }
            it("contains items in FIFO order") {
                val heapItems = drainAsOrderedList(subject.heap)
                assertEquals(items, heapItems)
            }
            describe("remove once and add value 5") {
                it("contains items in FIFO order") {
                    subject.remove()
                    subject.add(5)
                    val heapItems = drainAsOrderedList(subject.heap)
                    assertEquals(listOf(2,3,5), heapItems)
                }
            }
        }
    }
}) {
    companion object {
        fun drainAsOrderedList(queue: PriorityQueue<
                ImplementQueueUsingHeap.HeapItem<Int>>): List<Int> {
            val items = mutableListOf<Int>()
            with(queue) {
                while (isNotEmpty()) {
                    items.add(remove().data)
                }
            }
            return items
        }
    }
}
