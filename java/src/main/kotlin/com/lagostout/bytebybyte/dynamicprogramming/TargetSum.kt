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
        val cache = mutableMapOf<Int, MutableSet<List<Int>>>().apply {
            put(0, mutableSetOf(numbers))
        }
        var subSums = listOf(0)
        while (subSums.isNotEmpty()) {
//            subSums = subSums.map { subSum ->
//                println("subSum $subSum")
//                cache[subSum]?.let {
//                    it.forEach { list ->
//                        listOf(subSum + list.first(), subSum - list.first()).also {
//                            it.forEach {
//                                cache.getOrPut(it) { mutableSetOf() }.also {
//                                    it.add(list.takeFrom(1))
//                                }
//                            }
//                        }
//                    }
//                }
//            }
        }
//        return cache[target]!![numbers.lastIndex] ?: 0
        return 0
    }

}
