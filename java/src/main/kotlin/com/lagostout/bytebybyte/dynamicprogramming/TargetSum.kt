@file:Suppress("FunctionName")

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

    fun computeWithBruteForceAndRecursion(
            numbers: List<Int>, target: Int): Int {
        return _computeWithBruteForceAndRecursion(numbers, target)
    }

    /* Allows repeats */
    private fun _computeWithBruteForceAndRecursion(
            numbers: List<Int>, target: Int, numberIndex: Int = 0): Int {
        return if (numberIndex > numbers.lastIndex)
            if (target == 0) 1 else 0
        else {
            numbers[numberIndex].let { number ->
                val nextNumberIndex = numberIndex + 1
                _computeWithBruteForceAndRecursion(
                    numbers, target - number, nextNumberIndex) +
                        _computeWithBruteForceAndRecursion(
                            numbers, target + number, nextNumberIndex)
            }
        }.also { println("target: $target result: $it") }
    }

    fun computeWithRecursionAndMemoization(
            numbers: List<Int>, target: Int, numberIndex: Int = 0,
            cache: MutableMap<Int, Int> = mutableMapOf()): Int {
        return cache[numberIndex] ?: run {
            (if (numberIndex > numbers.lastIndex)
                if (target == 0) 1 else 0
            else {
                numbers[numberIndex].let { number ->
                    val nextNumberIndex = numberIndex + 1
                    computeWithRecursionAndMemoization(
                        numbers, target - number, nextNumberIndex, cache) +
                            computeWithRecursionAndMemoization(
                                numbers, target + number, nextNumberIndex, cache)
                }
            }).also {
                cache[numberIndex] = (cache[numberIndex] ?: 0) + it
                println(it)
            }
        }.also {
            println("cache $cache, numberIndex $numberIndex")
        }
    }

    fun computeWithMemoizationBottomUp(numbers: List<Int>, target: Int): Int {
        if (numbers.isEmpty()) return if (target == 0) 1 else 0
        var cache = mapOf(0 to 1)
        numbers.forEach { number ->
            val nextCache = mutableMapOf<Int, Int>()
            cache.forEach { (key, value) ->
                listOf(key + number, key - number).forEach {
                    nextCache[it] = value + (nextCache[it] ?: 0)
                }
            }
            cache = nextCache
            println(cache)
        }
        return cache[target] ?: 0
    }

}
