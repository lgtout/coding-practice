package com.lagostout.elementsofprogramminginterviews.hashtables

import java.util.Map.Entry.comparingByValue
import java.util.stream.Collectors.toList

/**
 * Problem 13.7.4 page 225
 */
fun maximumLengthOfShortestSubarrayContainingAllDistinctValues(values: List<Int>): Int {
    if (values.isEmpty()) return 0
    val mapOfValuesToCount = mutableMapOf<Int, Int>()
    values.forEach { value ->
        mapOfValuesToCount[value] = mapOfValuesToCount.getOrDefault(value, 0) + 1
    }
    val sortedEntries = mapOfValuesToCount.entries.stream()
            .sorted(comparingByValue()).collect(toList())
    if (mapOfValuesToCount.size == 1) return 1
    val positionOfLastValueOfFirstSubarray = sortedEntries[0].value
    val positionOfFirstValueOfLastSubarray = values.size - sortedEntries[1].value + 1
    return positionOfFirstValueOfLastSubarray - positionOfLastValueOfFirstSubarray + 1
}

