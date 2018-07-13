package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object ImplementQueueWithMaxAPISpek : Spek({

    describe("QueueWithMaxAPI") {

        val queue by memoized { QueueWithMaxAPI() }

        describe("queue enqueue/dequeue") {
            val entries = listOf(1, 2, 3)
            on("enqueue $entries") {
                entries.forEach { queue.enqueue(it) }
                it("dequeues $entries") {
                    val dequeuedEntries = (0 until entries.count()).map {
                        queue.dequeue()
                    }
                    assertThat(dequeuedEntries)
                            .containsExactlyElementsOf(entries)
                }
            }
        }

        Pair(1, 1).let { (entry, expected) ->
            describe("enqueued with $entry") {
                on("max") {
                    queue.enqueue(entry)
                    val max = queue.max()
                    it("should return $expected") {
                        assertThat(max).isEqualTo(expected)
                    }
                }
            }
        }

        Pair(listOf(1, 2), 2).let { (entries, expected) ->
            describe("enqueued with $entries") {
                on("max") {
                    entries.forEach {
                        queue.enqueue(it)
                    }
                    val max = queue.max()
                    it("should return $expected") {
                        assertThat(max).isEqualTo(expected)
                    }
                }
            }
        }

        Pair(listOf(1, 2, 3), 3).let { (entries, expected) ->
            describe("enqueued with $entries") {
                on("max") {
                    entries.forEach {
                        queue.enqueue(it)
                    }
                    val max = queue.max()
                    it("should return $expected") {
                        assertThat(max).isEqualTo(expected)
                    }
                }
            }
        }

        Pair(listOf(3, 2, 1), 3).let { (entries, expected) ->
            describe("enqueued with $entries") {
                on("max") {
                    entries.forEach {
                        queue.enqueue(it)
                    }
                    val max = queue.max()
                    it("should return $expected") {
                        assertThat(max).isEqualTo(expected)
                    }
                }
            }
        }

        Triple(listOf(1, 2, 3), 1, 3).let { (entries, dequeueCount, expected) ->
            describe("enqueued with $entries, then dequeued x$dequeueCount") {
                on("max") {
                    entries.forEach {
                        queue.enqueue(it)
                    }
                    (1..dequeueCount).forEach { queue.dequeue() }
                    val max = queue.max()
                    it("should return $expected") {
                        assertThat(max).isEqualTo(expected)
                    }
                }
            }
        }

        Triple(listOf(1, 2, 3), 2, 3).let { (entries, dequeueCount, expected) ->
            describe("enqueued with $entries, then dequeued x$dequeueCount") {
                on("max") {
                    entries.forEach {
                        queue.enqueue(it)
                    }
                    (1..dequeueCount).forEach { queue.dequeue() }
                    val max = queue.max()
                    it("should return $expected") {
                        assertThat(max).isEqualTo(expected)
                    }
                }
            }
        }

        Triple(listOf(1, 2, 3, 1, 2), 4, 2).let { (entries, dequeueCount, expected) ->
            describe("enqueued with $entries, then dequeued x$dequeueCount") {
                beforeEachTest {
                    entries.forEach {
                        queue.enqueue(it)
                    }
                }
                on("max") {
                    (1..dequeueCount).forEach { queue.dequeue() }
                    val max = queue.max()
                    it("should return $expected") {
                        assertThat(max).isEqualTo(expected)
                    }
                }
            }
        }

        Triple(listOf(5, 4, 3, 1, 2), 1, 4).let { (entries, dequeueCount, expected) ->
            describe("enqueued with $entries, then dequeued x$dequeueCount") {
                beforeEachTest {
                    entries.forEach {
                        queue.enqueue(it)
                    }
                }
                on("max") {
                    (1..dequeueCount).forEach { queue.dequeue() }
                    val max = queue.max()
                    it("should return $expected") {
                        assertThat(max).isEqualTo(expected)
                    }
                }
            }
        }

        Triple(listOf(5, 4, 3, 1, 2), 2, 3).let { (entries, dequeueCount, expected) ->
            describe("enqueued with $entries, then dequeued x$dequeueCount") {
                beforeEachTest {
                    entries.forEach {
                        queue.enqueue(it)
                    }
                }
                on("max") {
                    (1..dequeueCount).forEach { queue.dequeue() }
                    val max = queue.max()
                    it("should return $expected") {
                        assertThat(max).isEqualTo(expected)
                    }
                }
            }
        }

        Triple(listOf(5, 4, 3, 1, 2), 3, 2).let { (entries, dequeueCount, expected) ->
            describe("enqueued with $entries, then dequeued x$dequeueCount") {
                beforeEachTest {
                    entries.forEach {
                        queue.enqueue(it)
                    }
                }
                on("max") {
                    (1..dequeueCount).forEach { queue.dequeue() }
                    val max = queue.max()
                    it("should return $expected") {
                        assertThat(max).isEqualTo(expected)
                    }
                }
            }
        }

        listOf(5, 4, 3, 1, 2).let { entries ->
            val dequeueCount = 2
            val entriesToEnqueueAfterDequeue = listOf(6,2)
            val expected = 6
            describe("enqueued with $entries, then dequeued x$dequeueCount, then enqueued with $entriesToEnqueueAfterDequeue") {
                beforeEachTest {
                    entries.forEach {
                        queue.enqueue(it)
                    }
                    (1..dequeueCount).forEach { queue.dequeue() }
                    entriesToEnqueueAfterDequeue.forEach {
                        queue.enqueue(it)
                    }
                }
                on("max") {
                    val max = queue.max()
                    it("should return $expected") {
                        assertThat(max).isEqualTo(expected)
                    }
                }
            }
        }
    }

})
