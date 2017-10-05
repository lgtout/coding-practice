package com.lagostout.elementsofprogramminginterviews.arrays

object DutchNationalFlagWith4KeyValues {

    fun arrangeAsDutchNationalFlag(array: MutableList<Int>) {
        if (array.isEmpty() || array.size == 1) return
        fun swap(array: MutableList<Int>, index1: Int, index2: Int) {
            val temp = array[index1]
            array[index1] = array[index2]
            array[index2] = temp
        }
        var boundaryIndex = 0
        while (boundaryIndex < array.size) {
            val key = array[boundaryIndex]
            (boundaryIndex..array.lastIndex).forEach { currentIndex ->
                if (array[currentIndex] == key) {
                    swap(array, currentIndex, boundaryIndex++)
                }
            }
        }
    }

}