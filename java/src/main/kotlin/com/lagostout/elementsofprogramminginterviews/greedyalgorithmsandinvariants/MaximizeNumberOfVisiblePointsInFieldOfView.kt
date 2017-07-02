package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

/**
 * Problem 18.3.3 page 343
 */
class MaximizeNumberOfVisiblePointsInFieldOfView {
    companion object {
        data class FieldOfView(val start: Int, val stop: Int) {
            fun contains(other: FieldOfView):Boolean {
                return start <= other.start && stop >= other.stop
            }
            fun rotate(): FieldOfView {
                return copy(start = start+1, stop = stop+1)
            }
            val length: Int
                get() = stop - start + 1
        }
        data class Result(val fieldOfView: FieldOfView, val visiblePointCount: Int)
        fun maximumNumberOfVisiblePointsInFieldOfView(
                points: List<Int>, fieldOfView: FieldOfView): Result {
            val pointPositionToPointCountMap = mutableMapOf<Int, Int>().withDefault { 0 }
            points.forEach {
                pointPositionToPointCountMap.put(
                        it, pointPositionToPointCountMap[it]!!.plus(1))
            }
            var currentFieldOfView = fieldOfView.copy(
                    start = -fieldOfView.length, stop = -1)
            var maximumCountOfPointsInFieldOfView = 0
            var fieldOfViewWithMaximumCountOfPoints = currentFieldOfView
            var countOfPointsInCurrentFieldOfView = 0
            while (currentFieldOfView.stop <= 99) {
                if (maximumCountOfPointsInFieldOfView <
                        countOfPointsInCurrentFieldOfView) {
                    maximumCountOfPointsInFieldOfView =
                            countOfPointsInCurrentFieldOfView
                    fieldOfViewWithMaximumCountOfPoints = currentFieldOfView
                }
                countOfPointsInCurrentFieldOfView -=
                        pointPositionToPointCountMap[currentFieldOfView.start]!!
                currentFieldOfView = currentFieldOfView.rotate()
                countOfPointsInCurrentFieldOfView +=
                        pointPositionToPointCountMap[currentFieldOfView.stop]!!
            }
            return Result(fieldOfViewWithMaximumCountOfPoints,
                    countOfPointsInCurrentFieldOfView)
        }
    }
}
