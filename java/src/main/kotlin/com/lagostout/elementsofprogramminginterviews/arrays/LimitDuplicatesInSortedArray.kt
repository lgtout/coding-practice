package com.lagostout.elementsofprogramminginterviews.arrays

fun <T : Comparable<T>> limitDuplicatesInSortedArray(array: Array<T>) {
    var readIndex = 0
    var writeIndex = readIndex
    var duplicateCount = 0
    var currentEntry: T
    while (readIndex <= array.lastIndex) {
        currentEntry = array[readIndex]
        val entriesAreDifferent = array[readIndex] != currentEntry
        if (entriesAreDifferent || duplicateCount < 2) {
            if (entriesAreDifferent) duplicateCount = 1
            else ++duplicateCount
            array[++writeIndex] = array[readIndex]
        }
        ++readIndex
    }

}