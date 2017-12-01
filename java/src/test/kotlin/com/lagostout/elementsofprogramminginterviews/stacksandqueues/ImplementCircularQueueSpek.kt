package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.api.lifecycle.CachingMode

object ImplementCircularQueueSpek : Spek({
    describe("CircularQueue") {
        val queue by memoized (CachingMode.TEST){ CircularQueue<Int>(5) }
        describe("empty queue") {
            on("get size") {
                val expected = 0
                it("returns $expected") {
                    assertThat(queue.size)
                            .isEqualTo(expected)
                }
            }
            on("enqueue an entry") {
                queue.enqueue(1)
                val expected = 1
                it("has size $expected") {
                    assertThat(queue.size)
                            .isEqualTo(expected)
                }
            }
        }
        describe("queue containing 1 entry") {
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