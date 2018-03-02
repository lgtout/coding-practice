@file:Suppress("FunctionName")

package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.common.takeFrom
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/* Given a list of items with values and weights, as well as a max weight,
find the maximum value you can generate from items, where the sum of the
weights is less than or equal to the max.

eg.
items = {(v:6, w:1), (v:10, w:2), (v:12, w:3)}
maxWeight = 5
knapsack(items, maxWeight) = 22 */

object ZeroOneKnapsack {

    class Item(val value: Int, val weight: Int) {
        override fun toString(): String {
            return ToStringBuilder.reflectionToString(
                this, ToStringStyle.NO_CLASS_NAME_STYLE)
        }
        companion object {
            fun i(value: Int, weight: Int): Item {
                return Item(value, weight)
            }
        }
    }

    fun computeWithRecursion(
            items: Set<Item>, maxWeight: Int,
            computeWithRecursionImpl: (Set<Item>, Int) -> Int?): Int? {
        return computeWithRecursionImpl(items, maxWeight) ?: 0
    }

    /*
     Negative knapsack capacity is a base case, returning null.
     As a result, we return an optional.
     We explore every ordering of items.
     We handle an empty items set in the recursion case.
     */
    fun computeWithRecursion1(items: Set<Item>, maxWeight: Int): Int? {
        return when {
            maxWeight == 0 -> return 0 +
                    // Add in values of any zero-weight items.
                    items.filter { it.weight == 0 }.sumBy { it.value }
            maxWeight < 0 -> return null
            else -> {
                var maxValue = 0
                items.forEach { item ->
                    (computeWithRecursion1(items - item, maxWeight - item.weight)?.let {
                        item.value + it
                    } ?: 0).let {
                        maxValue = if (it > maxValue) it else maxValue
                    }
                }
                maxValue
            }
        }
    }

    /*
     Negative knapsack capacity is not a base case.  We check for this case before
     recursing.
     As a result, we do not return an optional.
     We explore every ordering of items.
     We handle an empty items set in the recursion case.
     */
     fun computeWithRecursion2(items: Set<Item>, maxWeight: Int): Int {
        return when (maxWeight) {
            0 -> return 0 +
                    items.filter { it.weight == 0 }.sumBy { it.value }
            else -> {
                items.filter { maxWeight >= it.weight }.map { item ->
                    computeWithRecursion2(items - item, maxWeight - item.weight).let {
                        item.value + it
                    }
                }.max() ?: 0
            }
        }
    }

    /*
     Negative knapsack capacity is a base case, returning null.
     As such, the function returns an optional.
     We explore every ordering of items.
     We handle an empty items set in a base case.
     */
    fun computeWithRecursion3(items: Set<Item>, maxWeight: Int): Int? {
        return when {
            maxWeight >= 0 && items.isEmpty() -> return 0
            maxWeight < 0 -> return null
            else -> {
                items.map { item ->
                    computeWithRecursion3(items - item, maxWeight - item.weight)?.let {
                        item.value + it
                    } ?: 0
                }.max()
            }
        }
    }

    /*
     Negative knapsack capacity is a base case, returning null.
     As a result, we return an optional.
     We explore every ordering of items.
     We handle an empty items set in the recursion case.
     */
     fun computeWithRecursion4(items: Set<Item>, maxWeight: Int): Int? {
        return when {
            maxWeight == 0 -> return 0 +
                    items.filter { it.weight == 0 }.sumBy { it.value }
            maxWeight < 0 -> return null
            else -> {
                items.mapNotNull { item ->
                    computeWithRecursion4(items - item, maxWeight - item.weight)?.let {
                        item.value + it
                    }
                }.max() ?: 0
            }
        }
    }

    /*
     Negative knapsack capacity is not a base case.
     As a result, we do not return an optional.
     We explore every outcome of a choice to include or not include
     an item in the knapsack.
     We handle an empty items set in the recursion case.
     Shows manual debugging technique.
     */
     fun computeWithRecursion5(items: Set<Item>, maxWeight: Int): Int {
        return when (maxWeight) {
            0 -> return 0 +
                    items.filter { it.weight == 0 }.sumBy { it.value }
            else -> {
                (if (items.isEmpty()) null
                else {
                    val nextItems = items.takeFrom(1).toSet()
                    val item = items.first()
                    listOfNotNull(
                        (maxWeight - item.weight).let {
                            if (it >= 0)
                                computeWithRecursion5(nextItems, it) + item.value
                            else null },
                        computeWithRecursion5(nextItems, maxWeight)
                    ).max()
                }) ?: 0
            }
        }
    }

