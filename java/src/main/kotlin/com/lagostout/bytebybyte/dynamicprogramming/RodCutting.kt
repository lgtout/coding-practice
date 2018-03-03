package com.lagostout.bytebybyte.dynamicprogramming

object RodCutting {

    fun computeWithRecursionAndBruteForce(len: Int, prices: List<Int>): Int {
        println(len)
        return if (len == 0) 0
        else {
            prices.withIndex().map { (index, price) ->
                (index + 1).let {
                    Pair(price, len - it)
                }
            }.filter { it.second >= 0 }.map {
                computeWithRecursionAndBruteForce(
                    it.second, prices) + it.first
            }.max() ?: 0
        }
    }

    fun computeWithRecursionAndMemoization(
            len: Int, prices: List<Int>,
            cache: MutableMap<Int, Int> = mutableMapOf()): Int {
        return cache[len].also {
//            if (it != null) println("Hit: len $len, price $it")
        } ?: run {
            if (len == 0) 0
            else {
                prices.withIndex().map { (index, price) ->
                    (index + 1).let {
                        Pair(price, len - it)
                    }
                }.filter { it.second >= 0 }.map {
                    computeWithRecursionAndMemoization(
                        it.second, prices, cache) + it.first
                }.max() ?: 0
            }
        }.also {
//            println(cache)
            cache[len] = it
        }
    }

    fun computeBottomUpWithMemoization(
        len: Int, prices: List<Int>): Int {
        val cache = mutableMapOf<Int, Int>().apply {
            put(0, 0)
        }
        (1..len).forEach { subLen ->
            cache[subLen] = prices.withIndex().map { (index, price) ->
                (index + 1).let {
                    Pair(price, subLen - it)
                }
            }.filter { it.second >= 0 }.map {
                it.first + (cache[it.second] ?: 0)
            }.max() ?: 0
        }
        return cache[len] ?: 0
    }

}