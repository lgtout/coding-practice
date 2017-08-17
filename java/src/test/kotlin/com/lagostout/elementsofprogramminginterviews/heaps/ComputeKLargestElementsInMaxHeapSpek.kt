package com.lagostout.elementsofprogramminginterviews.heaps

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import kotlin.test.assertFailsWith

class ComputeKLargestElementsInMaxHeapSpek : Spek({
    describe("kLargestElementsInMaxHeap") {
        testCases.forEach {
            (heap, k, expectedKLargestElements) ->
            given("heap $heap k $k") {
                it("returns $expectedKLargestElements") {
                    assertThat(kLargestElementsInMaxHeap(heap, k),
                            Matchers.contains(
                                    *expectedKLargestElements.toTypedArray()))
                }
            }
        }
        describe("Throws an IllegalArgumentException when k is negative") {
            val k = -1
            given("k $k") {
                it("throws an IllegalArgumentException") {
                    assertFailsWith<IllegalArgumentException> {
                        kLargestElementsInMaxHeap(listOf(1), k)
                    }
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val heap: List<Int> = emptyList(), val k: Int = 0) {
            operator fun component3() = heap.sorted().take(k)
        }
        val testCases = listOf(
                TestCase(),
                null).filterNotNull()
    }
}