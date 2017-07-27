package com.lagostout.elementsofprogramminginterviews.hashtables

/**
 * Problem 13.7.5 page 225
 */
fun rearrangeArraySoNoEqualElementsAreKOrLessApart(
        array: List<Int>, k: Int): List<Int> {
    data class ValueCount(val value: Int, val count: Int)
    val sortedArray = mutableListOf<Int>()
    if (array.isEmpty()) return sortedArray
    val valueCount = array.size
    val countToValuesMap = array.groupingBy { it }.eachCount()
            .entries.groupBy({ it.value }, { it.key })
            .mapValues { it.value.toMutableList()}
            .toSortedMap(Comparator { k1:Int, k2:Int -> k2.compareTo(k1)})
    val indexToAvailableValuesMap = mutableMapOf<Int, ValueCount>()
    val currentIndex = 0
    // TODO Use while(values.isNotEmpty()) to drain values while possibly adding to values
    0.rangeTo(array.size - 1).forEach {
        indexToAvailableValuesMap[it]?.also {
            (value, count) ->
            countToValuesMap[count]?: run {
                countToValuesMap[count] = mutableListOf<Int>()
                countToValuesMap[count]!!
            }.apply { add(value) }
        }
    }
    return sortedArray
}