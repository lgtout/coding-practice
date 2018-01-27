package com.lagostout.elementsofprogramminginterviews.searching

import org.apache.commons.math3.random.RandomDataGenerator

/* Problem 12.8 page 202 */

fun findKthLargestElement(list: MutableList<Int>, k: Int): Int {
    quickSelect(list, 0, list.lastIndex, k,
            RandomDataGenerator().apply { reSeed(1) })
    return list[list.size - k]
}

private fun swap(list: MutableList<Int>, index1: Int, index2: Int) {
    val temp = list[index1]
    list[index1] = list[index2]
    list[index2] = temp
}

@Suppress("NAME_SHADOWING")
private fun quickSelect(list: MutableList<Int>, left: Int, right: Int,
                        k: Int, random: RandomDataGenerator) {
    var left = left
    var right = right
    while (left < right) {
        val pivotIndex: Int = random.nextInt(left, right)
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
            (if (kIndex < writeIndex) Pair(left, writeIndex - 1)
            else Pair(writeIndex + 1, right)).apply {
                left = first
                right = second
            }
        }
    }
}