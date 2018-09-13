package com.lagostout.bytebybyte.recursion

data class Item(val value: Int, val weight: Int)

/* We'll assume that no items are duplicates. */

fun knapsack(items: List<Item>, maxWeight: Int): Int {
    data class State(val value: Int, val weight: Int,
                             val remainingItems: Set<Item>) {
        fun nextStates(): Set<State> {
            return remainingItems.map {
                State(value + it.value, weight + it.weight,
                    remainingItems.minus(it))
            }.toSet()
        }
    }
    fun compute(state: State, maxValue: Int): Int {
        if (state.weight > maxWeight) return maxValue
        else if (state.weight == maxWeight)
            return maxOf(state.value, maxValue)
        var result = maxOf(state.value, maxValue)
        state.nextStates().forEach {
            result = compute(it, result)
        }
        return result
    }
    return compute(State(0, 0, items.toSet()), 0)
}