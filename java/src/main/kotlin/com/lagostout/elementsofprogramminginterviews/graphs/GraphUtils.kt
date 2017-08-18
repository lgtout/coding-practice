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

/**
 * Compute components of a graph.
 */
fun computeComponents(graph: Map<Point, Set<Point>>): Set<Set<Point>> {
    val visited = mutableSetOf<Point>()
    val components = mutableSetOf<Set<Point>>()
    graph.keys.forEach { vertex ->
        if (visited.contains(vertex)) return@forEach
        var verticesToVisit = mutableSetOf(vertex)
        var vertexCount = 0
        val currentRegion = mutableSetOf(vertex)
        while (verticesToVisit.isNotEmpty()) {
            vertexCount += verticesToVisit.size
            visited.addAll(verticesToVisit)
            verticesToVisit = verticesToVisit.fold(mutableSetOf()) {
                acc, vertex ->
                graph[vertex]?.let {
                    adjacentVertices ->
                    acc.apply {
                        addAll(adjacentVertices.filterNot { visited.contains(it) })
                    }
                } ?: acc
            }
            currentRegion.addAll(verticesToVisit)
        }
        components.add(currentRegion)
    }
    return components
}

