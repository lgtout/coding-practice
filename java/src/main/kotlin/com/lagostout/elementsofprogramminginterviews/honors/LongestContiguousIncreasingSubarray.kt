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
        }
        else  {
            Pair(index, index)
        }
    }
    return longestSubarray
}