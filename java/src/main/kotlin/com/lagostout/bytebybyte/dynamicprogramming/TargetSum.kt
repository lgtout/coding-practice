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

    fun computeWithBruteForceAndRecursion(numbers: List<Int>, target: Int,
                                          index: Int = 0): Int {
        if (index > numbers.lastIndex) {
            return if (target == 0) 1 else 0
        }
        val number = numbers[index]
        val nextIndex = index + 1
        return computeWithBruteForceAndRecursion(numbers, target - number, nextIndex) +
                computeWithBruteForceAndRecursion(numbers, target + number, nextIndex)
    }

    data class CacheKey(val index: Int, val target: Int)

    fun computeWithRecursionAndMemoization(
            numbers: List<Int>, target: Int, index: Int = 0,
            cache: MutableMap<CacheKey, Int> = mutableMapOf()): Int {
        val cacheKey = CacheKey(index, target)
        return cache[cacheKey] ?: run {
            (if (index > numbers.lastIndex || numbers.isEmpty())
                if (target == 0) 1 else 0
            else {
                val number = numbers[index]
                val nextIndex = index + 1
                (computeWithRecursionAndMemoization(
                    numbers, target + number, nextIndex, cache) +
                        computeWithRecursionAndMemoization(
                            numbers, target - number, nextIndex, cache)).also {
                }
            }).also {
                cache[cacheKey] = it
            }
        }
    }

    @Suppress("NAME_SHADOWING")
    fun computeWithMemoizationBottomUp(numbers: List<Int>, target: Int) {
        val numbers = numbers.sorted() // This simplifies the implementation.
        val cache = HashMap<Int, MutableSet<Set<Int>>>().apply {
            put(target, mutableSetOf(emptySet()))
        }
        ((target + 1)..(target + numbers.sum())).forEach { currentTarget ->
            numbers.map { currentTarget + it }.forEach { previousTarget ->
                cache.getOrPut(previousTarget) { mutableSetOf(emptySet()) }.let {
//                    it.add()
                }
            }
//            val previousTarget
        }
    }

}
