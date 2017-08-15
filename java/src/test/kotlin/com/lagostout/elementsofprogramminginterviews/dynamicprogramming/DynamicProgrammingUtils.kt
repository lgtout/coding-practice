package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

// Recursion in either direction - comparing the strings from left-right,
// or from right-left - are equally valid solutions.  However, the non-recursive
// dynamic programming solution is modeled on the right-left recursive solution.

fun bruteForceLeftToRightLevenshteinDistance(from: String, to: String): Int {
    if (from.isEmpty()) return to.length
    if (to.isEmpty()) return from.length
    val substituteOrNoActionCost = bruteForceLeftToRightLevenshteinDistance(
            from.substring(1), to.substring(1)) +
            (if (from.first() == to.first()) 0 else 1)
    val deleteCost = bruteForceLeftToRightLevenshteinDistance(from.substring(1), to) + 1
    val insertCost = bruteForceLeftToRightLevenshteinDistance(from, to.substring(1)) + 1
    return listOf(substituteOrNoActionCost, deleteCost, insertCost).min()!!
}

fun bruteForceRightToLeftLevenshteinDistance(from: String, to: String): Int {
    if (from.isEmpty()) return to.length
    if (to.isEmpty()) return from.length
    val substituteOrNoActionCost = bruteForceRightToLeftLevenshteinDistance(
            from.dropLast(1), to.dropLast(1)) +
            (if (from.last() == to.last()) 0 else 1)
    val deleteCost = bruteForceRightToLeftLevenshteinDistance(from.dropLast(1), to) + 1
    val insertCost = bruteForceRightToLeftLevenshteinDistance(from, to.dropLast(1)) + 1
    return listOf(substituteOrNoActionCost, deleteCost, insertCost).min()!!
}
