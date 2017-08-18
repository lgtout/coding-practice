package com.lagostout.elementsofprogramminginterviews.heaps

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.jetbrains.spek.api.dsl.xdescribe
import kotlin.test.assertFailsWith

class ComputeKLargestElementsInMaxHeapSpek : Spek({
    describe("kLargestElementsInMaxHeap") {
        testCases.forEach {
            (heap, k, expectedKLargestElements) ->
            given("heap $heap k $k") {
                it("returns $expectedKLargestElements") {
                    val largestElements = kLargestElementsInMaxHeap(heap, k)
                    if (expectedKLargestElements.isEmpty()) {
                        assertThat(largestElements, empty())
                    } else {
                        assertThat(largestElements,
                                containsInAnyOrder(*expectedKLargestElements.toTypedArray()))
                    }
                }
            }
        }
        xdescribe("Throws an IllegalArgumentException when k is negative") {
            val k = -1
            given("k $k") {
                it("throws exception") {
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
//                TestCase(),
//                TestCase(k = 1),
//                TestCase(listOf(2,1)),
//                TestCase(listOf(2,1), 3),
//                TestCase(listOf(10,8,9,4,5,6,7), 7),
                TestCase(listOf(10,8,9,4,5,6,7), 5),
                null).filterNotNull()
    }
}