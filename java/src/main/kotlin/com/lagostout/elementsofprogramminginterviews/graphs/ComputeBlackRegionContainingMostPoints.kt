package com.lagostout.elementsofprogramminginterviews.graphs

/**
 * Problem 19.2.2 page 365
 */
fun computeBlackRegionContainingMostPoints(
        matrix: List<List<Boolean>>): Set<Point> {
    val graph = toGraph(matrix) // Let's assume true == black
    return computeComponents(graph).maxBy { it.size } ?: emptySet()
}
