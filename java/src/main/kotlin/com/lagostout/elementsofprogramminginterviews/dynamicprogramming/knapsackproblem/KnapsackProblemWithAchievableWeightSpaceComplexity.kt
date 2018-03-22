package com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem

/* Problem 17.6.3 page 325 */

object KnapsackProblemWithAchievableWeightSpaceComplexity {

    fun computeBottomUpWithMemoization(capacity: Int, clocks: List<Clock>): Int {
        // Use bottom-up DP to compute the achievable capacities.
        // Use the capacities as cache keys.
        val cache = run {
            clocks.map { it.weight }.fold (mutableListOf(0)) { acc, curr ->
                acc.apply {
                    addAll(acc.mapNotNull {
                        (it + curr).let {
                            if (it > capacity) null else it
                        }
                    })
                }
           }.sorted().map {
               it to 0
           }.toMap().toMutableMap()
        }
        val reversedCacheKeys = cache.keys.reversed()
        clocks.forEachIndexed { clockIndex, clock ->
            var indexOfKeyWhenIncludingClock = 0
            reversedCacheKeys.forEach { capacity ->
                cache[capacity] =
                        if (clockIndex == 0) {

                            // Seed the cache.

                            if (capacity >= clock.weight)
                                clock.value else 0
                        } else {
                            Pair(capacity, capacity - clock.weight) .let {
                                    (capacityWhenNotIncludingClock,
                                            capacityWhenIncludingClock) ->

                                // Not including the clock

                                val valueWhenNotIncludingClock = if (
                                capacityWhenNotIncludingClock >= 0 ) {
                                    cache[capacityWhenNotIncludingClock]
                                } else null

                                // Including the clock

                                val valueWhenIncludingCurrentClock = run {

                                    // Find the capacity before we include the current clock.

                                    while (indexOfKeyWhenIncludingClock <=
                                            reversedCacheKeys.lastIndex &&
                                            reversedCacheKeys[
                                                    indexOfKeyWhenIncludingClock] >
                                            capacityWhenIncludingClock) {
                                        indexOfKeyWhenIncludingClock += 1
                                    }
                                    if (indexOfKeyWhenIncludingClock >
                                            reversedCacheKeys.lastIndex) null
                                    else cache[reversedCacheKeys[
                                            indexOfKeyWhenIncludingClock]]?.let {
                                        it + clock.value }
                                }

                                listOfNotNull(valueWhenNotIncludingClock,
                                    valueWhenIncludingCurrentClock).max() ?: 0
                            }
                        }
            }
        }
        return cache[cache.keys.last()]  ?: 0
    }

}