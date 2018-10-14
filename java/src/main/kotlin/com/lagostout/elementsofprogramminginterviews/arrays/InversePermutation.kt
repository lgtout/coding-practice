package com.lagostout.elementsofprogramminginterviews.arrays

/* Problem 6.9.2 page 76 */

/* We know that the set of permutation values will exactly be the set of
indices of the permutation.  We use this knowledge to devise a way to mark
visited positions - when a position is visited, we make its value negative.
We shift all values right by 1. This eliminates the value 0, which cannot
be made negative.  Before we return, we undo the earlier right shift with a
left shift and we make all values positive. */

@Suppress("NAME_SHADOWING")
fun inversePermutation(permutation: MutableList<Int>): MutableList<Int> {
    if (permutation.isEmpty()) return permutation
    for (index in permutation.indices) {
        permutation[index] += 1
    }
    var index = permutation[0] - 1
    var minIndex = 0
    var permutedIndex = 1 // (0 + 1)
    while (true) {
        while (true) {
            if (permutation[index] < 0) break
            val nextIndex = permutation[index] - 1
            val nextPermutedIndex = index + 1
            permutation[index] = -permutedIndex
            index = nextIndex
            permutedIndex = nextPermutedIndex
        }
        minIndex += 1
        index = minIndex
        if (index > permutation.lastIndex) break
        permutedIndex = permutation[index]
    }
    println(permutation)
    for (index in permutation.indices) {
        permutation[index] = (permutation[index] * -1) - 1
    }
    println(permutation)
    return permutation
}