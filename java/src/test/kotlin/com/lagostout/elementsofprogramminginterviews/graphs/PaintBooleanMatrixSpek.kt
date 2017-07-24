package com.lagostout.elementsofprogramminginterviews.graphs

import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

class PaintBooleanMatrixSpek : Spek({
    describe("flipRegionColor") {
        // TODO
        // If flip is white to black, verify by comparing components of white
        // in the result to those of white in the input matrix.  They should
        // be identical, except for the component in the first that contains
        // the start point.  And the graph should contain no additional
        // cells e.g. cells of the starting color.

        // Cases:
        // Empty grid
        // Point beyond grid bounds - positive/negative
    }
}) {
    companion object {

        data class TestCase(
                val grid: List<List<Boolean>>,
                val start: Point) {
            val expectedUnflippedPoints = {
                val graph = toGraph(grid, grid[start.row][start.column])
                val components = mutableSetOf<Set<Point>>()
//                TODO("continue here")
//                graph.forEach
            }()
        }

        val testCases = {
            val testCaseCount = 1000
            val random = RandomDataGenerator().apply { reSeed(1) }
            val testCases = mutableListOf<TestCase>()
            val gridDimensionRange = IntRange(0, 10)
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
        }()

    }
}