package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.2 page 315
 */
fun levenshteinDistance(from: String, to: String): Int =
    levenshteinDistanceCache(from, to).last().last()
