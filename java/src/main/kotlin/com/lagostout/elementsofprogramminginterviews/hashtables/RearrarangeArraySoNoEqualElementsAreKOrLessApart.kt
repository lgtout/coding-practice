package com.lagostout.elementsofprogramminginterviews.hashtables

import java.util.*

/**
 * Problem 13.7.5 page 225
 */
fun rearrangeArraySoNoEqualElementsAreKOrLessApart(
        array: List<Int>, k: Int): List<Int> {
    data class ValueCount(val value: Int, var count: Int)
    val sortedArray = mutableListOf<Int>()
    val valueToValueCountMap = mutableMapOf<Int, ValueCount>()
    array.forEach { it ->
        val count = valueToValueCountMap.getOrDefault(
                it, ValueCount(value = 0, count = 0)).count + 1
        valueToValueCountMap[it] = ValueCount(value = it, count = count)
    }
    val valueCountsSortedByCount = LinkedList<ValueCount>(
            valueToValueCountMap.values.sortedBy { it.count })
    while (valueCountsSortedByCount.isNotEmpty()) {
        val iterator = valueCountsSortedByCount.iterator().withIndex()
        var groupSize = 0
        val zeroCountValueIndices = mutableSetOf<Int>()
        for ((index, valueCount) in iterator) {
            if (groupSize >= k) break
            sortedArray.add(valueCount.value)
            valueCount.count--
            groupSize++
            if (valueCount.count == 0) {
                zeroCountValueIndices.add(index)
            }
        }
        zeroCountValueIndices.map {
            valueCountsSortedByCount.removeAt(it)
        }
    }
    return sortedArray
}