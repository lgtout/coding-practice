package com.lagostout.elementsofprogramminginterviews.arrays

/* Problem 6.9.1 page 74 */

fun <T> permuteElementsOfArray(array: MutableList<T>, permutation: MutableList<Int>) {
    var safeLastPermutedIndex = 0
    var element: T
    while (safeLastPermutedIndex < permutation.size) {
        while (permutation[safeLastPermutedIndex] != safeLastPermutedIndex) {
            val permutedIndex = permutation[safeLastPermutedIndex]
            // Swap elements
            element = array[safeLastPermutedIndex]!!
            val tempElement = array[permutedIndex]
            array[permutedIndex] = element
            array[safeLastPermutedIndex] = tempElement
            // Swap permutations
            val tempPermutedIndex = permutation[permutedIndex]
            permutation[permutedIndex] = permutedIndex
            permutation[safeLastPermutedIndex] = tempPermutedIndex
        }
        safeLastPermutedIndex += 1
    }
}