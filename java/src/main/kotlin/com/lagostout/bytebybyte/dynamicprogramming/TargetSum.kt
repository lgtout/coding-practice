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
            numbers: List<Int>, target: Int): Set<Set<Int>> {
        return if (numbers.isEmpty())
            if (target == 0) setOf(emptySet()) else emptySet()
        else {
            numbers.flatMapTo(mutableSetOf()) { number ->
                listOf(Pair(computeWithBruteForceAndRecursion(
                    numbers - number, target - number), -number),
                    Pair(computeWithBruteForceAndRecursion(
                        numbers - number, target + number), number)).filter {
                    it.first.isNotEmpty() }.flatMapTo(mutableSetOf()) {
                        pair -> pair.first.map { it + pair.second } }
            }
        }
    }

    fun computeWithRecursionAndMemoization(
            numbers: List<Int>, target: Int,
            cache: MutableMap<Int, MutableMap<List<Int>, Set<Set<Int>>>> =
            mutableMapOf()): Set<Set<Int>> {
//        return if (numbers.isEmpty())
//            if (target == 0) mutableSetOf(emptySet()) else emptySet()
//        else {
//            cache.getOrPut(target) { mutableMapOf() }.also {
//                it[numbers] ?: run {
//                    it
//                    numbers.flatMapTo(mutableSetOf()) { number ->
//                        listOf(Pair(computeWithRecursionAndMemoization(
//                            numbers - number, target - number, cache), -number),
//                            Pair(computeWithRecursionAndMemoization(
//                                numbers - number, target + number, cache), number)).filter {
//                            it.first.isNotEmpty() }.flatMapTo(mutableSetOf()) {
//                                pair -> pair.first.map { it + pair.second } }
//                    }
//                }
//            }
//        }
        return emptySet()
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
