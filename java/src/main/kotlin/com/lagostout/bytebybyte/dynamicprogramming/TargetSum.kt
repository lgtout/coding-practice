package com.lagostout.bytebybyte.dynamicprogramming

/* Given an array of integers, nums and a target value T, find the number of
ways that you can add and subtract the values in nums to add up to T.

eg.
nums = {1, 2, 3, 4}
target = 0

1 - 2 - 3 + 4
-1 + 2 + 3 - 4

targetSum(nums, target) = 2 */

object TargetSum {

    fun computeWithRecursion(numbers: List<Int>, target: Int): Int {
        return computeWithRecursion(numbers, 0, 0, target)
    }

    private fun computeWithRecursion(numbers: List<Int>, index: Int, sum: Int, target: Int): Int {
        if (index > numbers.lastIndex) {
            return if (sum == target) 1 else 0
        }
        val number = numbers[index]
        val nextIndex = index + 1
        return computeWithRecursion(numbers, nextIndex, sum + number, target) +
                computeWithRecursion(numbers, nextIndex, sum + number, target)
    }

//    fun computeWithRecursion() {}
//    fun computeWithRecursionAndMemoization() {}
//    fun computeWithMemoizationBottomUp() {}
}
