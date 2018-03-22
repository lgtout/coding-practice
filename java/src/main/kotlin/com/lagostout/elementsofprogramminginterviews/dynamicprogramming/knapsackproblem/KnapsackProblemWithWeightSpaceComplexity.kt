package com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem

/* Problem 17.6.2 page 325 */

object KnapsackProblemWithWeightSpaceComplexity {

    fun computeBottomUpWithMemoization(capacity: Int, clocks: List<Clock>): Int {
        val cache = MutableList(capacity + 1) { 0 }
        clocks.forEachIndexed { clockIndex, clock ->
            (capacity downTo 0).forEach { capacity ->
                cache[capacity] =
                        if (clockIndex == 0) {
                            // Seed the cache.
                            if (capacity >= clock.weight)
                                clock.value else 0
                       } else {
                            listOf(capacity, capacity - clock.weight)
                                    .map {
                                        // Filter out invalid clockIndex +
                                        // capacity pairs.
                                        if (it >= 0 )
                                            cache[it]
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
        return cache[capacity]
    }

}