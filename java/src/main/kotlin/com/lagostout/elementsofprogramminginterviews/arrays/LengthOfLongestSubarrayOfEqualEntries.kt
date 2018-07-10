package com.lagostout.elementsofprogramminginterviews.arrays

/* Problem 6.6.2 page 71 */

fun lengthOfLongestSubarrayOfEqualEntries(array: List<Int>): Int {
    if (array.count() <= 1) return array.count()
    var longestSubarrayCount = 1
    var currentSubarrayCount = 1
    (1 until array.count()).forEach { index ->
        val currentNumber = array[index]
        val previousNumber = array[index - 1]
        if (currentNumber == previousNumber) {
            currentSubarrayCount += 1
            if (currentSubarrayCount > longestSubarrayCount) {
                longestSubarrayCount = currentSubarrayCount
            }
        } else {
            currentSubarrayCount = 1
        }
    }
    return longestSubarrayCount
}