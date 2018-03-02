package com.lagostout.bytebybyte.dynamicprogramming

/*
Given a 2xN floor and 2x1 tiles, determine the number of ways that you can tile the floor.
eg.
floorTiling(3) = 3
floorTiling(5) = 8
*/

object FloorTiling {

    fun computeWithRecursionAndBruteForce(n: Int): Int {
        return if (n == 0) 1
        else {
            listOf(n-1, n-2).filter { it >= 0 }.map {
                computeWithRecursionAndBruteForce(it)
            }.sum()
        }
    }

    fun computeWithRecursionAndMemoization(
            n: Int, cache: MutableMap<Int, Int> = mutableMapOf()): Int {
        return cache[n] ?: run {
            if (n == 0) 1
            else listOf(n-1, n-2).filter { it >= 0 }.map {
                computeWithRecursionAndMemoization(it, cache)
            }.sum()
        }.also {
            cache[n] = it
        }
    }

    fun computeBottomUpWithMemoization(n: Int): Int {
        val cache = mutableMapOf<Int, Int>().apply {
            put(0, 1)
        }
        (1..n).map { subN ->
            cache[subN] = listOf(subN-1, subN-2).filter { it >= 0 }.mapNotNull {
                cache[it]
            }.sum()
        }
        return cache[n] ?: 0
    }

}