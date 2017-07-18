package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

object PaintBooleanMatrix {

    fun flipRegionColor(grid: List<List<Boolean>>, start: Point):
            List<List<Boolean>> {
        val result = grid.map {
            mutableListOf<Boolean>().apply {
                addAll(it)
            }
        }
        val graph = toGraph(grid, grid[start.row][start.column])
        val queue = LinkedList<List<Point>>()
        queue.add(listOf(start))
        while (queue.isNotEmpty()) {
            val points = queue.remove()
            points.forEach {
//                result.
            }
        }
        return result
    }

}
