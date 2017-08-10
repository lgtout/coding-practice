package com.lagostout.elementsofprogramminginterviews.graphs

/**
 * Transforms a boolean matrix into an adjacency map of a graph.
 * Edges are 2-way.
 */
fun booleanMatrixToGraph(grid: List<List<Boolean>>,
                         cellValueToRetain: Boolean = true):
        Map<Point, Set<Point>> {
    val adjacencies: MutableMap<Point, MutableSet<Point>> = mutableMapOf()
    grid.forEachIndexed { rowIndex, list ->
        list.forEachIndexed {
            columnIndex, cellValue ->
            if (cellValue == cellValueToRetain) {
                val point = Point(columnIndex, rowIndex)
                val adjacentPoints = mutableSetOf<Point>()
                adjacencies.put(point, adjacentPoints)
                val previousRow = rowIndex - 1
                if (previousRow >= 0) {
                    val previousRowPoint = Point(columnIndex, previousRow)
                    if (adjacencies.containsKey(previousRowPoint)) {
                        adjacencies[point]?.add(previousRowPoint)
                        adjacencies[previousRowPoint]?.add(point)
                    }
                }
                val previousColumn = columnIndex - 1
                if (previousColumn >= 0) {
                    val previousColumnPoint = Point(previousColumn, rowIndex)
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

