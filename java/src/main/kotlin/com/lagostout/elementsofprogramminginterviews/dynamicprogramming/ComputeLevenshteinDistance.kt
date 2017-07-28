package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

fun levenshteinDistance(from: String, to: String): Int {
    var distance = -1
    val cache = mutableListOf<MutableList<Int>>()
    0.rangeTo(to.length).forEach {
        val row = mutableListOf<Int>()
        cache.add(row)
    }
    val firstRow = cache[0]
    0.rangeTo(from.length).forEach {
        firstRow.add(it)
    }
    0.rangeTo(to.length).forEach {
        cache[it].add(it)
    }
    return distance
}