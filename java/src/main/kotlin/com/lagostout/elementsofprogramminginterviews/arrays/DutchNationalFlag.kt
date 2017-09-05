package com.lagostout.elementsofprogramminginterviews.arrays

fun rearrangeAsDutchNationalFlag(array: MutableList<Int>, pivotIndex: Int) {
    if (array.isEmpty() || array.size == 1) return
    if (pivotIndex > array.lastIndex || pivotIndex < 0)
        throw IllegalArgumentException(
                "Argument pivotIndex must be > 0 and <= array last index")
    fun swap(array: MutableList<Int>, index1: Int, index2: Int) {
        val temp = array[index1]
        array[index1] = array[index2]
        array[index2] = temp
    }
    val pivotValue = array[pivotIndex]
    swap(array, 0, pivotIndex)
    var boundaryIndex = 0
    (1 until array.size).forEach { currentIndex ->
        if (array[currentIndex] < pivotValue) {
            swap(array, currentIndex, ++boundaryIndex)
        }
//        println(array)
    }
    swap(array, 0, boundaryIndex)
    println(array)
    (boundaryIndex + 1 until array.size).forEach {
        currentIndex ->
        println(currentIndex)
        if (array[currentIndex] == pivotValue) {
            swap(array, currentIndex, ++boundaryIndex)
        }
    }
}