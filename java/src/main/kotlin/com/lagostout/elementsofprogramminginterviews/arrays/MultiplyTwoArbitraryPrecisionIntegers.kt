package com.lagostout.elementsofprogramminginterviews.arrays

/**
 * Problem 6.3 page 66
 */
@Suppress("NAME_SHADOWING")
fun multiplyTwoArbitraryPrecisionIntegers(
        first: List<Int>, second: List<Int>): List<Int> {
    if (first.isEmpty()) return second
    else if (second.isEmpty()) return first
    val result = MutableList(first.size + second.size, { 0 })
    val (sign, numbers) = listOf(first, second)
            .filter { it.isNotEmpty() }
            .map {
                val negative = if (it.first() < 0) -1 else 1
                val integer = it.toMutableList().apply {
                    set(0, first() * negative)
                }
                Pair(negative, integer)
            }
            .fold(Pair(1, mutableListOf<List<Int>>())) { acc, pair ->
                Pair(acc.first * pair.first, acc.second.apply {
                    add(pair.second)
                })
            }
    val (first, second) = numbers
    var product: Int
    var excess = 0
    first.reversed().forEachIndexed { firstIndex, firstValue ->
        second.reversed().forEachIndexed { secondIndex, secondValue ->
            val resultIndex = result.lastIndex -
                    (firstIndex + secondIndex)
            product = result[resultIndex] + excess +
                    firstValue * secondValue
            excess = product / 10
            result[resultIndex] = product - excess
        }
    }
    return result.let { sum ->
        // Trim extra 0s on left side and set sign
        sum.find { it != 0 }?.let {
            sum.subList(it, sum.size).also {
                it[0] = it.first() * sign
            }
        } ?: listOf(0)
    }
}
