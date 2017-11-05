package com.lagostout.elementsofprogramminginterviews.searching

/**
 * Problem 12.3.2 page 196
 */
fun findMaxInAscDescSequences(sequences: List<Int>): Int {
    var left = 0
    var right = sequences.lastIndex
    var mid: Int = left
    while (right - left > 1) {
        mid = (right - left) / 2 + left
        if (sequences[mid] > sequences[mid - 1] &&
                sequences[mid] > sequences[mid + 1]) {
            break
        } else if (sequences[mid] > sequences[mid - 1]) {
            left = mid + 1
        } else if (sequences[mid] > sequences[mid + 1]) {
            right = mid
        }
    }
    return sequences[mid]
}