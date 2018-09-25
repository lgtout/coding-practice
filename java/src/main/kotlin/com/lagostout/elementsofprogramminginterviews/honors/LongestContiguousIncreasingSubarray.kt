package com.lagostout.elementsofprogramminginterviews.honors

import com.lagostout.common.count

/* Problem 25.5 page 448 */

fun longestContiguousIncreasingSubarray(list: List<Int>): Pair<Int, Int>? {
    if (list.isEmpty()) return null
    var longestSubarray = Pair(0,0)
    var currentSubarray = longestSubarray
    list.indices.drop(1).forEach { index ->
        val value = list[index]
        val subarrayEndValue = list[currentSubarray.second]
        currentSubarray = if (value > subarrayEndValue) {
            currentSubarray.copy(second = index).also {
                if (it.count > longestSubarray.count)
                    longestSubarray = it
            }
        } else  {
            Pair(index, index)
        }
    }
    return longestSubarray
}

fun longestContiguousIncreasingSubarrayWithSkipping(
        list: List<Int>): Pair<Int, Int>? {
    if (list.isEmpty()) return null
    var longestSubarray = Pair(0,0)
    var index = 0
    while (index <= list.lastIndex) {
        val minLeftIndex = index - longestSubarray.count + 1
        var leftIndex = index - 1
        var currentSubarray = Pair(index, index)
        while (leftIndex >= minLeftIndex) {
            val value = list[leftIndex]
            if (value > list[leftIndex + 1]) break
            currentSubarray = currentSubarray.copy(
                first = leftIndex)
            leftIndex -= 1
        }
        var rightIndex = index + 1
        while (rightIndex <= list.lastIndex) {
            val value = list[rightIndex]
            if (value < list[rightIndex - 1]) break
            currentSubarray = currentSubarray.copy(
                second = rightIndex)
            rightIndex += 1
        }
        if (currentSubarray.count > longestSubarray.count) {
            longestSubarray = currentSubarray
            index = longestSubarray.first
        } else {
            index = currentSubarray.second
        }
        index += longestSubarray.count
    }
    return longestSubarray
}
