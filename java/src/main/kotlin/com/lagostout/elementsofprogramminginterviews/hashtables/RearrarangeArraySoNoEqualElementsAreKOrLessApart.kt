package com.lagostout.elementsofprogramminginterviews.hashtables

/**
 * Problem 13.7.5 page 225
 */
fun rearrangeArraySoNoEqualElementsAreKOrLessApart(
        array: List<Int>, k: Int): List<Int> {
    val sortedArray = mutableListOf<Int>()
    if (array.isEmpty()) return sortedArray
    val countToValueMap = array.groupingBy { it }.eachCount().
            asIterable().groupBy( { it.value }, { it.key }).
            toMutableMap().mapValues(
            { it.value.toMutableSet().iterator() })
    val currentCount = countToValueMap.keys.max()!!
    // Maybe maintain 2 maps?  Would that be simpler than trying to
    // merge used values into value sets of lower counts?
    while (currentCount > 0) {
        var remainingK = k
        val nextCount = currentCount
        while (remainingK > 0) {
            val values = countToValueMap[currentCount]!!
            val usedValues = mutableSetOf<Int>()
            values.forEach {
                usedValues.add(it)
                values.remove()
            }
            if (usedValues.size < remainingK) {
                remainingK =- usedValues.size
            }
            var currentK = 0
            while (currentK < k) {
                val value = values.next()
            }
//            val kValues = values.take(k)
//            if (values.)
//            val nextValues = countToValueMap.getOrDefault(
//                    nextCount, mutableListOf())
//            nextValues.addAll(kValues)
//            remainingK -= values.size
        }
    }
    return sortedArray
}