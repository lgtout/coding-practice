package com.lagostout.elementsofprogramminginterviews.hashtables

/**
 * Problem 13.7.3 page 225
 */
fun rangeOfShortestSubarrayOfDistinctValues(values: List<Int>): IntRange? {
    if (values.isEmpty()) return null
    var shortestRange: IntRange = (0..0)
    val valueToPositionMap = LinkedHashMap<Int, Int>()
    values.forEachIndexed {
        index, value ->
        val valueIsNew = !valueToPositionMap.containsKey(value)
        if (!valueIsNew) {
            valueToPositionMap.remove(value)
        }
        valueToPositionMap.put(value, index)
        val positions = valueToPositionMap.values
        val newRange = positions.first()..positions.last()
        if (valueIsNew ||
                shortestRange.count() >= newRange.count()) {
            shortestRange = newRange
        }
    }
    return shortestRange
}