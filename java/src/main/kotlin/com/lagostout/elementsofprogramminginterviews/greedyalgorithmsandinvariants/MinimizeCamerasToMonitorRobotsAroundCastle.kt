package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import java.util.*

/**
 * Problem 18.3.2 page 343
 */

// Let's assume that a complete rotation about
// the castle center is 100 units: 0 - 99.

data class Arc(val start: Int, val end: Int) {
    val isReversed:Boolean
        get() { return start > end }
}

fun minimumNumberOfCamerasToMonitorRobotsPatrollingACastle(
        perimeters: List<List<Arc>>): Int {
    when (perimeters.size) {
        0 -> return 0
        1 -> return perimeters[0].size
    }
    val stack = LinkedList<List<Arc>>(perimeters)
    val accumulatedPerimeter = LinkedList<Arc>()
    while (stack.isNotEmpty()) {
        val firstPerimeter = LinkedList<Arc>(stack.pop())
        val secondPerimeter = LinkedList<Arc>(stack.pop())
        while (firstPerimeter.isNotEmpty()) {
            val firstPerimeterArc = firstPerimeter.peek()
            val secondPerimeterArc = secondPerimeter.peek()
            while (secondPerimeterArc.end < firstPerimeterArc.start) {
                secondPerimeter.pop()
                accumulatedPerimeter.add(secondPerimeterArc)
            }
            while (firstPerimeterArc.end > secondPerimeterArc.start) {
                firstPerimeter.pop()
                accumulatedPerimeter.add(firstPerimeterArc)
            }
            while (secondPerimeterArc.start <= firstPerimeterArc.end) {
                accumulatedPerimeter.add(
                        Arc(secondPerimeterArc.start,
                        Math.min(secondPerimeterArc.end, firstPerimeterArc.end)))
                secondPerimeter.pop()
            }
        }
        stack.push(accumulatedPerimeter)
    }
    return stack.pop().size
}