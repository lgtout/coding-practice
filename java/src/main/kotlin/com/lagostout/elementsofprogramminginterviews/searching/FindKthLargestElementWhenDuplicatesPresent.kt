package com.lagostout.elementsofprogramminginterviews.searching

import com.lagostout.common.swap
import org.apache.commons.math3.random.RandomDataGenerator

/* Problem 12.8.3 page 204 */

data class ComparableIndexedValue<T : Comparable<T>>(val index: Int, val value: T) : Comparable<ComparableIndexedValue<T>> {
    override fun compareTo(other: ComparableIndexedValue<T>): Int {
        return value.compareTo(other.value).let {
            if (it == 0) {
                index.compareTo(other.index)
            } else it
        }
    }
}

fun <T : Comparable<T>> findKthLargestElementWhenDuplicatesPresent(
        list: List<T>, k: Int): T? {
    if (k > list.size || k < 0) return null
    val kIndex = k - 1
    val result: T
    val elements = mutableListOf<ComparableIndexedValue<T>>().apply {
        addAll(list.withIndex().map { (index, value) -> ComparableIndexedValue(index, value) })
    }
    var subarrayLeft = 0
    var subarrayRight = elements.lastIndex
    val rdg = RandomDataGenerator().apply { reSeed(1) }
    loop@ while (true) {
        val pivotIndex = rdg.nextInt(subarrayLeft, subarrayRight)
        val pivot = elements[pivotIndex]
        elements.swap(subarrayLeft, pivotIndex)
        // Rightmost sorted element that's < pivot value.
        var leftIndex = subarrayLeft
        // Rightmost unsorted element.
        var rightIndex = subarrayRight
        while (leftIndex < rightIndex) {
            val unsortedElement = elements[rightIndex]
            if (unsortedElement < pivot) {
                elements.swap(++leftIndex, rightIndex)
            } else {
                rightIndex--
            }
        }
        elements.swap(subarrayLeft, leftIndex)
        when {
            leftIndex == kIndex -> {
                result = elements[leftIndex].value
                break@loop
            }
            leftIndex < kIndex -> subarrayLeft = leftIndex + 1
            leftIndex > kIndex -> subarrayRight = leftIndex - 1
        }
    }
    return result
}