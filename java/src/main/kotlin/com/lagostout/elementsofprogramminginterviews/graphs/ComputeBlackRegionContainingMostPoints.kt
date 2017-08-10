package com.lagostout.elementsofprogramminginterviews.graphs

/**
 * Problem 19.2.2 page 365
 */
fun computeBlackRegionContainingMostPoints(matrix: List<List<Boolean>>): Point {
    val graph = booleanMatrixToGraph(matrix) // Let's assume true == black
    val visited = mutableSetOf<Point>()
    var regionContainingMostPoints: Set<Point> = emptySet()
    graph.forEach {
        (vertex, adjacentVertices) ->
        if (visited.contains(vertex)) return@forEach
        var verticesToVisit = mutableSetOf(vertex)
        var vertexCount = 0
        val currentRegion = mutableSetOf<Point>()
        while (verticesToVisit.isNotEmpty()) {
            vertexCount += verticesToVisit.size
            verticesToVisit = verticesToVisit.fold(mutableSetOf()) {
                acc, vertex ->
                graph[vertex]?.filterNot { visited.contains(it) }?.apply {
                    acc.addAll(this)
                }
                acc
            }
            currentRegion.addAll(verticesToVisit)
        }
        if (currentRegion.size > regionContainingMostPoints.size) {
            regionContainingMostPoints = currentRegion
        }
    }
    return Point(0,0)
}
