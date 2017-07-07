package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

object SearchMaze {

    data class Point(val x: Int, val y: Int)

    fun findPathThroughMaze(
            maze: Map<Point, Set<Point>>,
            entry: Point, exit: Point): List<Point> {
        data class Frame(val point: Point, val adjacentPoints: Iterator<Point>)
        val stackOfFrames = LinkedList<Frame>()
        stackOfFrames.push(Frame(point = entry,
                adjacentPoints = maze[entry]?.iterator() ?:
                        emptyList<Point>().iterator()))
        while (stackOfFrames.isNotEmpty() &&
                !(stackOfFrames.first().point == entry &&
                        stackOfFrames.last().point == exit)) {
            val frame = stackOfFrames.peek()
            val adjacentPoints = frame.adjacentPoints
            if (adjacentPoints.hasNext()) {
                val adjacentPoint = adjacentPoints.next()
                stackOfFrames.push(Frame(point = adjacentPoint,
                        adjacentPoints = maze[adjacentPoint]?.iterator() ?:
                                emptyList<Point>().iterator()))
            } else {
                stackOfFrames.pop()
            }
        }
        return listOf()
    }
}
