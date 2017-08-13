package com.lagostout.elementsofprogramminginterviews.graphs

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*

// TODO Graph with no black points

class ComputeBlackRegionContainingMostPointsSpek : Spek({
    describe("computeBlackRegionContainingMostPoints") {
        testCases.forEach {
            (grid, expectedRegionContainingMostPoints) ->
            given("grid $grid") {
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
                       pointInBlackRegionContainingMostPoints: Point) {
            private val expectedRegionContainingMostPoints = run {
                val graph = booleanMatrixToGraph(grid)
                val result = mutableSetOf<Point>()
                var adjacentVertices = mutableSetOf(pointInBlackRegionContainingMostPoints)
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
                result
            }
            operator fun component1() = grid
            operator fun component2() = expectedRegionContainingMostPoints
        }
        val testCases = listOf<TestCase>()
    }
}
