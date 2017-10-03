package com.lagostout.elementsofprogramminginterviews.searching

/**
 * Problem 12.1.3 page 193
 */
fun findIndexOfLocalMinimum(array: List<Int>): Int {
    val indexOfMinimum: Int
    var left = 0
    var right = array.size
    while (true) {
        val middle = (left + right) / 2
        if (array[middle - 1] >= array[middle] &&
                array[middle + 1] >= array[middle]) {
            indexOfMinimum = middle
            break
        } else if (array[middle - 1] <= array[middle]) {
            right = middle
        } else {
            left = middle
        }
    }
    return indexOfMinimum
}
