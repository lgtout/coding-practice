package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

fun levenshteinDistanceInWorstCaseSpaceOfShorterStringLength(from: String, to: String): Int {
    val (shorterString, longerString) = listOf(from, to).sorted().apply {
        Pair(first(), get(1))
    }
    val cache = mutableListOf<Int>().apply {
        (0..shorterString.length).forEach {
            add(it)
        }
    }
    for (i in 0..longerString.length) {
        var oldPreviousRowDistance: Int
        for (j in 0..shorterString.length) {
            if (j == 0) {
                oldPreviousRowDistance = cache[0]
                cache[0]++
            } else {

            }
        }
    }
    return 0
}