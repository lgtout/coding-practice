package com.lagostout.elementsofprogramminginterviews.arrays

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