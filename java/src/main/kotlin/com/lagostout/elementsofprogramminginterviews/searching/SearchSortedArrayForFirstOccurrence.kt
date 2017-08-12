package com.lagostout.elementsofprogramminginterviews.searching

/**
 * Problem 12.1 page 192
 */
fun firstOccurrence(array: List<Int>, k: Int): Int? {
    var firstOccurrenceOfK: Int? = null
    var left = 0
    var right = array.lastIndex
    while (left <= right) {
        val middle = left + ((right - left) / 2)
        val middleValue = array[middle]
        if (middleValue >= k) {
            right = middle - 1
            if (middleValue == k) {
                firstOccurrenceOfK = middle
            }
        } else if (middleValue < k) {
            left = middle + 1
        }
    }
    return firstOccurrenceOfK
}