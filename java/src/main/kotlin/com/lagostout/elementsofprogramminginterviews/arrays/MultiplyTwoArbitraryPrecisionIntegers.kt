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
                // Extract the sign and make the number
                // absolute.
                val signum = Integer.signum(it.first())
                val integer = it.toMutableList().apply {
                    set(0, first() * signum)
                }
                Pair(signum, integer)
            }
            .fold(Pair(1, mutableListOf<List<Int>>())) { acc, pair ->
                // Compute a pair of the product of the signs
                // and a list of the absolute numbers.
                Pair(acc.first * pair.first, acc.second.apply {
                    add(pair.second)
                })
            }
    val (first, second) = numbers
    var product: Int
    first.reversed().forEachIndexed { firstIndex, firstValue ->
        var excess = 0
        var resultIndex = result.lastIndex - firstIndex
        second.reversed().forEachIndexed { secondIndex, secondValue ->
            resultIndex -= secondIndex
            product = result[resultIndex] + excess +
                    firstValue * secondValue
            excess = product / 10
            result[resultIndex] = product.rem(10)
        }
        result[resultIndex - 1] = excess
    }
    return result.let { product ->
        // Trim extra 0s on left side and set sign
        product.indexOfFirst { it != 0 }.let {
            if (it == -1) null
            else product.subList(it, product.size).also {
                it[0] = it.first() * sign
            }
        } ?: listOf(0)
    }
}
