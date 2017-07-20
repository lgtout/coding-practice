package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

object PaintBooleanMatrix {

    fun flipRegionColor(grid: List<List<Boolean>>, start: Point):
            List<List<Boolean>> {
        if (start.row < 0 ||
                start.column < 0 ||
                start.row >= grid.size ||
                start.column >= grid[start.row].size)
            throw IllegalArgumentException("Point must be contained within grid")
        val result = mutableListOf<MutableList<Boolean>>()
        grid.forEach {
            result.add(it.toMutableList())
        }
        val startCellColor = grid[start.row][start.column]
        val flippedColor = !startCellColor
        val graph = toGraph(grid, startCellColor)
        val queue = LinkedList<Set<Point>>()
        queue.add(setOf(start))
        while (queue.isNotEmpty()) {
            val points = queue.remove()
            val nextPoints = points.map { graph[it]!! }
                    .fold(mutableSetOf<Point>()) {
                        nextPoints, points -> nextPoints.apply { addAll(points) } }
            queue.add(nextPoints)
            // Flip colors
            points.forEach {
                result[it.row][it.column] = flippedColor
            }
        }
        return result
    }

}
