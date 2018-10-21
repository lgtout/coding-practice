package com.lagostout.elementsofprogramminginterviews.arrays

/* Problem 6.10.1 page 76 */

fun nextPermutation(permutation: List<Int>): List<Int> {
    if (permutation.size <= 1) return permutation
    @Suppress("NAME_SHADOWING")
    val permutation = permutation.toMutableList()
    var previousElement = permutation.last()
    var currentIndex = permutation.lastIndex - 1
    fun swap(list: MutableList<Int>, from: Int, to: Int) {
        val temp = list[from]
        list[from] = list[to]
        list[to] = temp
    }
    while (currentIndex >= 0) {
        val element = permutation[currentIndex]
        if (element < previousElement) {
            // Find next higher digit in the right sublist.
            var indexOfHigherElement = currentIndex + 1
            for (indexOfCandidate in currentIndex + 2..permutation.lastIndex) {
                if (permutation[indexOfCandidate] > element)
                    indexOfHigherElement = indexOfCandidate
            }
            // Swap the next higher digit and the leftmost digit that we'll modify.
            swap(permutation, currentIndex, indexOfHigherElement)
            // Reverse the right sublist.
            permutation.subList(currentIndex + 1, permutation.size)
                    .let {
                        for (index in 0..((it.size / 2) - 1)) {
                            val oppositeIndex = it.lastIndex - index
                            swap(it, index, oppositeIndex)
                        }
                    }
            break
        }
        previousElement = element
        currentIndex -= 1
    }
    return if (currentIndex < 0) emptyList() else permutation
}
