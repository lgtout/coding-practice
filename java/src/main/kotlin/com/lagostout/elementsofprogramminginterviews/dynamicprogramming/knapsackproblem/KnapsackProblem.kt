package com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem

/* Problem 17.6.1 page 322 */

object KnapsackProblem {

    fun computeWithRecursionAndBruteForce(capacity: Int, clocks: List<Clock>): Int {
        fun compute(capacity: Int, clocks: List<Clock>, clocksIndex: Int): Int {
            return if (clocksIndex > clocks.lastIndex) 0
            else {
                Pair(clocks[clocksIndex], clocksIndex + 1).let {
                        (clock, nextClocksIndex) ->
                    listOfNotNull(
                        if (capacity >= clock.weight)
                            clock.value + compute(
                                capacity - clock.weight, clocks, nextClocksIndex)
                        else null,
                        compute(capacity, clocks, nextClocksIndex)).max() ?: 0
                }
            }
        }
        return compute(capacity, clocks, 0)
    }

    fun computeWithRecursionAndMemoization(capacity: Int, clocks: List<Clock>): Int {
        val cache = mutableMapOf<Key, Int>()
        println()
        fun compute(capacity: Int, clocks: List<Clock>, clocksIndex: Int): Int {
            val key = Key(capacity, clocksIndex)
            return cache[key] ?: when {
                clocksIndex > clocks.lastIndex -> 0
                else ->
                    Pair(clocks[clocksIndex], clocksIndex + 1).let {
                            (clock, nextClocksIndex) ->
                        listOfNotNull(
                            if (capacity >= clock.weight)
                                clock.value + compute(
                                    capacity - clock.weight, clocks, nextClocksIndex)
                            else null,
                            compute(capacity, clocks, nextClocksIndex)).max() ?: 0
                    }
            }.also {
                cache[key] = it
            }
        }
        return compute(capacity, clocks, 0)
    }

    fun computeBottomUpWithMemoization(capacity: Int, clocks: List<Clock>): Int {
        val cache = List(clocks.size) {
            MutableList(capacity + 1) { 0 }
        }
        clocks.forEachIndexed { clockIndex, clock ->
            (0..capacity).forEach { capacity ->
                cache[clockIndex][capacity] =
                        if (clockIndex == 0) {
                            // Seed the cache.
                            if (capacity >= clock.weight)
                                clock.value else 0
                        } else {
                            listOf(Pair(clockIndex - 1, capacity),
                                Pair(clockIndex - 1, capacity - clock.weight))
                                    .map {
                                        // Filter out invalid clockIndex +
                                        // capacity pairs.
                                        if (it.toList().all { it >= 0 })
                                            cache[it.first][it.second]
                                        else null
                                    }
                                    .let {
                                        // Select the max value.
                                        listOfNotNull(
                                            it[0],
                                            it[1]?.let { it + clock.value })
                                                .max() ?: 0
                                    }
                        }
            }
        }
        return cache[clocks.lastIndex][capacity]
    }

}
