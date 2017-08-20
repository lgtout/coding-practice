package com.lagostout.elementsofprogramminginterviews.graphs

class SetPointToBlackAndReturnSizeOfLargestBlackRegion(matrix: List<List<Boolean>>) {

    private var sizeOfLargestBlackRegion = 0
    private val graph: Map<Point, Set<Point>> = booleanMatrixToGraph(matrix)
    private val pointToComponentMap = computeComponents(graph).flatMap {
        component -> component.map { it to component }
    }.toMap().toMutableMap()

    fun setPointToBlackAndReturnSizeOfLargestBlackRegion(point: Point): Int {
        if (graph.containsKey(point)) return sizeOfLargestBlackRegion
        val adjacentPoints = mutableSetOf(
                point.copy(row = point.row - 1),
                point.copy(column = point.column - 1),
                point.copy(row = point.row + 1),
                point.copy(column = point.column + 1))
        val adjacentBlackPoints = adjacentPoints.filter { graph.containsKey(it) }
        val component = mutableSetOf(point)
        pointToComponentMap[point] = component

        // Merge components
        adjacentBlackPoints.fold(component) {
            acc, point ->
            pointToComponentMap[point]?.let { component ->
                acc.apply {
                    addAll(component)
                }
            } ?: acc
        }

        // Update components of adjacent points
        adjacentBlackPoints.forEach {
            pointToComponentMap[it] = component
        }

        if (component.size > sizeOfLargestBlackRegion)
            sizeOfLargestBlackRegion = component.size

        return sizeOfLargestBlackRegion
    }

}
