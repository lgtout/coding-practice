package com.lagostout.elementsofprogramminginterviews.heaps

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class MedianOfOnlineDataSpek : Spek({
    describe("medianOfOnlineData") {
        testCases.forEach {
            (data, expectedMedian) ->
            given("data $data") {
                it("computes $expectedMedian as the median") {
                    assertEquals(expectedMedian?.toFloat(), medianOfOnlineData(data))
                }
            }
        }
    }
}) {
    data class TestCase(val data: List<Int> = emptyList(),
                        val expectedMedian: Number? = null)
    companion object {
        val testCases = run {
            listOf(listOf(
//                    TestCase(),
//                    TestCase(listOf(0), 0),
                    TestCase(listOf(0,1), 1),
//                    TestCase(listOf(1,0), 1),
//                    TestCase(listOf(1,2), 1.5),
//                    TestCase(listOf(2,1), 1.5),
//                    TestCase(listOf(1,0,0), 0),
//                    TestCase(listOf(0,1,1), 1),
//                    TestCase(listOf(1,1,0), 1),
                    null),
                    run {
                       listOf(null)
//                        emptyList<TestCase>()
//                        TODO("random generated cases")
                    }).flatten().filterNotNull().apply {println(this)}
        }
    }
}
