@file:Suppress("NAME_SHADOWING")

package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object ImplementQueueUsingStacksSpek : Spek({
    describe("Queue") {
        val queue by memoized { Queue<Int>() }
        describe("queue is empty") {
            val expectedSize = 0
            it("has size $expectedSize") {
                assertThat(queue.size).isEqualTo(expectedSize)
            }
            on("enqueue an entry") {
                val expectedSize = 1
                val addedEntry = 111
                queue.enqueue(addedEntry)
                it("has size $expectedSize") {
                    assertThat(queue.size).isEqualTo(expectedSize)
                }
            }
        }
        describe("queue has entries enqueued") {
            val expectedEntries = listOf(1,2,3)
            beforeEachTest {
                expectedEntries.forEach {
                    queue.enqueue(it)
                }
            }
            on("enqueue an entry") {
                val expectedSize = queue.size + 1
                val entry = 4
                queue.enqueue(entry)
                it("has size $expectedSize") {
                    assertThat(queue.size).isEqualTo(expectedSize)
                }
            }
            on("dequeue all entries") {
                val dequeuedEntries = mutableListOf<Int>()
                while (queue.isNotEmpty()) {
                    dequeuedEntries.add(queue.dequeue())
                }
                it("dequeues entries in FIFO order") {
                    assertThat(dequeuedEntries)
                            .isEqualTo(expectedEntries)
                }
                val expectedSize = 0
                it("has size $expectedSize") {
                    assertThat(queue.size).isEqualTo(expectedSize)
                }
            }
        }
    }
})