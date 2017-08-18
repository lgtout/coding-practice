package com.lagostout.elementsofprogramminginterviews.graphs

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*

class ComputeBlackRegionContainingMostPointsSpek : Spek({
    describe("computeBlackRegionContainingMostPoints") {
        testCases.forEachIndexed {
            index, (grid, expectedRegionContainingMostPoints) ->
            given("#${index + 1} grid $grid") {
               it("returns expectedRegion $expectedRegionContainingMostPoints") {
                    assertThat(computeBlackRegionContainingMostPoints(grid),
                            containsInAnyOrder(*expectedRegionContainingMostPoints.toTypedArray()))
               }
            }
        }
    }
}) {
    companion object {
        class TestCase(private val grid: List<List<Boolean>>,
                       pointInBlackRegionContainingMostPoints: Point? = null) {
            private val expectedRegionContainingMostPoints = run {
                val graph = booleanMatrixToGraph(grid)
                val result = mutableSetOf<Point>()
                var adjacentVertices = mutableSetOf<Point>()
                pointInBlackRegionContainingMostPoints?.apply {
                    adjacentVertices.add(this)
                }
                while (adjacentVertices.isNotEmpty()) {
                    result.addAll(adjacentVertices)
                    adjacentVertices = adjacentVertices.fold(mutableSetOf()) {
                        acc, point ->
                        acc.apply {
                            addAll(graph[point]
                                    // Need to filter because graph maintains
                                    // 2 edges for each pair of adjacent vertices.
                                    ?.filterNot { result.contains(it) }
                                    ?: emptySet())
                        }
                    }
                }
                result.toSet()
            }
            operator fun component1() = grid
            operator fun component2() = expectedRegionContainingMostPoints
        }
        // TODO Empty graph case?
        val testCases = run {
            val T = true
            val F = false
            listOf(
                    TestCase(listOf(
                            listOf(F,F,F),
                            listOf(F,F,F),
                            listOf(F,F,F),
                            listOf(F,F,F),
                            listOf(F,F,F)), null),
                    TestCase(listOf(
                            listOf(F,F,F),
                            listOf(F,T,F),
                            listOf(F,F,F),
                            listOf(F,F,F),
                            listOf(F,F,F)), Point(1,1)),
                    TestCase(listOf(
                            listOf(F,F,F),
                            listOf(F,T,F),
                            listOf(F,F,F),
                            listOf(F,F,F),
                            listOf(F,F,F)), Point(1,1)),
                    TestCase(listOf(
                            listOf(F,F,F),
                            listOf(F,T,F),
                            listOf(F,F,F),
                            listOf(T,T,F),
                            listOf(F,F,F)), Point(0,3)),
                    TestCase(listOf(
                            listOf(F,F,F),
                            listOf(F,T,F),
                            listOf(F,F,F),
                            listOf(T,T,F),
                            listOf(F,F,T)), Point(0,3)),
                    null).filterNotNull()
        }
    }
}
