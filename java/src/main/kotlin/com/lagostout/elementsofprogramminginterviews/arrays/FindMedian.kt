@file:Suppress("NAME_SHADOWING")

package com.lagostout.elementsofprogramminginterviews.arrays

import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator

/* Problem 12.8.2 page 204 */

fun findMedian(array: List<Int>): Double? {

    if (array.isEmpty()) return null

    val array = array.toMutableList()
    var leftBounds: Pair<Int, Int>
    var rightBounds = Pair(0, array.lastIndex)
    var boundsToSearch = rightBounds
    val random = RandomDataGenerator().apply { reSeed(1) }
    val medianBounds = (array.count() / 2).let {
        val countIsOdd = array.count() % 2 == 1
        Pair(it, it).let {
            if (countIsOdd) it
            else it.copy(first = it.first - 1)
        }
    }

    fun swap(from: Int, to: Int) {
        val temp = array[from]
        array[from] = array[to]
        array[to] = temp
    }

    fun contains(bounds1: Pair<Int, Int>,
                 bounds2: Pair<Int, Int>): Boolean {
        return bounds2.toList().all {
            it >= bounds1.first && it <= bounds1.second
        }
    }

    while (boundsToSearch != medianBounds) {
        val pivotIndex = random.nextInt(boundsToSearch)
        val pivotValue = array[pivotIndex]
        swap(boundsToSearch.first, pivotIndex)
        var sortedIndex = boundsToSearch.first
        var unsortedIndex = boundsToSearch.second
        while (sortedIndex < unsortedIndex) {
            val unsortedValue = array[unsortedIndex]
            if (unsortedValue <= pivotValue) {
                swap(sortedIndex + 1, unsortedIndex)
                sortedIndex += 1
            }
            unsortedIndex -= 1
        }
        swap(boundsToSearch.first, sortedIndex)
        leftBounds = boundsToSearch.copy(second = sortedIndex)
        rightBounds = boundsToSearch.copy(first = sortedIndex + 1)
        boundsToSearch = if (contains(leftBounds, medianBounds))
            leftBounds else rightBounds
    }

    return boundsToSearch.let {
        (array[it.first] + array[it.second]) / 2.0
    }

}
