package com.lagostout.elementsofprogramminginterviews.sorting

/**
 * Problem 14.2 page 242
 */
fun mergeSortedArrays(toArray: MutableList<Int?>, fromArray: List<Int?>) {
    // We'll assume that empty spaces are contiguous and exist only at
    // the end of toArray.
    // Also, the size of toArray will be equal to or
    // greater than the number of elements in both arrays.
    if (fromArray.isEmpty()) return
    // At this point we know that, since fromArray is not empty,
    // toArray has at least one empty position.
    val nonNullElementCount = toArray.withIndex().find {
        it.value == null
    }!!.index
    // This step could be eliminated if we merged going from right to
    // left in the arrays, instead of left to right.
    val firstNonNullElementIndexAfterMove =
            toArray.size - nonNullElementCount
    (nonNullElementCount - 1 downTo 0).forEach { index ->
        toArray[firstNonNullElementIndexAfterMove + index] = toArray[index]
        // Not necessary, but might be useful during debugging.
        toArray[index] = null
    }
    var toArrayIndex = toArray.size - nonNullElementCount
    var mergedArrayIndex = 0
    var fromArrayIndex = 0
    while (true) {
        val pastEndOfFromArray = fromArrayIndex > fromArray.lastIndex
        val pastEndOfToArray = toArrayIndex > toArray.lastIndex
        val fromValue = if (!pastEndOfFromArray) fromArray[fromArrayIndex] else null
        val toValue = if (!pastEndOfToArray) toArray[toArrayIndex] else null
        toArray[mergedArrayIndex++] = if (!pastEndOfFromArray && !pastEndOfToArray) {
            compareValues(fromValue, toValue).let {
                when (it) {
                    1,0 -> {
                        toArray[toArrayIndex] = null
                        ++toArrayIndex
                        toValue
                    }
                    else -> {
                        ++fromArrayIndex
                        fromValue
                    }
                }
            }
        } else if (!pastEndOfFromArray) {
            fromArrayIndex++
            fromValue
        } else if (!pastEndOfToArray) {
            toArrayIndex++
            toValue
        } else break
    }

}