package com.lagostout.elementsofprogramminginterviews.searching

/**
 * Problem 12.1.4 page 193
 */
fun <T : Comparable<T>>findIntervalEnclosingInteger(
        sortedList: List<T>, k: T): Pair<Int, Int> {
    var i = 0
    var j = sortedList.size

    // Find first occurrence

    while (i < j && sortedList[i] != k) {
        val mid = i + ((j - i) / 2)
        if (sortedList[mid] < k) {
            i = mid + 1
        }
        else j = mid
    }

    // Integer not present in list

    if (i == sortedList.size // Elements < k
            || sortedList[i] != k) // Elements > k
        return Pair(-1, -1)

    // Find last occurrence

    val left = i
    j = sortedList.size
    while (i < j) {
        val mid = i + ((j - i) / 2)
        if (sortedList[mid] > k) j = mid
        else i = mid + 1
    }
    val right = j - 1

    return Pair(left, right)
}