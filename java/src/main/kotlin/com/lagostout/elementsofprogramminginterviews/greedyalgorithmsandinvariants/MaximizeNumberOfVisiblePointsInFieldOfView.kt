package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import org.apache.commons.collections4.iterators.PeekingIterator
import java.util.*

/**
 * Problem 18.3.3 page 343
 */
object MaximizeNumberOfVisiblePointsInFieldOfView {
    data class FieldOfView(val start: Int = 0, val stop: Int = 0) {
        val length: Int
            get() = stop - start + 1

        fun contains(other: FieldOfView): Boolean {
            return start <= other.start && stop >= other.stop
        }
    }
    data class Result(val fieldOfView: FieldOfView = FieldOfView(0,0),
                      val visiblePointCount: Int = 0)
    fun maximumNumberOfVisiblePointsInFieldOfView(
            points: List<Int>, fieldOfView: FieldOfView): Result {
        var result: Result = Result()
        val fieldOfViewPoints = LinkedList<Int>(points.sorted())
        val fieldOfViewEndPointIterator = PeekingIterator(fieldOfViewPoints.iterator())
        val currentFieldOfViewPoints = LinkedList<Int>()
        var pointCount = 0
        while (fieldOfViewEndPointIterator.hasNext()) {
            do {
                val entry = fieldOfViewEndPointIterator.next()
                currentFieldOfViewPoints.addLast(entry)
                pointCount++
            } while (fieldOfViewEndPointIterator.peek() == entry)
            while (FieldOfView(
                    currentFieldOfViewPoints.first(),
                    currentFieldOfViewPoints.last()).length > fieldOfView.length) {
                currentFieldOfViewPoints.removeFirst()
                pointCount--
            }
            if (result.visiblePointCount <= currentFieldOfViewPoints.size) {
                result = result.copy(
                        fieldOfView = FieldOfView(currentFieldOfViewPoints.first(),
                                currentFieldOfViewPoints.last()),
                        visiblePointCount = currentFieldOfViewPoints.size)
            }
        }
        return result
    }
}
