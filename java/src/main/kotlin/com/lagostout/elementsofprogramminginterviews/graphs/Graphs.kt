package com.lagostout.elementsofprogramminginterviews.graphs


fun toGraph(booleanMatrix: List<List<Boolean>>):
        MutableMap<Point<Boolean>, MutableSet<Point<Boolean>>> {
    val adjacencies1 = toGraph(booleanMatrix)
    val adjacencies2 = toGraph(booleanMatrix, false)
    return (adjacencies1 + adjacencies2).toMutableMap()
}

/**
 * Transforms a boolean matrix into an adjacency map of a graph.
 * Edges are 2-way.
 */
fun toGraph(booleanMatrix: List<List<Boolean>>,
            cellValueToRetain: Boolean = true):
        MutableMap<Point<Boolean>, MutableSet<Point<Boolean>>> {
    val adjacencies: MutableMap<Point<Boolean>, MutableSet<Point<Boolean>>> = mutableMapOf()
    booleanMatrix.forEachIndexed { rowIndex, list ->
        list.forEachIndexed {
            columnIndex, cellValue ->
            if (cellValue == cellValueToRetain) {
                val point = Point(columnIndex, rowIndex, cellValue)
                val adjacentPoints = mutableSetOf<Point<Boolean>>()
                adjacencies.put(point, adjacentPoints)
                val previousRow = rowIndex - 1
                if (previousRow >= 0) {
                    val previousRowPoint = Point(columnIndex, previousRow, cellValue)
                    if (adjacencies.containsKey(previousRowPoint)) {
                        adjacencies[point]?.add(previousRowPoint)
                        adjacencies[previousRowPoint]?.add(point)
                    }
                }
                val previousColumn = columnIndex - 1
                if (previousColumn >= 0) {
                    val previousColumnPoint = Point(previousColumn, rowIndex, cellValue)
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
fun computeComponents(graph: Map<Point<Boolean>, Set<Point<Boolean>>>):
        Set<Set<Point<Boolean>>> {
    // TODO Modify to also group components by value i.e. T/F
    val visited = mutableSetOf<Point<Boolean>>()
    val components = mutableSetOf<Set<Point<Boolean>>>()
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

