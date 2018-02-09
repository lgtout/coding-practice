package com.lagostout.bytebybyte

/* Given a list of items with values and weights, as well as a max weight,
find the maximum value you can generate from items, where the sum of the
weights is less than or equal to the max.

eg.
items = {(w:1, v:6), (w:2, v:10), (w:3, v:12)}
maxWeight = 5
knapsack(items, maxWeight) = 22 */

object ZeroOneKnapsack {

    open class Item(val value: Int, val weight: Int)
    object NullItem : Item(0, 0)
    object IllegalItem : Item(0, 0)

    fun computeWithRecursion(items: Set<Item>, maxWeight: Int): Int {
        return _computeWithRecursion(
                items.toMutableSet().apply { add(NullItem) },
                maxWeight).weight
    }

    @Suppress("FunctionName")
    private fun _computeWithRecursion(items: MutableSet<Item>, maxWeight: Int): Item {
        return when {
            items == mutableSetOf(NullItem) -> NullItem
            maxWeight < 0 -> IllegalItem
            else -> items.map {
                Pair(it, _computeWithRecursion(
                        (if (it == NullItem) items else ((items - it).toMutableSet())),
                        maxWeight - it.weight))
            }.map {
                        if (it.second != IllegalItem) {
                            Item(it.first.value + it.second.value,
                                    it.first.weight + it.second.weight)
                        } else NullItem
                    }.sortedBy { it.value }.last()
        }
    }
//    fun computeWithRecursionAndMemoization() {}
//    fun computeWithRecursionAndMemoization() {}
//    fun computeWithMemoizationBottomUp() {}
}