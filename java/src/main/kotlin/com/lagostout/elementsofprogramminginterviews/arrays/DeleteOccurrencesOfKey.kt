package com.lagostout.elementsofprogramminginterviews.arrays

fun <T: Comparable<T>> deleteOccurrencesOfKey(key: T, array: Array<T>): Int {
    var writeIndex = 0
    var readIndex = writeIndex
    while(readIndex <= array.lastIndex) {
        if (array[readIndex] != key) {
            array[writeIndex++] = array[readIndex]
        }
        ++readIndex
    }
    return writeIndex
}
