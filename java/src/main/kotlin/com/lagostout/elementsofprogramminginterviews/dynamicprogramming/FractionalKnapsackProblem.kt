package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import java.math.RoundingMode
import kotlin.math.max

/* Problem 17.6.4 page 325 */

object FractionalKnapsackProblem {

    data class Item(val weight: Int, val value: Int)

    fun computeByValueDensity(capacity: Int, items: List<Item>): Double {
        return items.sortedByDescending {
            if (it.weight == 0) Double.MAX_VALUE
            else it.value.toDouble() / it.weight
        }.also {
//            println(it)
        }.fold(Pair(0.0, 0.0)) { (weight, value), item ->
            when {
                item.weight == 0 || weight + item.weight <= capacity -> Pair(weight + item.weight, value + item.value)
                weight < capacity -> Pair(capacity.toDouble(),
                    value + (item.value.toDouble() / item.weight)
                            * (capacity - weight))
                else -> Pair(weight, value)
            }
        }.second.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()
    }

    // Generate every possible ordering of items.  This will give every item
    // the opportunity to contribute the fractional part.
    fun computeWithRecursionAndBruteForce(capacity: Int, items: List<Item>): Double {
        fun compute(capacity: Int, unusedItemIndices: Set<Int> = emptySet()): Double {
            return if (unusedItemIndices.isEmpty()) 0.0
            else {
                unusedItemIndices.map { itemIndex ->
                    items[itemIndex].let {
                        val nextIndices = unusedItemIndices - itemIndex
                        when {
                            it.weight == 0 || it.weight <= capacity ->
                                compute(capacity - it.weight, nextIndices) + it.value
                            it.weight >= capacity ->
                                compute(0, nextIndices) +
                                        ((capacity / it.weight.toDouble()) *
                                                it.value)
                            else -> compute(capacity - it.weight, nextIndices) +
                                    it.value
                        }
                    }
                }.max() ?: 0.0
            }
        }
        return compute(capacity, items.indices.toSet())
                .toBigDecimal().setScale(2, RoundingMode.HALF_UP)
                .toDouble()
    }

    fun computeWithRecursionAndMemoization(capacity: Int, items: List<Item>): Double {
        val cache = mutableMapOf<Set<Int>, Double>()
        fun compute(capacity: Int, unusedItemIndices: Set<Int> = emptySet()): Double {
            return cache[unusedItemIndices]?.also {
//                println("hit: $unusedItemIndices value: $it")
            } ?: when {
                unusedItemIndices.isEmpty() -> 0.0
                else -> unusedItemIndices.map { itemIndex ->
                    items[itemIndex].let {
                        val nextIndices = unusedItemIndices - itemIndex
                        when {
                            it.weight == 0 || it.weight <= capacity ->
                                compute(capacity - it.weight, nextIndices) + it.value
                            else ->
                                compute(0, nextIndices) +
                                        ((capacity / it.weight.toDouble()) *
                                                it.value)
                        }
                    }
                }.max() ?: 0.0
            }.also {
                cache[unusedItemIndices] = it
            }
        }
        return compute(capacity, items.indices.toSet())
                .toBigDecimal().setScale(2, RoundingMode.HALF_UP)
                .toDouble()
    }

    fun computeBottomUpWithMemoization(capacity: Int, items: List<Item>): Double {
        val cache = mutableMapOf<Set<Int>, Double>().apply {
            put(emptySet(), 0.0)
        }
        items.indices.forEach { cacheKeySize ->
            val cacheKeys = cache.keys.filter {
                it.size ==  cacheKeySize
            }
            items.indices.forEach { itemIndex ->
                cacheKeys.filter {
                    itemIndex !in it
                }.map { usedItemIndices ->
                    Pair(usedItemIndices + itemIndex, run {
                        val item = items[itemIndex]
                        val usedItemsWeight = usedItemIndices.sumBy {
                            items[it].weight
                        }
                        cache.getOrDefault(usedItemIndices, 0.0) + when {
                            item.weight == 0 ||
                                    usedItemsWeight + item.weight <= capacity ->
                                item.value.toDouble()
                            usedItemsWeight < capacity ->
                                (item.value / item.weight.toDouble()) *
                                        (capacity - usedItemsWeight)
                            else -> 0.0
                        }
                    })
                }.forEach {
                    cache.merge(it.first, it.second) { t: Double, u: Double ->
                        max(t, u)
                    }
                }
            }
        }
        return cache.filterKeys {
            it.size == items.size
        }.map {
            it.value
        }.max()?.toBigDecimal()
                ?.setScale(2, RoundingMode.HALF_UP)?.toDouble()
                ?: 0.0
    }

}