package com.lagostout.elementsofprogramminginterviews.recursion

/* Problem 18.7 page 350 */

fun maximumWaterTrappedByVerticalLines(lines: List<Int>): Int {
    var leftIndex = 0
    var rightIndex = lines.lastIndex
    var max = 0
    while (leftIndex < rightIndex) {
        val left = lines[leftIndex]
        val right = lines[rightIndex]
        val volume = minOf(left, right) * (rightIndex - leftIndex)
        max = maxOf(max, volume)
        if (left > right) rightIndex -= 1
        else leftIndex += 1
    }
    return max
}