    fun computeWithRecursionAndMemoization(
            items: List<Item>, maxWeight: Int, itemIndex: Int = 0,
            cache: MutableMap<Pair<Int, Int>, Int?> = mutableMapOf()): Int? {
        val key = Pair(itemIndex, maxWeight)
        return if (cache.containsKey(key)) cache[key] else
            when {
                maxWeight >= 0 && itemIndex > items.lastIndex -> 0
                maxWeight < 0 -> null
                else -> {
                    Pair(items[itemIndex], itemIndex + 1).let { (item, nextIndex) ->
                        listOfNotNull(
                            computeWithRecursionAndMemoization(
                                items, maxWeight, nextIndex, cache),
                            computeWithRecursionAndMemoization(
                                items, maxWeight - item.weight, nextIndex, cache)
                                    ?.let { it + item.value }).max()
                    }
                }
            }.also {
                cache[key] = it
            }
    }

    /* Illustrates (considerable number of) explicit variable declarations
    needed for debugging by hand */
    fun computeWithRecursionAndMemoizationForManualDebugging(
            items: List<Item>, maxWeight: Int, itemIndex: Int = 0,
            cache: MutableMap<Pair<Int, Int>, Int?> = mutableMapOf()): Int? {
        println("itemIndex $itemIndex, item ${if (itemIndex <= items.lastIndex) items[itemIndex] else null}, " +
                "maxWeight $maxWeight, cache $cache")
        val key = Pair(itemIndex, maxWeight)
        val cacheContainsResult = cache.containsKey(key)
        return if (cacheContainsResult) cache[key].also {
            println("hit $key = $it")
        }!!
        else when {
            maxWeight >= 0 && itemIndex > items.lastIndex -> 0
            maxWeight < 0 -> null
            else -> {
                val noMoreItems = itemIndex > items.lastIndex
                if (noMoreItems) 0
                else {
                    val item = items[itemIndex]
                    val nextIndex = itemIndex + 1
                    val valueWithoutItem = computeWithRecursionAndMemoizationForManualDebugging(
                        items, maxWeight, nextIndex, cache)
                    val remainingCapacity = maxWeight - item.weight
                    val valueWithItem = computeWithRecursionAndMemoizationForManualDebugging(
                            items, remainingCapacity, nextIndex, cache)
                            ?.let { it + item.value }
                    val values = listOfNotNull(valueWithoutItem, valueWithItem)
                    val bestValue = if (values.isEmpty()) null else values.max()
                    bestValue
                }
            }
        }.also {
            cache[key] = it
        }
    }

    /*
    At each weight we store:
    - the max value so far
    - the sets of _un_used items so far
    */
    // Failing tests - not worth fixing.
    fun computeWithMemoizationBottomUp1(items: Set<Item>, maxWeight: Int): Int {
        // Map of used capacity to unused items and accumulated value.
        val cache = mutableMapOf<Int, MutableSet<CacheValue1>>()
        cache[0] = mutableSetOf(CacheValue1(items, 0))
        var maxValue = 0
        (0..maxWeight).forEach { weight ->
            cache[weight]?.toList()?.forEach { (remainingItems, value) ->
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
    // Failing tests - not worth fixing.
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

    /*
    A very functional approach. Compared with the others, it seems much easier to
    reason about and debug, once one is familiar with what fold, map etc do.  There's
    also less code.  The advantage of this approach derives from variables being
    strictly local, vastly limiting their state space compared with either a partially
    function approach that still relies on side-effects outside of the currently executing
    scope, or one that is entirely imperative.
    */
    fun computeWithMemoizationBottomUp_functional(items: List<Item>, maxWeight: Int) : Int {
        // Map of knapsack weight to max value
        return items.fold(mapOf(0 to 0).withDefault { 0 }) { cache, item ->
            cache.flatMap {
                listOf(it.toPair(), Pair(item.weight + it.key, item.value + it.value))
            }.fold(mutableMapOf<Int, Int>().withDefault { 0 }) { acc, pair ->
                acc.also {
                    if (pair.first <= maxWeight &&
                            pair.second >= acc.getValue(pair.first))
                        acc[pair.first] = pair.second
                }.also {
                    println(acc)
                }
            }
        }.values.max() ?: 0
    }

}