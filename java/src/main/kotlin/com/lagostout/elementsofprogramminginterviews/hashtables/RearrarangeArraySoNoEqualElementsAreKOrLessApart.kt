package com.lagostout.elementsofprogramminginterviews.hashtables

import java.util.*

/**
 * Problem 13.7.5 page 225
 */
fun rearrangeArraySoNoEqualElementsAreKOrLessApart(
        array: List<Int>, k: Int): List<Int> {
    data class ValueCount(val value: Int, var count: Int)
    val valueToCountMap = mutableMapOf<Int, Int>()
    val sortedArray = mutableListOf<Int>()
    array.forEach {
        val count = valueToCountMap.getOrDefault(it, 0) + 1
        valueToCountMap[it] = count
    }
    val sortedValueCountList = LinkedList<ValueCount>(
            valueToCountMap.toSortedMap().map {
                ValueCount(it.key, it.value) }.toList())
    while (sortedValueCountList.isNotEmpty()) {
        val iterator = sortedValueCountList.iterator().withIndex()
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
            sortedValueCountList.removeAt(it)
        }
    }
    return sortedArray
}