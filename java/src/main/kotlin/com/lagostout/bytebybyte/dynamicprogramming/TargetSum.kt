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

    // TODO
    fun computeWithMemoizationBottomUp(numbers: List<Int>, target: Int): Int {
        if (numbers.isEmpty()) return if (target == 0) 1 else 0
        val cache = mutableMapOf<Int, MutableMap<Int, Int>>().apply {
            put(0, mutableMapOf(0 to 1))
        }
        var subSums = listOf(0)
        numbers.forEachIndexed { index, number ->
            subSums = subSums.flatMap { subSum ->
                println("subSum $subSum")
                listOf(subSum + number, subSum - number).also {
//                    println(it)
                    it.forEach {
                        cache.getOrPut(it) {
                            mutableMapOf()
                        }.also { it[index] = (it[index] ?: it[index - 1] ?: 0)
                                    + (cache[subSum]!![index] ?:
                                    cache[subSum]!![index - 1] ?: 0) }
                    }.also { println(it) }
                }
            }
//            println("subSums $subSums")
//            println("cache $cache")
        }
        return cache[target]!![numbers.lastIndex] ?: 0
    }

}
