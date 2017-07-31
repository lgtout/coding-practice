package com.lagostout.elementsofprogramminginterviews.hashtables

/**
 * Problem 13.7.5 page 225
 */
fun rearrangeArraySoNoEqualElementsAreKOrLessApart(
        array: List<Int>, k: Int): List<Int> {
    data class ValueCount(val value: Int, val count: Int)
    val sortedArray = mutableListOf<Int>()
    if (array.isEmpty()) return sortedArray
    val countToValuesMap = array.groupingBy { it }.eachCount()
            .entries.groupBy({ it.value }, { it.key })
            .mapValues { it.value.toMutableList()}
            // Highest to lowest (reversed) order
            .toSortedMap(Comparator { o1, o2 -> o2.compareTo(o1)} )
    val indexToFreedUpValuesMap = mutableMapOf<Int, ValueCount>()
//    val currentIndex = 0
    // TODO Use while(values.isNotEmpty()) to drain values while possibly adding to values
    array.indices.forEach {
        indexToFreedUpValuesMap[it]?.also {
            (value, count) ->
            countToValuesMap.getOrPut(count) {
               mutableListOf<Int>()
            }.add(value)
        }
        val countToValuesEntry = countToValuesMap.entries.first()
        val value = countToValuesEntry.value.removeAt(0)
        val count = countToValuesEntry.key
        indexToFreedUpValuesMap[it + k + 1] =
                ValueCount(value, count - 1)
        // If there are no more items with the current count,
        // remove the entry from the sorted array so that the
        // next set of items with the next lower count can be
        // used.
        if (countToValuesEntry.value.size == 0) {
            countToValuesMap.entries.remove(countToValuesEntry)
        }
    }
    return sortedArray
}