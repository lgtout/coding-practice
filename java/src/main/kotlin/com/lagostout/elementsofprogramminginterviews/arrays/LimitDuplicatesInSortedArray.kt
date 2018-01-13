package com.lagostout.elementsofprogramminginterviews.arrays

/* Problem 6.5.3 page 70 */

fun limitDuplicatesInSortedArray(array: Array<Int?>, m: Int) {
    var writeIndex: Int? = null
    var readIndex = 0
    var duplicateCount = 0
    var currentReadEntry = array[readIndex]
    while (true) {
        if (readIndex <= array.lastIndex &&
                array[readIndex] == currentReadEntry) {
            ++duplicateCount
            array[readIndex++] = null
        } else {
            val copyCount = if (duplicateCount == m)
                minOf(2, duplicateCount) else duplicateCount
            (1..copyCount).forEach {
                writeIndex = (writeIndex?.inc() ?: 0).also {
                    array[it] = currentReadEntry
                }
            }
            if (readIndex >= array.size) break
            currentReadEntry = array[readIndex]
            duplicateCount = 0
        }
    }
}