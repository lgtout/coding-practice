package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import org.assertj.core.api.Assertions.assertThat
import org.funktionale.composition.andThen
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*

object ImplementCircularQueueSpek : Spek({
    describe("CircularQueue") {
        val arraysCreated by memoized { mutableListOf<Array<Int?>>() }
        val arrayFactory by memoized {
            arrayFactory<Int>() andThen {
                println(it)
                arraysCreated.add(it)
                it
            }
        }
        val queue by memoized { circularQueue(arrayFactory = arrayFactory) }
        describe("empty queue with 0 initial size") {
            on("get size") {
                val expectedQueueSize = 0
                it("returns $expectedQueueSize") {
                    assertThat(queue.size)
                            .isEqualTo(expectedQueueSize)
                }
                xit("creates an empty backing array") {
                    assertThat(arraysCreated.size)
                            .isEqualTo(1)
                    assertThat(arraysCreated.first().size)
                            .isEqualTo(0)
                }
            }
            xon("enqueue an entry") {
                val expectedSize = 1
                val expectedBackingArraySize = 1
                val entry = 111
                queue.enqueue(entry)
                it("has size $expectedSize") {
                    assertThat(queue.size)
                            .isEqualTo(expectedSize)
                }
                it("creates a second backing array of size " +
                        "$expectedBackingArraySize") {
                    assertThat(arraysCreated.size)
                            .isEqualTo(2)
                    assertThat(arraysCreated[1].size)
                            .isEqualTo(entry)
                }
            }
        }
        xdescribe("queue containing 1 entry") {
            beforeGroup {
                queue.enqueue(1)
            }
            on("enqueue another entry") {
                queue.enqueue(1)
                val expected = 2
                it("has size $expected") {
                    assertThat(queue.size)
                            .isEqualTo(expected)
                }
            }
        }
    }
    // TODO Continue
})