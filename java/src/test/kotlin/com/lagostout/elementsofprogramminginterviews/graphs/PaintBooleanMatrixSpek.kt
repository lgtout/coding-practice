package com.lagostout.elementsofprogramminginterviews.graphs

import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class PaintBooleanMatrixSpek : Spek({
    describe("flipRegionColor") {
        testCases.forEach {
            (grid, start, expectedUnflippedPoints) ->
            given("start point $start") {
                it("flips color of region containing start point") {
                    assertEquals(expectedUnflippedPoints,
                            pointsWithColor(grid, grid[start.row][start.column]))
                }
            }
        }
    }
}) {
    companion object {

        fun pointsWithColor(grid: List<List<Boolean>>, color: Boolean): Set<Point> {
            val points = mutableSetOf<Point>()
            0.rangeTo(grid.size - 1).forEach { row ->
                0.rangeTo(grid[row].size - 1).forEach { column ->
                    if (grid[row][column] == color)
                        points.add(Point(row, column))
                }
            }
            return points
        }

        data class TestCase(
                val grid: List<List<Boolean>>,
                val start: Point) {
            val expectedUnflippedPoints = run {
                val graph = toGraph(grid, grid[start.row][start.column])
                val components = mutableSetOf<Set<Point>>()
                val exploredPoints = mutableSetOf<Point>()
                var currentComponent: MutableSet<Point>
                graph.keys.forEach {
                    point ->
                    if (exploredPoints.contains(point)) return@forEach
                    var points = mutableSetOf<Point>().apply { add(point) }
                    currentComponent = mutableSetOf<Point>()
                    while (points.isNotEmpty()) {
                        currentComponent.addAll(points)
                        val nextPoints = mutableSetOf<Point>()
                        points.forEach {
                            nextPoints.addAll(graph[it]?: emptySet())
                        }
                        points = nextPoints
                    }
                    components.add(currentComponent)
                    exploredPoints.addAll(currentComponent)
                }
                components.filterNot { it.contains(start) }
                        .flatMap { it }.toSet()
            }
            operator fun component3() = expectedUnflippedPoints
        }

        val testCases = run {
            val testCaseCount = 10
            val random = RandomDataGenerator().apply { reSeed(1) }
            val testCases = mutableListOf<TestCase>()
            val gridDimensionRange = IntRange(1, 10)
            1.rangeTo(testCaseCount).forEach {
                val grid = mutableListOf<MutableList<Boolean>>()
                val rowCount = random.nextInt(gridDimensionRange)
                val columnCount = random.nextInt(gridDimensionRange)
                1.rangeTo(rowCount).forEach {
                    val row = mutableListOf<Boolean>()
                    grid.add(row)
                    1.rangeTo(columnCount).forEach {
                        row.add(random.nextInt(0, 1) == 1)
                    }
                }
                val start = Point(random.nextInt(0, rowCount - 1),
                        random.nextInt(0, columnCount - 1))
                testCases.add(TestCase(grid, start))
            }
            testCases
        }

    }
}