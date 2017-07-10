package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

object SearchMaze {

    data class Pixel(val column: Int, val row: Int)
    data class Result(val path: List<Pixel>? = null, val graph: Map<Pixel, Set<Pixel>>? = null)
    data class Frame(val pixel: Pixel, val adjacentPoints: Iterator<Pixel>)

    fun findPathThroughMaze(
            grid: List<List<Boolean>>,
            entry: Pixel, exit: Pixel): Result {
        if (!containsOpenPixel(grid, entry) ||
                !containsOpenPixel(grid, exit)) return Result()
        val graph = toGraph(grid)
        val stackOfFrames = LinkedList<Frame>()
        stackOfFrames.push(
                Frame(pixel = entry, adjacentPoints = graph[entry]!!.iterator()))
        while (stackOfFrames.isNotEmpty() &&
                !(stackOfFrames.first().pixel == exit &&
                        stackOfFrames.last().pixel == entry)) {
            val adjacentPoints = stackOfFrames.peek().adjacentPoints
            if (adjacentPoints.hasNext()) {
                val adjacentPoint = adjacentPoints.next()
                stackOfFrames.push(
                        Frame(pixel = adjacentPoint,
                                adjacentPoints = graph[adjacentPoint]!!.iterator()))
            } else {
                stackOfFrames.pop()
            }
        }
        return Result(stackOfFrames.map { it.pixel }.reversed(), graph)
    }

    private fun containsOpenPixel(grid: List<List<Boolean>>, pixel: Pixel): Boolean {
        return (pixel.row < grid.size && pixel.column < grid[0].size && grid[pixel.row][pixel.column])
    }

    private fun toGraph(grid: List<List<Boolean>>): Map<Pixel, Set<Pixel>> {
        val adjacencies: MutableMap<Pixel, MutableSet<Pixel>> = mutableMapOf()
        grid.forEachIndexed { rowIndex, list ->
            list.forEachIndexed {
                columnIndex, isOpen ->
                if (isOpen) {
                    val point = Pixel(columnIndex, rowIndex)
                    val adjacentPoints = mutableSetOf<Pixel>()
                    adjacencies.put(point, adjacentPoints)
                    val previousRow = rowIndex - 1
                    if (previousRow >= 0) {
                        val previousRowPoint = Pixel(previousRow, columnIndex)
                        if (adjacencies.containsKey(previousRowPoint)) {
                            adjacencies[point]?.add(previousRowPoint)
                            adjacencies[previousRowPoint]?.add(point)
                        }
                    }
                    val previousColumn = columnIndex - 1
                    if (previousColumn >= 0) {
                        val previousColumnPoint = Pixel(rowIndex, previousColumn)
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
