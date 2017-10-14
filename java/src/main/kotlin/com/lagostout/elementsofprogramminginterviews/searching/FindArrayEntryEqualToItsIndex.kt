package com.lagostout.elementsofprogramminginterviews.searching

/**
 * Problem 12.2.1 page 194
 */
fun findArrayEntryEqualToItsIndex(array: List<Int>): Int? {
    var left = 0
    var right = array.lastIndex
    var entryEqualToIndex: Int? = null
    while (left <= right) {
        val mid = (right - left) + (left/2)
        val midEntry = array[mid]
        if (mid == midEntry) {
            entryEqualToIndex = mid
            break
        } else if (mid < midEntry) {
            right = mid - 1
        } else if (mid > midEntry) {
            left = mid + 1
        }
    }
    return entryEqualToIndex
}