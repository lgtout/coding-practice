package com.lagostout.elementsofprogramminginterviews.graphs

import com.lagostout.common.nextLevel

object PaintBooleanMatrix {

    fun flipRegionColor(
            grid: List<List<Boolean>>,
            start: Point): List<List<Boolean>> {
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
        val graph = booleanMatrixToGraph(grid, startCellColor)
        var currentLevel = setOf(start)
        val exploredPoints = mutableSetOf<Point>()
        while (currentLevel.isNotEmpty()) {
            currentLevel.forEach {
                result[it.row][it.column] = flippedColor
                exploredPoints.add(it)
            }
            currentLevel = nextLevel(graph, currentLevel, exploredPoints)
            println("currentLevel $currentLevel")
        }
        return result
    }

}
