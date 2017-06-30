package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

/**
 * Problem 18.3.3 page 343
 */
class MaximizeNumberOfVisiblePointsInFieldOfView {
    companion object {
        class FieldOfView(val start: Int, val stop: Int) {
            fun contains(other: FieldOfView):Boolean {
                return start <= other.start && stop >= other.stop
            }
        }
        data class Result(val fieldOfView: FieldOfView, val pointCount: Int)
        fun maximumNumberOfVisiblePointsInFieldOfView(
                points: List<Int>, fieldOfView: FieldOfView): Result {
            return Result(FieldOfView(1,1), -1)
        }
    }
}
