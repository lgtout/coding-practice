package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

/**
 * Problem 18.4.3 page 346
 */
fun kElementsAddUpToNumberWithRepetitionAllowed(
        k: Int, allElements: List<Int>, s: Int): Boolean {
    return false
}

fun permutationsWithRepetition(
        elements: List<Int>, repeatCount: Int = 1,
        startIndex: Int = 0): List<List<Int>> {
    // TODO This algorithm for generating permutations isn't right yet.
    return if (repeatCount == 0) {
        // Should we be returning the same number of empty lists as
        // the size of the elements list?
        emptyList()
    } else {
        (startIndex..elements.lastIndex).map { index ->
            permutationsWithRepetition(elements, repeatCount - 1, index).map {
                listOf(index) + it
            }.flatten()
        }
    }
}