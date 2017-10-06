package com.lagostout.elementsofprogramminginterviews.sorting

/**
 * Problem 14.2 page 242
 *
 * Assumptions -
 * Empty spaces are contiguous and exist only at the end of [toArray].
 * There may be more empty spaces than are needed to contain the merged
 * values.
 */
fun mergeSortedArrays(toArray: Array<Int?>, fromArray: Array<Int?>) {
    if (toArray.isEmpty()) return
    var lastNonEmptyToArrayIndex = 0
    while (true) {
        val nextIndex = lastNonEmptyToArrayIndex + 1
        if (nextIndex > toArray.lastIndex ||
                toArray[nextIndex] == null) break
        else lastNonEmptyToArrayIndex = nextIndex
    }
    var toArrayIndex = toArray.size
    (lastNonEmptyToArrayIndex downTo 0).forEach {
        toArray[--toArrayIndex] = toArray[it]
    }
    var mergedArrayIndex = 0
    var fromArrayIndex = 0
    while (true) {
        val pastEndOfFromArray = fromArrayIndex > fromArray.lastIndex
        val pastEndOfToArray = toArrayIndex > toArray.lastIndex
        toArray[mergedArrayIndex++] = if (!pastEndOfFromArray && !pastEndOfToArray) {
            val toArrayValue = toArray[toArrayIndex]!!
            val fromArrayValue = fromArray[fromArrayIndex]!!
            if (toArrayValue <= fromArrayValue) {
                toArrayIndex++
                toArrayValue
            } else {
                fromArrayIndex++
                fromArrayValue
            }
        } else if (!pastEndOfToArray) {
            toArrayIndex++
            fromArray[fromArrayIndex]
        } else if (!pastEndOfFromArray) {
            toArrayIndex++
            toArray[toArrayIndex]
        } else break
    }
}