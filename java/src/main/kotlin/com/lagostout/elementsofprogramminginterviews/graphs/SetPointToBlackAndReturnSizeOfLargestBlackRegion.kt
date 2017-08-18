package com.lagostout.elementsofprogramminginterviews.graphs

class SetPointToBlackAndReturnSizeOfLargestBlackRegion(matrix: List<List<Boolean>>) {

    private var sizeOfLargestBlackRegion = 0
    private val graph: Map<Point, Set<Point>> = booleanMatrixToGraph(matrix)
    private val pointToComponentMap = computeComponents(graph).map { component ->
        component.fold(mutableMapOf<Point, Set<Point>>()) {
            acc, partial ->
            acc.apply {
                put(partial, component)
            }
        }
    }.reduce {
        acc, partial ->
        acc.apply {
            entries.addAll(partial.entries)
        }
    }

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
        return 0
    }

}
