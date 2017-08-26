package com.lagostout.elementsofprogramminginterviews.heaps

import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek
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
                subject.push(item)
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

    }
})