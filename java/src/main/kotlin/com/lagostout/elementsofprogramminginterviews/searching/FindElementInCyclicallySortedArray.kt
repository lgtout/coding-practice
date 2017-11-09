package com.lagostout.elementsofprogramminginterviews.searching

/**
 * Problem 12.3.1 page 194
 */
fun <T : Comparable<T>> findElementInCyclicallySortedArray(
        array: List<T>, value: T): Int? {
    var left = 0
    var right = array.lastIndex
    var elementIndex: Int? = null
    while (left <= right) {
        val leftValue = array[left]
        val rightValue = array[right]
        val mid = (right - left) / 2 + left
        val midValue = array[mid]
        if (midValue == value) {
            elementIndex = mid
            break
        } else if (midValue > value) {
            if (value < leftValue)
                left = mid + 1
            else right = mid - 1
        } else {
            if (value > rightValue)
                right = mid - 1
            else left = mid + 1
        }
    }
    return elementIndex
}