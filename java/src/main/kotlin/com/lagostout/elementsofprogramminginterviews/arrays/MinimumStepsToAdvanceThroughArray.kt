package com.lagostout.elementsofprogramminginterviews.arrays

/**
 * Problem 6.4.2 page 69
 */
// Assumptions:
// -- The last location is already reachable.  We already
// solved the problem of determining if it's reachable in 6.4.1, so
// no benefit to repeating that here.
// -- The supplied list is non-empty.
fun minimumStepsToAdvanceThroughArray(maxSteps: List<Int>): Int {
    var destinationIndex = maxSteps.lastIndex
    var stepCount: Int? = null
    var candidateStepIndex: Int
    (maxSteps.lastIndex - 1 downTo 0).forEach {
       if (it + maxSteps[it] >= destinationIndex) {
           destinationIndex = it
       }
    }
    return 0
}