package com.lagostout.bytebybyte.dynamicprogramming

object LongestIncreasingSubsequence {

    fun computeWithBruteForceAndRecursion(
            integers: List<Int>, index: Int = 0,
            min: Int? = null): Int {
        return if (index > integers.lastIndex) 0
        else {
            // If we're at index = 0, include the value in the
            // subsequence.  Otherwise, only include the value
            // if it's greater than the min.
            mutableListOf<Int>().apply {
                if (min == null || integers[index] > min) {
                    add(computeWithBruteForceAndRecursion(
                        integers, index + 1, integers[index]) + 1)
                }
                // Maintain the existing min.
                add(computeWithBruteForceAndRecursion(
                    integers, index + 1, min))
            }.max()!!
        }
    }

}