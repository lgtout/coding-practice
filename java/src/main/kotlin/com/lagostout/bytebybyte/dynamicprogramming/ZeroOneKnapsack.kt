package com.lagostout.bytebybyte.dynamicprogramming

/* Given a list of items with values and weights, as well as a max weight,
find the maximum value you can generate from items, where the sum of the
weights is less than or equal to the max.

eg.
items = {(v:6, w:1), (v:10, w:2), (v:12, w:3)}
maxWeight = 5
knapsack(items, maxWeight) = 22 */

object ZeroOneKnapsack {

    data class Item(val value: Int, val weight: Int) {
        companion object {
            fun i(value: Int, weight: Int): Item {
                return Item(value, weight)
            }
        }
    }

    fun computeWithRecursion(items: Set<Item>, maxWeight: Int): Int {
        return _computeWithRecursion(items, maxWeight) ?: 0
    }

    @Suppress("FunctionName")
    private fun _computeWithRecursion(items: Set<Item>, maxWeight: Int): Int? {
        return when {
            maxWeight == 0 -> return 0
            maxWeight < 0 -> return null
            else -> {
                var maxValue = 0
                items.forEach { item ->
                    (_computeWithRecursion(items - item, maxWeight - item.weight)?.let {
                        item.value + it
                    } ?: 0).let {
                        maxValue = if (it > maxValue) it else maxValue
                    }
                }
                maxValue
            }
        }
    }
    fun computeWithRecursionAndMemoization(
            items: Set<Item>, maxWeight: Int): Int {
        val cache = mutableMapOf<Set<Item>, Int?>()
        computeWithRecursionAndMemoization(
            items, maxWeight, cache)
        return cache[items] ?: 0
    }

    fun computeWithRecursionAndMemoization(
            items: Set<Item>, maxWeight: Int,
            cache: MutableMap<Set<Item>, Int?>): Int? {
        return cache[items] ?: when {
            maxWeight == 0 -> 0
            maxWeight < 0 -> null
            else -> {
                var maxValue = 0
                items.forEach { item ->
                    (_computeWithRecursion(items - item, maxWeight - item.weight)?.let {
                        item.value + it
                    } ?: 0).let {
                        maxValue = if (it > maxValue) it else maxValue
                    }
                }
                maxValue
            }
        }.also {
            cache[items] = it
        }
    }

    fun computeWithMemoizationBottomUp(items: Set<Item>, maxWeight: Int): Int {
        val cache = mutableMapOf<Set<Item>, Int>()
        val itemSubsets = mutableSetOf<Set<Item>>().apply { add(emptySet())}
        while (true) {
            items.forEach {

            }
        }
        return 0
    }

}