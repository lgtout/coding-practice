@file:Suppress("FunctionName")

package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.common.mergeReduce

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
        }
    }

    fun computeWithRecursionAndMemoization1(
            numbers: List<Int>, target: Int, numberIndex: Int = 0): Int {
        return _computeWithRecursionAndMemoization1(numbers, numberIndex).getOrDefault(target, 0)
    }

    private fun _computeWithRecursionAndMemoization1(
            numbers: List<Int>, numberIndex: Int = 0,
            cache: MutableMap<Int, Map<Int, Int>> = mutableMapOf()): Map<Int, Int> {
        return (if (numberIndex > numbers.lastIndex) mapOf(0 to 1)
        else {
            cache[numberIndex] ?: run {
                numbers[numberIndex].let { number ->
                    val nextNumberIndex = numberIndex + 1
                    Pair(_computeWithRecursionAndMemoization1(
                        numbers, nextNumberIndex, cache).mapKeys {
                        it.key - number
                    },
                    _computeWithRecursionAndMemoization1(
                        numbers, nextNumberIndex, cache).mapKeys {
                        it.key + number
                    }).let {
                        it.first.mergeReduce(it.second, { a, b -> a + b })
                    }.also {
                        cache[numberIndex] = it
                    }
                }
            }.let { it }
        })
    }

    fun computeWithRecursionAndMemoization2(
            numbers: List<Int>, target: Int, numberIndex: Int = 0,
            cache: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()): Int {
        val key = Pair(numberIndex, target)
        return cache[key] ?: run {
            if (numberIndex > numbers.lastIndex) {
                if (target == 0) 1 else 0
            } else {
                val number = numbers[numberIndex]
                val nextNumberIndex = numberIndex + 1
                computeWithRecursionAndMemoization2(
                    numbers, target - number, nextNumberIndex, cache) +
                        computeWithRecursionAndMemoization2(
                            numbers, target + number, nextNumberIndex, cache)
            }
        }.also {
            cache[key] = it
        }
    }

    /* Maintain a cache per number index. Max cache entries is the number of leaves
    at the bottom of the choice tree. */
    fun computeWithMemoizationBottomUp1(numbers: List<Int>, target: Int): Int {
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
        }
        return cache[target] ?: 0
    }

    /* Maintain a single cache.  Max cache entries is the total number of nodes in the
    choice tree. */
    fun computeWithMemoizationBottomUp2(numbers: List<Int>, target: Int): Int {
        if (numbers.isEmpty()) return if (target == 0) 1 else 0
        val cache = mutableMapOf<Int, MutableMap<Int,Int>>().apply {
            put(-1, mutableMapOf(0 to 1))
        }
        numbers.forEachIndexed { index, number ->
            cache[index - 1]?.forEach { (target, count) ->
                cache.getOrPut(index, { mutableMapOf() }).let { targetToCountMap ->
                    listOf(number, -number).map { it + target }.forEach {
                        targetToCountMap[it] = count + (targetToCountMap[it] ?: 0)
                    }
                }
            }
        }
        return cache[numbers.lastIndex]?.get(target) ?: 0
    }
}
