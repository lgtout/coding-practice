package com.lagostout.elementsofprogramminginterviews.searching

/* Problem 12.7 page 201 */

fun <T : Comparable<T>> findMinMaxSimultaneously(list: List<T>): Pair<Int, Pair<T, T>?> {
    var comparisonCount = 0
    if (list.isEmpty()) return Pair(comparisonCount, null)
    var minMax: Pair<T, T>? = null
    (0..list.lastIndex step 2).forEach {
        val pair = Pair(list[it], if (it == list.lastIndex)
            list[it] else list[it + 1]).let {
            ++comparisonCount
            if (it.first > it.second) Pair(it.second, it.first) else it
        }
        minMax = minMax?.let {
            comparisonCount += 2
            Pair(minOf(pair.first, it.first),
                    maxOf(pair.second, it.second))
        } ?: pair
    }
    return Pair(comparisonCount, minMax)
}