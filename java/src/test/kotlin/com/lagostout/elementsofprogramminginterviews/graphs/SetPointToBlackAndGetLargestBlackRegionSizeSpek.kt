package com.lagostout.elementsofprogramminginterviews.graphs

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class SetPointToBlackAndGetLargestBlackRegionSizeSpek : Spek({
    describe("setToBlackAndGetLargestBlackRegionSize") {
        testCases.forEach {
            (graph, operations) ->
            val setPointToBlack = SetPointToBlackAndGetLargestBlackRegionSize(graph)
            given("graph $graph") {
                operations.forEach {
                    (point, expectedLargestBlackRegionSize) ->
                    given("point: $point") {
                        it("returns $expectedLargestBlackRegionSize") {
                            assertEquals(
                                    expectedLargestBlackRegionSize,
                                    setPointToBlack
                                            .setToBlackAndGetLargestBlackRegionSize(point))
                        }
                    }
                }
            }
        }
    }
}) {
    companion object {
        data class ToBlack(val point: Point,
                           val largestBlackRegionSize: Int = 0)
        class TestCase(private val graph: List<List<Boolean>> = emptyList(),
                       private val operations: List<ToBlack> = emptyList()) {
            operator fun component1() = graph
            operator fun component2() = operations
        }
        private val T = true
        private val F = false
        val testCases = listOf(
                TestCase(listOf(
                        listOf(F,F,F),
                        listOf(F,F,F),
                        listOf(F,F,F),
                        listOf(F,F,F),
                        listOf(F,F,F)),
                        listOf(
                                ToBlack(Point(1,0), 1),
                                ToBlack(Point(1,0), 1),
                                ToBlack(Point(0,1), 1),
                                ToBlack(Point(2,0), 2),
                                ToBlack(Point(0,3), 2),
                                ToBlack(Point(0,2), 3),
                                ToBlack(Point(2,1), 3),
                                ToBlack(Point(2,2), 4),
                                ToBlack(Point(0,0), 8))),
                TestCase(listOf(
                        listOf(F,F,F),
                        listOf(F,T,F),
                        listOf(F,T,F),
                        listOf(F,F,F),
                        listOf(F,F,F)),
                        listOf(
                                ToBlack(Point(1,4), 1),
                                ToBlack(Point(1,3), 4))),
                null).filterNotNull()
    }
}