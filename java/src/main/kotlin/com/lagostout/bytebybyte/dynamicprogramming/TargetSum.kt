@file:Suppress("FunctionName")

package com.lagostout.bytebybyte.dynamicprogramming

import org.apache.commons.collections4.bag.HashBag

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
        return _computeWithBruteForceAndRecursion(numbers, target).count()
    }

    /* Allows repeats */
    private fun _computeWithBruteForceAndRecursion(
            numbers: List<Int>, target: Int, numberIndex: Int = 0): List<List<Int>> {
        println(numberIndex)
        return if (numberIndex > numbers.lastIndex)
            if (target == 0) listOf(emptyList()) else emptyList()
        else {
            numbers[numberIndex].let { number ->
                val nextNumberIndex = numberIndex + 1
                listOfNotNull(
                    _computeWithBruteForceAndRecursion(
                        numbers, target - number, nextNumberIndex).map {
                        mutableListOf(-number) + it
                    },
                    _computeWithBruteForceAndRecursion(
                        numbers, target + number, nextNumberIndex).map {
                        mutableListOf(number) + it
                    }
                ).flatMap {
                    it
                }.distinctBy {
                    HashBag(it)
                }
            }
        }
    }

    fun computeWithRecursionAndMemoization(
            numbers: List<Int>, target: Int, numberIndex: Int = 0,
            cache: MutableMap<Pair<Int, Int>, MutableList<List<Int>>> = mutableMapOf()):
            List<List<Int>> {
        val key = Pair(target, numberIndex)
        return cache[key] ?: run {
            (if (numberIndex > numbers.lastIndex)
                if (target == 0)
                    listOf(emptyList())
                else emptyList()
            else {
                numbers[numberIndex].let { number ->
                    val nextNumberIndex = numberIndex + 1
                    listOfNotNull(
                        computeWithRecursionAndMemoization(
                            numbers, target - number, nextNumberIndex, cache).map {
                            mutableListOf(-number) + it
                        }.also {
//                            println(it)
                        },
                        computeWithRecursionAndMemoization(
                            numbers, target + number, nextNumberIndex, cache).map {
                            mutableListOf(number) + it
                        }.also {
//                            println(it)
                        }
                    ).flatMap {
                        it
                    }.distinctBy {
                        HashBag(it)
                    }
                }
            }).also { result ->
                cache.getOrPut(key, { mutableListOf() }).addAll(result)
            }
        }.also {
            println("cache $cache")
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
