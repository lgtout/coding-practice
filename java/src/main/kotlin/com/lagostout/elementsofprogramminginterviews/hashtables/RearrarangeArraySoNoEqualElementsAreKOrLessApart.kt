package com.lagostout.elementsofprogramminginterviews.hashtables

/**
 * Problem 13.7.5 page 225
 */
fun rearrangeArraySoNoEqualElementsAreKOrLessApart(
        array: List<Int>, k: Int): List<Int> {
    val sortedArray = mutableListOf<Int>()
    if (array.isEmpty()) return sortedArray
    val valueCount = array.size
    val valueToCountMap = array.groupingBy { it }.eachCount().toMutableMap()
    val indexToAvailableValueMap = mutableMapOf<Int, Int>()
    val allAvailableValues = mutableSetOf<Int>()
            .apply { addAll(valueToCountMap.keys) }
    val availableValues: MutableSet<Int> = allAvailableValues
    0.rangeTo(valueCount).forEach {
        // If a value has been freed up in this new position,
        // let's add it to our set of available values.
        // TODO We still need to prioritize which value we select by occurrence count.
        // Maybe availableValues should be a map of counts to values.
        // Or, for O(n), we could have a list of sets of values, where
        // we move sets to ever lower indices, where an index represents
        // a value's remaining occurrence count.
        indexToAvailableValueMap[it]?.let {
            availableValues.add(it)
        }
        availableValues.apply {
            val value = first()
            remove(value)
            valueToCountMap[value] =- 1
            // Bump value to next index when it's available
            if (valueToCountMap[value]!! > 0) {
                val nextAvailableIndex = it + k
                indexToAvailableValueMap[nextAvailableIndex] = value
            }
            return@forEach
        }
        // There's a problem:  We've run out of available values.
        throw IllegalArgumentException("Array cannot be arranged so no " +
                "equal elements k or less apart")
    }
    return sortedArray
}