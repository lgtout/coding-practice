package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

fun levenshteinDistance(from: String, to: String): Int? {

    (if (from.isEmpty()) to.length
    else if (to.isEmpty()) from.length
    else null)?.apply {
        return this
    }

    val cache = mutableListOf<MutableList<Int>>()

    // Build the cache
    0.rangeTo(to.length).forEach {
        val row = mutableListOf<Int>()
        cache.add(row)
    }

    // 0th row and column are the positions before
    // each of the two strings.  The first character
    // of each string is at index 1 in the cache.

    // Populate first row
    val firstRow = cache[0]
    firstRow += (0..from.length)

    // Populate first column
    for (i in (1..to.length)) {
        cache[i].add(i)
    }

    // Move left to right, top to bottom
    // computing cache values.
    for (j in (1..to.length)) {
        for (i in (1..from.length)) {
            val substituteOrNoActionCost = if (from[i-1] == to[j-1]) {
                cache[i-1][j-1]
            } else {
                cache[i-1][j-1] + 1
            }
            val deleteCost = cache[i+1][j] + 1
            val insertCost = cache[i][j-1] + 1
            listOf(substituteOrNoActionCost,
                    deleteCost, insertCost).min()?.apply {
                cache[i][j] = this
            }
        }
    }

    return cache[to.length][from.length]
}