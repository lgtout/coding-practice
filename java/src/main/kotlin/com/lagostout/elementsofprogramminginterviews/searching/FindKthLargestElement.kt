package com.lagostout.elementsofprogramminginterviews.searching

/* Problem 12.8 page 202 */

fun findKthLargestElement(list: MutableList<Int>, k: Int): Int {
    quickSelect(list, 0, list.lastIndex, k)
    return list[list.size - k]
}

private fun swap(list: MutableList<Int>, index1: Int, index2: Int) {
    val temp = list[index1]
    list[index1] = list[index2]
    list[index2] = temp
}

private fun quickSelect(list: MutableList<Int>, left: Int, right: Int, k: Int) {
    if (left >= right) return
    val pivotIndex: Int = ((right - left) / 2.0).toInt() + left
    swap(list, left, pivotIndex)
    val pivot = list[left]
    var readIndex = left + 1
    var writeIndex = left
    while (readIndex <= right) {
        val entry = list[readIndex]
        if (entry < pivot) {
            swap(list, ++writeIndex, readIndex)
        }
        ++readIndex
    }
    /* This is tricky.  We want to swap with the writeIndex i.e.
    the last entry that's less than the pivot entry.  We DON'T
    want to swap with the pivot index.  The last entry could end
    up being to the left or right of the pivot index, depending
    on the size of the pivot entry relative to all the other
    entries. */
    swap(list, left, writeIndex)
    val kIndex = list.size - k
    if (writeIndex == kIndex) return
    else {
        (if (kIndex < pivotIndex) Pair(left, writeIndex - 1)
        else Pair(writeIndex + 1, right)).let {
            (nextLeft, nextRight) ->
            quickSelect(list, nextLeft, nextRight, k)
        }
    }
}