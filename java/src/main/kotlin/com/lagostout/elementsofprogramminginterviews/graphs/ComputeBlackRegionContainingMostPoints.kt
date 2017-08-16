package com.lagostout.elementsofprogramminginterviews.graphs

/**
 * Problem 19.2.2 page 365
 */
fun computeBlackRegionContainingMostPoints(
        matrix: List<List<Boolean>>): Set<Point> {
    val graph = booleanMatrixToGraph(matrix) // Let's assume true == black
    val visited = mutableSetOf<Point>()
    var regionContainingMostPoints: Set<Point> = emptySet()
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
        if (currentRegion.size > regionContainingMostPoints.size) {
            regionContainingMostPoints = currentRegion
        }
    }
    return regionContainingMostPoints
}
