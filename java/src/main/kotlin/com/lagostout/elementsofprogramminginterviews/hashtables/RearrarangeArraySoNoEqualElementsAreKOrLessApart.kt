package com.lagostout.elementsofprogramminginterviews.hashtables

/**
 * Problem 13.7.5 page 225
 */
fun rearrangeArraySoNoEqualElementsAreKOrLessApart(
        array: List<Int>, k: Int): List<Int> {
    if (k < 0) throw IllegalArgumentException("k must be non-negative")
    data class ValueCount(val element: Int, val count: Int)
    val sortedArray = mutableListOf<Int>()
    if (array.isEmpty()) return sortedArray
    val countToElementsMap = array.groupingBy { it }.eachCount()
            .entries.groupBy({ it.value }, { it.key })
            .mapValues { it.value.toMutableList()}
            // Highest to lowest (reversed) order
            .toSortedMap(Comparator { o1, o2 -> o2.compareTo(o1)} )
    val indexToFreedUpElementsMap = mutableMapOf<Int, ValueCount>()
    array.indices.forEach {
        indexToFreedUpElementsMap[it]?.apply {
            countToElementsMap.getOrPut(count) {
                mutableListOf<Int>()
            }.add(element)
        }
        if (countToElementsMap.entries.isEmpty()) {
            throw IllegalArgumentException("Array doesn't have enough different elements")
        }
        countToElementsMap.apply {
            entries.apply {
                first().apply {
                    val (count, elements) = this
                    val element = elements.removeAt(0)
                    val nextCount = count - 1
                    if (nextCount > 0) {
                        indexToFreedUpElementsMap[it + k + 1] =
                                ValueCount(element, nextCount)
                    }
                    // If we just used the last element with the current
                    // count, remove the entry from the sorted array so
                    // that the next set of items with the next lower
                    // count on deck for use next.
                    if (value.size == 1) {
                        entries.remove(this)
                    }
                }
            }
        }
    }
    return sortedArray
}