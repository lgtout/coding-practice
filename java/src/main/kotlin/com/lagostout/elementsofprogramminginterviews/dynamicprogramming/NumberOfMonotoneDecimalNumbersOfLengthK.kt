package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

object NumberOfMonotoneDecimalNumbersOfLengthK {

    fun computeWithRecursionAndBruteForce(k: Int): Int {
        return if (k == 0) 0
        else {
            val digits = (1..9)
            fun compute(k: Int, start: Int): Int {
                return when (k) {
                    0 -> 1
                    else -> (start..digits.last).map {
                        compute(k - 1, it)
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
                MutableList<Int?>(digits.last + 1) { null }
            }
            fun compute(k: Int, start: Int): Int {
                return cache[k][start] ?: when (k) {
                    0 -> 1
                    else -> (start..digits.last).map {
                        compute(k - 1, it)
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
                MutableList(digits.last + 1) { if (k == 0) 1 else null }
            }
            (1..k).forEach { k ->
                digits.reversed().forEach { digit ->
                    cache[k][digit] = (digit..digits.last()).mapNotNull {
                        cache[k-1][it]
                    }.sum()
                }
            }
            println(cache)
            cache[k][1]!!
        }
    }

}