package com.lagostout.elementsofprogramminginterviews.heaps

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals
import com.lagostout.elementsofprogramminginterviews.heaps.MedianOfOnlineData.medians

class MedianOfOnlineDataSpek : Spek({
    describe("medianOfOnlineData") {
        testCases.forEach {
            (data, expectedMedians) ->
            given("data $data") {
                it("computes $expectedMedians as the median") {
                    assertEquals(expectedMedians, medians(data))
                }
            }
        }
    }
}) {
    data class TestCase(val data: List<Int> = emptyList(),
                        val expectedMedians: List<Double> = emptyList())
    companion object {
        val testCases = run {
            listOf(listOf(
                    TestCase(),
                    TestCase(listOf(0), listOf(0.0)),
                    TestCase(listOf(0,1), listOf(0.0,0.5)),
                    TestCase(listOf(1,0), listOf(1.0,0.5)),
                    TestCase(listOf(1,2), listOf(1.0,1.5)),
                    TestCase(listOf(2,1), listOf(2.0,1.5)),
                    TestCase(listOf(1,0,0), listOf(1.0,0.5,0.0)),
                    TestCase(listOf(0,1,1), listOf(0.0,0.5,1.0)),
                    TestCase(listOf(1,1,0), listOf(1.0,1.0,1.0)),
                    null)).flatten().filterNotNull().apply { println(this) }
        }
    }
}
