package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import java.util.*

/**
 * Problem 18.3.2 page 343
 */

// For ease of testing, we'll assume that a complete
// rotation about the castle center is 100 units: 0 - 99.

data class Arc(val start: Int, val end: Int)

fun minimumNumberOfCamerasToMonitorRobotsPatrollingACastle(
        perimeters: List<List<Arc>>): Int {
    if (perimeters.isEmpty()) return 0
    val stack = LinkedList<List<Arc>>(perimeters)
    var accumulatedPerimeter = LinkedList<Arc>()
    while (stack.isNotEmpty()) {
        val nextAccumulatedPerimeter = LinkedList<Arc>()
        val perimeter = LinkedList<Arc>(stack.pop())
        while (accumulatedPerimeter.isNotEmpty() || perimeter.isNotEmpty()) {
            val perimeterArc = perimeter.peek()
            var nextArc = if (accumulatedPerimeter.isNotEmpty()) {
                val accumulatedPerimeterArc = accumulatedPerimeter.peek()
                if (accumulatedPerimeterArc.start < perimeterArc.start) {
                    accumulatedPerimeter.pop()
                } else {
                    perimeter.pop()
                }
            } else {
                perimeter.pop()
            }
            if (nextAccumulatedPerimeter.isEmpty()) {
                nextAccumulatedPerimeter.push(nextArc)
            } else {
                val nextAccumulatedPerimeterArc = nextAccumulatedPerimeter.peek()
                if (nextAccumulatedPerimeterArc.end <= nextArc.start) {
                    nextArc = nextArc.copy(end = Math.min(
                            nextArc.end, nextAccumulatedPerimeterArc.end))
                    nextAccumulatedPerimeter.pop()
                }
                nextAccumulatedPerimeter.push(nextArc)
            }
        }
        accumulatedPerimeter = nextAccumulatedPerimeter
    }
    return accumulatedPerimeter.size
}