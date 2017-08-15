package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.2.2 page 316
 */
fun levenshteinDistanceInWorstCaseSpaceOfShorterStringLength(from: String, to: String): Int {
    val (shorterString, longerString) = listOf(from, to).sorted()
    val cache = mutableListOf<Int>().apply {
        (0..shorterString.length).forEach {
            add(it)
        }
    }
    for (i in 1..longerString.length) {
        var upperLeftDistance = cache[0]
        for (j in 0..shorterString.length) {
            if (j == 0) {
                cache[0]++
            } else {
                val deleteDistance = cache[j] + 1
                val insertDistance = cache[j-1] + 1
                val substituteOrDoNothingDistance =
                        upperLeftDistance +
                                if (shorterString[j-1] == longerString[i-1])
                                    0  else 1
                upperLeftDistance = cache[j]
                listOf(deleteDistance, insertDistance,
                        substituteOrDoNothingDistance).min()?.let {
                    cache[j] = it
                }
            }
        }
    }
    return cache.last()
}