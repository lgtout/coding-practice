package com.lagostout.elementsofprogramminginterviews.arrays

/**
 * Problem 6.1.4 page 65
 */
@Suppress("NAME_SHADOWING")
fun arrangeAsDutchNationalFlag(array: MutableList<Boolean>) {
    var firstTrueIndex = 0
    var currentIndex = 0
    fun swap (fromIndex: Int, toIndex: Int) {
        val temp = array[fromIndex]
        array[fromIndex] = array[toIndex]
        array[toIndex] = temp
    }
    while (currentIndex < array.size) {
        if (!array[currentIndex]) {
            while (!array[firstTrueIndex] &&
                    firstTrueIndex < currentIndex) {
                firstTrueIndex++
            }
            swap(currentIndex, firstTrueIndex)
        }
        currentIndex++
    }
}