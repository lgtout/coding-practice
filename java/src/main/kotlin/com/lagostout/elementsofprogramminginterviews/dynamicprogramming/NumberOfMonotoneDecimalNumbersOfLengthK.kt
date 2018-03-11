package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

object NumberOfMonotoneDecimalNumbersOfLengthK {

    fun computeWithRecursionAndBruteForce(k: Int): Int {
        return if (k == 0) 0
        else {
            fun compute(k: Int, start: Int): Int {
                return when (k) {
                    0 -> 1
                    else -> (start..(9 - k + 1)).map {
                        compute(k - 1, it)
                    }.sum()
                }
            }
            compute(k, 0)
        }
    }

}