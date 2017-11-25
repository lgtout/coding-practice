package com.lagostout.elementsofprogramminginterviews.arrays

/**
 * Problem 6.4.1 page 68
 */
fun advancingToEndOfArrayIsPossible(array: List<Int>): Boolean {
    var destinationIndex = array.lastIndex
    var destinationIndexIsReachable = true
    var finishedTraversingArray = false
    while (!finishedTraversingArray) {
        var currentIndex = destinationIndex - 1
        while (true) {
            if (currentIndex < 0) {
                finishedTraversingArray = true
                break
            }
            destinationIndexIsReachable = false
            if (currentIndex +
                    array[currentIndex] >= destinationIndex) {
                destinationIndexIsReachable = true
                destinationIndex = currentIndex
                break
            }
            --currentIndex
        }
    }
    return destinationIndexIsReachable
}