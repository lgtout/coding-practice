package com.lagostout.elementsofprogramminginterviews.arrays

/**
 * Problem 6.1.2 page 65
 */
fun arrangeAsDutchNationalFlag(array: MutableList<Int>) {
    if (array.isEmpty() || array.size == 1) return
    var key = array[0]
    fun swap(array: MutableList<Int>, index1: Int, index2: Int) {
        val temp = array[index1]
        array[index1] = array[index2]
        array[index2] = temp
    }
    var boundaryIndex = 1
    (1 until array.size).forEach { currentIndex ->
        if (array[currentIndex] == key) {
            swap(array, currentIndex, boundaryIndex++)
        }
    }
    if (boundaryIndex == array.size) return
    key = array[boundaryIndex++]
    (boundaryIndex until array.size).forEach {
        currentIndex ->
        if (array[currentIndex] == key) {
            swap(array, currentIndex, boundaryIndex++)
        }
    }
}
