package com.lagostout.elementsofprogramminginterviews.hashtables

/**
 * Problem 13.10 page 229
 */
fun findLengthOfLongestContainedInterval(integers: List<Int>): Int {
    if (integers.isEmpty()) return 0
    data class Interval(var start: Int, var end: Int) {
        constructor (start: Int) : this(start = start, end = start)
        val length: Int
            get() = end - start + 1
    }
    var longestInterval = Interval(0)
    val integerToIntervalMap = mutableMapOf<Int, Interval>()
    integers.forEach { number ->
        integerToIntervalMap[number] ?: run {
            val previousNumber = number - 1
            val nextNumber = number + 1
            integerToIntervalMap[number] = Interval(number)
            val numbers = listOf(previousNumber, number, nextNumber).filter {
               it in integerToIntervalMap.keys
            }
            val interval = numbers.map { integerToIntervalMap[it]!!
            }.reduce { acc, entry -> Interval(acc.start, entry.end) }
            numbers.forEach {
                integerToIntervalMap[it] = interval
            }
            if (interval.length > longestInterval.length)
                longestInterval = interval
        }
    }
    return longestInterval.length
}