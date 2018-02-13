package com.lagostout.bytebybyte.dynamicprogramming

import kotlin.math.min

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

    private fun computeWithRecursion(numbers: List<Int>, index: Int,
                                     sum: Int, target: Int): Int {
        if (index > numbers.lastIndex) {
            return if (sum == target) 1 else 0
        }
        val number = numbers[index]
        val nextIndex = index + 1
        return computeWithRecursion(numbers, nextIndex, sum + number, target) +
                computeWithRecursion(numbers, nextIndex, sum - number, target)
    }

    fun computeWithRecursionAndMemoization(
            numbers: List<Int>, target: Int, index: Int = min(0, numbers.lastIndex),
            cache: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()): Int {
        val cacheKey = Pair(index, target)
        return if (cache.containsKey(cacheKey)) {
            cache[cacheKey]!!
        } else {
            if (index == numbers.lastIndex) if (target == 0) 1 else 0
            else {
                val number = numbers[index]
                val nextIndex = index + 1
                (computeWithRecursionAndMemoization(
                    numbers, target + number, nextIndex, cache) +
                        computeWithRecursionAndMemoization(
                            numbers, target - number, nextIndex, cache)).also {
                    cache[cacheKey] = it
                }
            }
        }
    }

    fun computeWithMemoizationBottomUp(numbers: List<Int>, target: Int) {
        var index: Int = 0
        var sum: Int = 0
        var cache = mutableMapOf<Pair<Int, Int>, Int>()

    }

}
