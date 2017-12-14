package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import org.assertj.core.api.Assertions.assertThat
import org.funktionale.composition.andThen
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.api.dsl.xon

object ImplementCircularQueueSpek : Spek({
    describe("CircularQueue") {
        val state by memoized {
            object {
                val arraysCreated = mutableListOf<Array<Int?>>()
                val arrayFactory = arrayFactory<Int>() andThen {
                    arraysCreated.add(it)
                    it
                }
                val queue = circularQueue(arrayFactory = arrayFactory)
            }
        }
        describe("empty queue with 0 initial size") {
            xon("get size") {
                val expectedQueueSize = 0
                val expectedBackingArraysCount = 1
                val expectedBackingArraySize = 0
                it("returns $expectedQueueSize") {
                    assertThat(state.queue.size)
                            .isEqualTo(expectedQueueSize)
                }
                it("has a backing array") {
                    assertThat(state.arraysCreated.size)
                            .isEqualTo(expectedBackingArraysCount)
                }
                it("has backing array of size $expectedBackingArraySize") {
                    assertThat(state.arraysCreated.last().size)
                            .isEqualTo(expectedBackingArraySize)
                }
            }
            on("enqueue an entry") {
                val expectedSize = 1
                val expectedBackingArraysCount = 2
                val entry = 111
                val expectedBackingArray = arrayOf(entry, null)
                state.queue.enqueue(entry)
                it("has size $expectedSize") {
                    assertThat(state.queue.size).isEqualTo(expectedSize)
                }
                it("has created a new backing array") {
                    assertThat(state.arraysCreated.size)
                            .isEqualTo(expectedBackingArraysCount)
                }
                it("has backing array of size ${expectedBackingArray.size}") {
                    assertThat(state.arraysCreated.last().size)
                            .isEqualTo(expectedBackingArray.size)
                }
                it("has a backing array that contains the new entry") {
                    assertThat(state.arraysCreated.last())
                            .contains(entry)
                }
                it("puts the entry in the first position of its backing array") {
                    println(state.arraysCreated.map { it.toList() })
                    assertThat(state.arraysCreated.last())
                            .isEqualTo(expectedBackingArray)
                }
            }
        }
        describe("queue containing 4 entries") {
            val entries = listOf(111, 222, 333, 444)
            beforeEachTest {
                entries.forEach {
                    state.queue.enqueue(it)
                    println(state.arraysCreated.map { it.toList() })
                }
            }
            val expectedBackingArraysCount = 3
            val expectedBackingArraySize = 4
            val expectedQueueSize = 4
            it("has created $expectedBackingArraysCount backing arrays") {
                assertThat(state.arraysCreated.size)
                        .isEqualTo(expectedBackingArraysCount)
            }
            it("has backing array of size $expectedBackingArraySize") {
                assertThat(state.arraysCreated.last().size)
                        .isEqualTo(expectedBackingArraySize)
            }
            it("has size $expectedQueueSize") {
                assertThat(state.queue.size)
                        .isEqualTo(expectedQueueSize)
            }
            @Suppress("NAME_SHADOWING")
            on("dequeue") {
                val expectedQueueSize = 3
                val expectedBackingArray = entries.toTypedArray<Int?>().apply {
                    set(0, null)
                }
                val entry = state.queue.dequeue()
                it("dequeues entry $entry") {
                    assertThat(entry).isEqualTo(entries.first())
                }
                it("has size $expectedQueueSize") {
                    assertThat(state.queue.size).isEqualTo(expectedQueueSize)
                }
                it("does not create a new backing array") {
                    assertThat(state.arraysCreated.size)
                            .isEqualTo(expectedBackingArraysCount)
                }
                it("has a backing array that contains all entries except the dequeued one") {
                    assertThat(state.arraysCreated.last())
                            .isEqualTo(expectedBackingArray)
                }
            }
        }
    }
    // TODO Continue
})