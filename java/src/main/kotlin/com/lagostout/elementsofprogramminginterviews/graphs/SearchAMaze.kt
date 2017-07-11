package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

/**
 * Problem 19.1 page 360
 */
object SearchMaze {

    data class Pixel(val column: Int, val row: Int)
    data class Result(val path: List<Pixel>, val graph: Map<Pixel, Set<Pixel>>)
    data class Frame(val pixel: Pixel, val adjacentPoints: Iterator<Pixel>)

    fun findPathThroughMaze(
            grid: List<List<Boolean>>,
            entry: Pixel, exit: Pixel): Result {
        if (!containsOpenPixel(grid, entry) ||
                !containsOpenPixel(grid, exit)) return Result(emptyList(), emptyMap())
        val graph = toGraph(grid)
        val stackOfFrames = LinkedList<Frame>()
        val pixelsInPath = mutableSetOf<Pixel>()
        stackOfFrames.push(
                Frame(pixel = entry, adjacentPoints = graph[entry]!!.iterator()))
        pixelsInPath.add(entry)
        while (stackOfFrames.isNotEmpty() &&
                !(stackOfFrames.first().pixel == exit &&
                        stackOfFrames.last().pixel == entry)) {
            val (_, adjacentPoints) = stackOfFrames.peek()
            if (adjacentPoints.hasNext()) {
                val adjacentPoint = adjacentPoints.next()
                if (pixelsInPath.contains(adjacentPoint)) continue
                pixelsInPath.add(adjacentPoint)
                stackOfFrames.push(
                        Frame(pixel = adjacentPoint,
                                adjacentPoints = graph[adjacentPoint]!!.iterator()))
            } else {
                val pixelNotInPath  = stackOfFrames.pop().pixel
                pixelsInPath.remove(pixelNotInPath)
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
                        val previousRowPoint = Pixel(columnIndex, previousRow)
                        if (adjacencies.containsKey(previousRowPoint)) {
                            adjacencies[point]?.add(previousRowPoint)
                            adjacencies[previousRowPoint]?.add(point)
                        }
                    }
                    val previousColumn = columnIndex - 1
                    if (previousColumn >= 0) {
                        val previousColumnPoint = Pixel(previousColumn, rowIndex)
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
