package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

/**
 * Problem 18.3.3 page 343
 */
// TODO
// Alternate implementation that accumulates all peripheries at once
// instead of one at a time, as here.  Also, rotate as few times as
// possible.  Not necessary to rotate one unit at a time.  Instead,
// move field of view start directly to next point.
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
        data class Result(val fieldOfView: FieldOfView = FieldOfView(0,0),
                          val visiblePointCount: Int = 0)
        fun maximumNumberOfVisiblePointsInFieldOfView(
                points: List<Int>, fieldOfView: FieldOfView): Result {
            var result: Result = Result()
            val pointPositionToPointCountMap = mutableMapOf<Int, Int>().withDefault { 0 }
            points.forEach {
                pointPositionToPointCountMap.put(
                        it, pointPositionToPointCountMap.getValue(it).plus(1))
            }
            var currentFieldOfView = fieldOfView.copy(
                    start = -fieldOfView.length, stop = -1)
//            println("currentFieldOfView $currentFieldOfView")
            var maximumCountOfPointsInFieldOfView = 0
            var countOfPointsInCurrentFieldOfView = 0
            while (currentFieldOfView.stop < 99) {
                currentFieldOfView = currentFieldOfView.rotate()
//                println("rotate")
//                println("currentFieldOfView $currentFieldOfView")
                countOfPointsInCurrentFieldOfView +=
                        pointPositionToPointCountMap.getValue(currentFieldOfView.stop)
                countOfPointsInCurrentFieldOfView -=
                        pointPositionToPointCountMap.getValue(currentFieldOfView.start - 1)
                if (maximumCountOfPointsInFieldOfView <=
                        countOfPointsInCurrentFieldOfView) {
                    maximumCountOfPointsInFieldOfView =
                            countOfPointsInCurrentFieldOfView
                    result = result.copy(fieldOfView = currentFieldOfView,
                            visiblePointCount = countOfPointsInCurrentFieldOfView)
                }
            }
            return result
        }
    }
}
