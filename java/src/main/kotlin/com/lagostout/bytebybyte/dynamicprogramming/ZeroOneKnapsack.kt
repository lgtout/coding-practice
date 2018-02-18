package com.lagostout.bytebybyte.dynamicprogramming

/* Given a list of items with values and weights, as well as a max weight,
find the maximum value you can generate from items, where the sum of the
weights is less than or equal to the max.

eg.
items = {(v:6, w:1), (v:10, w:2), (v:12, w:3)}
maxWeight = 5
knapsack(items, maxWeight) = 22 */

object ZeroOneKnapsack {

    class Item(val value: Int, val weight: Int) {
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

    /*
    At each weight we store:
    - the max value so far
    - the sets of _un_used items so far
    */
    fun computeWithMemoizationBottomUp1(items: Set<Item>, maxWeight: Int): Int {
        val cache = mutableMapOf<Int, MutableSet<CacheValue1>>()
        cache[0] = mutableSetOf(CacheValue1(items, 0))
        var maxValue = 0
        (0..maxWeight).forEach { weight ->
            cache[weight]?.forEach { (remainingItems, value) ->
                remainingItems.forEach {
                    val nextWeight = weight + it.weight
                    cache.getOrPut(nextWeight, { mutableSetOf() })
                            .add(CacheValue1(remainingItems - it, value + it.value).also {
                                if (nextWeight <= maxWeight && it.value > maxValue)
                                    maxValue = it.value
                            })
                }
            }
        }
        return maxValue
    }

    data class CacheValue1(val remainingItems: Set<Item>, val value: Int)

    /*
    Alternate approach that better captures the spirit of bottom-up, compared
    with the previous solution.  At each weight we see the similar state to what
    we would have if we were solving top down recursively.  We see: the capacity
    of the knapsack (this changes as items are added in bottom-up, and removed in
    top-down), the items added to the knapsack so far, and their total value.

    At each weight we store:
    - the max value so far (CacheValue2.maxValue)
    - the sets of used items so far (CacheValue2.allItems)
    - the possibility of reaching each weight exactly by some
      combination of items (indicated by an empty set value for CacheValue2.allItems)
    */
    fun computeWithMemoizationBottomUp2(items: Set<Item>, maxWeight: Int): Int {
        val cache = mutableMapOf<Int, CacheValue2>()
        cache[0] = CacheValue2(mutableSetOf(Pair(emptySet(), 0)))
        (0..maxWeight).forEach { weight ->
            cache.getOrPut(weight) {
                CacheValue2(mutableSetOf(), cache[weight - 1]!!.maxValue)
            }.let { value ->
                value.allItems.forEach { usedItems ->
                    items.forEach {
                        if (it !in usedItems.first) {
                            cache.getOrPut(weight + it.weight) {
                                CacheValue2(mutableSetOf())
                            }.add(usedItems, it)
                        }
                    }
                }
            }
        }
        return cache[maxWeight]!!.maxValue
    }

    data class CacheValue2(val allItems: MutableSet<Pair<Set<Item>, Int>>,
                           var maxValue: Int = 0) {
        fun add(items: Pair<Set<Item>, Int>, item: Item) {
            allItems.add(Pair(items.first + item, (items.second + item.value).also {
                if (it > maxValue) maxValue = it
            }))
        }
    }

}