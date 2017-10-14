package com.lagostout.elementsofprogramminginterviews.hashtables

/**
 * Problem 13.10 page 229
 */
fun findLengthOfLongestContainedInterval(integers: List<Int>): Int {
    var lengthOfLongestInterval = 0
    data class Interval(var start: Int, var end: Int) {
        constructor (start: Int) : this(start = start, end = start)
        val length: Int
            get() = end - start + 1
    }
    val integerToIntervalMap = mutableMapOf<Int, Interval>()
    integers.forEach { number ->
        integerToIntervalMap[number] ?: run {
            val previousNumber = number - 1
            val nextNumber = number + 1
            val numbers = listOf(previousNumber, number, nextNumber)
            integerToIntervalMap[number] =
                    numbers.mapNotNull {
                        integerToIntervalMap[previousNumber]
                    }.reduce { acc, entry -> Interval(acc.start, entry.end) }
            // I think it's OK to add keys to the map when the keys are
            // numbers adjacent in counting order to the current one, but
            // the numbers aren't actually entries in the provided list. Or,
            // they are entries, but haven't yet been encountered in iteration.
            numbers.forEach {
                integerToIntervalMap[it] = integerToIntervalMap[number]!!
            }
        }
    }
    // TODO Run through map intervals and find longest one or integrate this step into main algorithm.
    return lengthOfLongestInterval
}