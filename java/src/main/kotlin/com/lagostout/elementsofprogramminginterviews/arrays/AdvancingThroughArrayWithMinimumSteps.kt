package com.lagostout.elementsofprogramminginterviews.arrays

/**
 * Problem 6.4.2 page 69
 */
fun advanceThroughArrayWithMinimumSteps(moves: List<Int>): Int {
    // Assumptions and implications:
    // -- The supplied array is non-empty.
    // -- The last location is already reachable.  (We already
    // solved the problem of determining if it's reachable in
    // 6.4.1, so we won't repeat that here.)  As such, there is
    // a sequence of positions such that, at each position, the
    // subsequent positions in the sequence are reachable.
    // -- The first position in the array has a value. Otherwise
    // it would be impossible to proceed.
    var steps = 0
    var currentIndex = 0
    var nextIndex: Int
    while (true) {
        if (currentIndex >= moves.lastIndex) break
        var farthestReachableIndex = currentIndex + moves[currentIndex]
        nextIndex = farthestReachableIndex
        run {
            for (index in ((currentIndex + 1)..minOf(
                    farthestReachableIndex, moves.lastIndex))) {
                (index + moves[index]).let {
                    if (it > farthestReachableIndex) {
                        farthestReachableIndex = it
                        nextIndex = index
                    }
                }
            }
            currentIndex = nextIndex
            ++steps
        }
    }
    return steps
}