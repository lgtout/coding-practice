package com.lagostout.elementsofprogramminginterviews.searching

/**
 * Problem 12.3.1 page 194
 */
fun findPositionOfSmallestEntryInCyclicallySortedArray(list: List<Int>): Int? {
    if (list.isEmpty()) return null
    var left = 0
    var right = list.lastIndex
    while (right - left > 1) {
        val mid = (right - left) / 2 + left
        if (list[left] > list[mid]) {
            right = mid
        } else {
            left = mid
        }
    }
    return right
}