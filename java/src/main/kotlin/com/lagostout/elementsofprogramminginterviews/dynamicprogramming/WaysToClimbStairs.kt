package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

fun countOfWaysToClimbStairs(stairCount: Int, maxStepsAtATime: Int): Int {
    if (stairCount == 0) return 0
    val cache = mutableListOf(1)
    1.rangeTo(stairCount).forEach { stair ->
        var ways = 0
        1.rangeTo(maxStepsAtATime).forEach { steps ->
            val previousStair = stair - steps
            ways += if (previousStair >= 0)
                cache[previousStair]
            else 0
        }
        cache.add(ways)
    }
    return cache[stairCount]
}
