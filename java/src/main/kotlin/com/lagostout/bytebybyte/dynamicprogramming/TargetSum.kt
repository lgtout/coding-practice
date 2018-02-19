package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.common.takeFrom

/* Given an array of integers, nums and a target value T, find the number of
ways that you can add and subtract the values in nums to add up to T.

eg.
nums = {1, 2, 3, 4}
target = 0

1 - 2 - 3 + 4
-1 + 2 + 3 - 4

targetSum(nums, target) = 2 */

object TargetSum {

    // TODO Allow repeats
    fun computeWithBruteForceAndRecursion(
            numbers: List<Int>, target: Int): Int {
        return if (numbers.isEmpty())
            if (target == 0) 1 else 0
        else {
            numbers.first().let {
                val nextNumbers = numbers.takeFrom(1)
                listOfNotNull(computeWithBruteForceAndRecursion(nextNumbers, target - it),
                    computeWithBruteForceAndRecursion(nextNumbers, target + it)).sum()
            }
        }
    }

    fun computeWithRecursionAndMemoization(
            numbers: List<Int>, target: Int, cache: MutableMap<List<Int>, Int> =
            mutableMapOf()): Int {
        return cache[numbers] ?: run {
            if (numbers.isEmpty())
                if (target == 0) 1 else 0
            else {
                numbers.first().let { number ->
                    numbers.takeFrom(1).let {
                        listOfNotNull(computeWithBruteForceAndRecursion(it, target - number),
                            computeWithBruteForceAndRecursion(it, target + number)).sum()
                    }
                }
            }.also {
                cache[numbers] = it
            }
        }
    }

    fun computeWithMemoizationBottomUp(numbers: List<Int>, target: Int): Int {
        if (numbers.isEmpty()) return if (target == 0) 1 else 0
        var cache = mapOf<Int, List<List<Int>>>(0 to listOf(emptyList()))
        (0..numbers.lastIndex).forEach { numbersIndex ->
            cache = cache.flatMap { (subTarget, subNumbersSet) ->
                numbers[numbersIndex].let { nextNumber ->
                    subNumbersSet.flatMap {
                        listOf(Pair(it + nextNumber, subTarget + nextNumber),
                            Pair(it + -nextNumber, subTarget - nextNumber))
                    }
                }.let { it }
            }.groupBy({ it.second }, { it.first }).let { it }.toMap()
        }
        return cache[target]?.size ?: 0
    }

}
