package com.lagostout.elementsofprogramminginterviews.arrays

/**
 * Problem 6.4.2 page 69
 */
// Assumptions and implications:
// -- The supplied array is non-empty.
// -- The last location is already reachable.  We already
// solved the problem of determining if it's reachable in
// 6.4.1, so no need to repeat that here.
// -- There is a sequence of positions such that, at each
// position, the subsequent positions in the sequence are
// reachable.
// -- The first position in the array has a value.
// Otherwise it would be impossible to proceed.
fun minimumStepsToAdvanceThroughArray(moves: List<Int>): Int {
    var steps = 0
    var currentIndex = 0
    var nextMoveIndex: Int
    while (currentIndex < moves.lastIndex) {
        var movesAvailable = moves[currentIndex]
        var maxDistance = Pair(currentIndex, movesAvailable)
        nextMoveIndex = currentIndex
        while (movesAvailable > 0 &&
                nextMoveIndex < moves.lastIndex - 1) {
            movesAvailable--
            nextMoveIndex++
            val movesPossible = movesAvailable + moves[nextMoveIndex]
            maxDistance = listOf(
                    maxDistance, Pair(nextMoveIndex, movesPossible))
                    .maxBy { it.first + it.second }!! // ???
        }
        currentIndex += maxDistance.first // ???
        steps++
    }
    return steps
}