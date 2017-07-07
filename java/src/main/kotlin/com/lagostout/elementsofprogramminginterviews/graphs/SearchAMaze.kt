package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

object SearchMaze {

    data class Point(val column: Int, val row: Int)
    data class Result(val path: List<Point>? = null, val graph: Map<Point, Set<Point>>? = null)

    fun findPathThroughMaze(
            grid: List<List<Boolean>>,
            entry: Point, exit: Point): Result {
        if (!containsOpenPixel(grid, entry) ||
                !containsOpenPixel(grid, exit)) return Result()
        data class Frame(val point: Point, val adjacentPoints: Iterator<Point>)
        val graph = toGraph(grid)
        val stackOfFrames = LinkedList<Frame>()
        stackOfFrames.push(
                Frame(point = entry, adjacentPoints = graph[entry]!!.iterator()))
        while (stackOfFrames.isNotEmpty() &&
                !(stackOfFrames.first().point == entry &&
                        stackOfFrames.last().point == exit)) {
            val adjacentPoints = stackOfFrames.peek().adjacentPoints
            if (adjacentPoints.hasNext()) {
                val adjacentPoint = adjacentPoints.next()
                stackOfFrames.push(
                        Frame(point = adjacentPoint,
                                adjacentPoints = graph[adjacentPoint]?.iterator() ?:
                                        emptyList<Point>().iterator()))
            } else {
                stackOfFrames.pop()
            }
        }
        return Result(stackOfFrames.map { it.point }, graph)
    }

    private fun containsOpenPixel(grid: List<List<Boolean>>, point: Point): Boolean {
        return (point.row < grid.size && point.column < grid[0].size && grid[point.row][point.column])
    }

    private fun toGraph(grid: List<List<Boolean>>): Map<Point, Set<Point>> {
        val adjacencies: MutableMap<Point, MutableSet<Point>> = mutableMapOf()
        grid.forEachIndexed { rowIndex, list ->
            list.forEachIndexed {
                columnIndex, isOpen ->
                if (isOpen) {
                    val point = Point(columnIndex, rowIndex)
                    val adjacentPoints = mutableSetOf<Point>()
                    adjacencies.put(point, adjacentPoints)
                    val previousRow = rowIndex - 1
                    if (previousRow >= 0) {
                        val previousRowPoint = Point(previousRow, columnIndex)
                        if (adjacencies.containsKey(previousRowPoint)) {
                            adjacencies[point]?.add(previousRowPoint)
                            adjacencies[previousRowPoint]?.add(point)
                        }
                    }
                    val previousColumn = columnIndex - 1
                    if (previousColumn >= 0) {
                        val previousColumnPoint = Point(rowIndex, previousColumn)
                        if (adjacencies.containsKey(previousColumnPoint)) {
                            adjacencies[point]?.add(previousColumnPoint)
                            adjacencies[previousColumnPoint]?.add(point)
                        }
                    }
                }
            }
        }
        return adjacencies
    }

}
