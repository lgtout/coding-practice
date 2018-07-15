@file:Suppress("NAME_SHADOWING")

package com.lagostout.elementsofprogramminginterviews.arrays

import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator

/* Problem 12.8.2 page 204 */

fun findMedian(array: List<Int>): Double? {

    if (array.isEmpty()) return null

    val array = array.toMutableList()
    var boundsToSearch = Pair(0, array.lastIndex)
    val random = RandomDataGenerator().apply { reSeed(1) }
    val countIsOdd = array.count() % 2 == 1
    val medianIndices = (array.count() / 2).let {
        if (countIsOdd) listOf(it)
        else listOf(it - 1, it)
    }

    fun swap(from: Int, to: Int) {
        val temp = array[from]
        array[from] = array[to]
        array[to] = temp
    }

    val remainingMedianIndices = medianIndices.toMutableList()
    while (true) {
        val pivotIndex = random.nextInt(boundsToSearch)
        val pivotValue = array[pivotIndex]
        swap(boundsToSearch.first, pivotIndex)
        var sortedEndIndex = boundsToSearch.first
        var unsortedIndex = boundsToSearch.second
        while (sortedEndIndex < unsortedIndex) {
            val unsortedValue = array[unsortedIndex]
            if (unsortedValue <= pivotValue) {
                swap(sortedEndIndex + 1, unsortedIndex)
                sortedEndIndex += 1
            } else {
                unsortedIndex -= 1
            }
        }
        swap(boundsToSearch.first, sortedEndIndex)
        if (sortedEndIndex in remainingMedianIndices) {
            remainingMedianIndices.remove(sortedEndIndex)
            if (remainingMedianIndices.isEmpty()) break
        }
        val medianIndex = remainingMedianIndices.first()
        boundsToSearch = if (medianIndex < sortedEndIndex)
            boundsToSearch.copy(second = sortedEndIndex - 1)
        else  boundsToSearch.copy(first = sortedEndIndex + 1)
    }

    return medianIndices.sumBy { array[it] }
            .let { it / (if (countIsOdd) 1.0 else 2.0) }

}
