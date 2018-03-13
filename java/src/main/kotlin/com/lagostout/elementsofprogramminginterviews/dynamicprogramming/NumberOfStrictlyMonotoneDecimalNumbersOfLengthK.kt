package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/* Problem 17.3.6 page 319 */

object NumberOfStrictlyMonotoneDecimalNumbersOfLengthK {

    fun computeWithRecursionAndBruteForce(k: Int): Int {
        return if (k == 0) 0
        else {
            val digits = (1..9)
            fun compute(k: Int, start: Int): Int {
                return when (k) {
                    0 -> 1
                    else -> (start..(digits.last - k + 1)).map {
                        compute(k - 1, it + 1)
                    }.sum()
                }
            }
            compute(k, 1)
        }
    }

    fun computeWithRecursionAndMemoization(k: Int): Int {
        return if (k == 0) 0
        else {
            val digits = (1..9)
            val cache = List(k + 1) {
                MutableList<Int?>(digits.last + 2) { null }
            }
            fun compute(k: Int, start: Int): Int {
                return cache[k][start] ?: when (k) {
                    0 -> 1
                    else -> (start..(digits.last - k + 1)).map {
                        compute(k - 1, it + 1)
                    }.sum()
                }.also {
                    cache[k][start] = it
                }
            }
            compute(k, 1)
        }
    }

    @Suppress("NAME_SHADOWING")
    fun computeBottomUpWithMemoization(k: Int): Int {
        return if (k == 0) 0 else {
            val digits = (1..9)
            val cache = List(k + 1) { k ->
                MutableList(digits.last + 2) { if (k == 0) 1 else null }
            }
            (1..k).forEach { k ->
                digits.reversed().forEach { digit ->
                    cache[k][digit] = (digit..(digits.last - k + 1)).mapNotNull {
                        cache[k-1][it + 1]
                    }.sum()
                }
            }
            cache[k][1]!!
        }
    }

}