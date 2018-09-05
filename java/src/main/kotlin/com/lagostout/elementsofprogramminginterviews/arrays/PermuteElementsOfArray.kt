package com.lagostout.elementsofprogramminginterviews.arrays

/* Problem 6.9.1 page 74 */

fun <T> permuteElementsOfArray(array: MutableList<T?>, permutation: MutableList<Int>) {
    var safeLastPermutedIndex = 0
    var element: T
    while (safeLastPermutedIndex < permutation.size) {
        element = array[safeLastPermutedIndex]!!
        var permutedIndex = permutation[safeLastPermutedIndex]
        while (permutation[permutedIndex] != permutedIndex) {
            val tempElement = array[permutedIndex]
            array[permutedIndex] = element
            val tempPermutedIndex = permutation[permutedIndex]
            permutation[permutedIndex] = permutedIndex
            element = tempElement!!
            permutedIndex = tempPermutedIndex
        }
        safeLastPermutedIndex += 1
    }
}