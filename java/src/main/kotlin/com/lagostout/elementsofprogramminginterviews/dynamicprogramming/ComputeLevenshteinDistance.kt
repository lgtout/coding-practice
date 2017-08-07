package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.2 page 315
 */
fun levenshteinDistance(from: String, to: String): Int {

    (if (from.isEmpty()) to.length
    else if (to.isEmpty()) from.length
    else null)?.apply {
        return this
    }

    val cache = mutableListOf<MutableList<Int>>()

    // Build the cache
    (0..to.length).forEach {
        val row = mutableListOf<Int>()
        cache.add(row)
    }

    // 0th row and column are the positions before
    // the first character of each string.  The first
    // character of each string is at index 1 in the
    // cache.

    // Populate first row
    val firstRow = cache[0]
    firstRow += (0..from.length)

    // Populate first column, starting from second
    // row.
    for (i in (1..to.length)) {
        cache[i].add(i)
    }

    // Move left to right, top to bottom
    // computing cache values.
    for (j in (1..to.length)) { // rows
        for (i in (1..from.length)) { // columns
            val substituteOrNoActionCost = cache[j-1][i-1] +
                    if (from[i-1] == to[j-1]) 0 else 1
            val deleteCost = cache[j][i-1] + 1 // Moving from left
            val insertCost = cache[j-1][i] + 1 // Moving from above
            listOf(substituteOrNoActionCost,
                    deleteCost, insertCost).min()?.apply {
                cache[j].add(this)
            }
        }
    }

    return cache[to.length][from.length]
}