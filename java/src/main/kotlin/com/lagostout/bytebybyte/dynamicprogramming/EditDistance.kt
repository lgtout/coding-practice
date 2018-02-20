package com.lagostout.bytebybyte.dynamicprogramming

import kotlin.math.max

/* Given two strings, write a function that determines the minimum edit distance
between the two strings. You can insert and modify characters.

eg.
editDistance("ABCD", "ACBD") = 2 (ABCD->ACCD->ACBD)
editDistance("AC", "ABCD") = 2 (AC->ABC->ABCD) */

object EditDistance {

    fun computeWithBruteForceAndRecursion(
            string1: String, string2: String,
            string1Index: Int = 0, string2Index: Int = 0): Int {
        return when {
            string1Index > string1.lastIndex -> string2.length - string2Index
            string2Index > string2.lastIndex -> string1.length - string1Index
            else -> listOfNotNull(
                // Insertion
                computeWithRecursionAndMemoization(string1, string2,
                    string1Index, string2Index + 1) + 1,
                // Modification or match.
                // There's no need to handle match as a separate case because,
                // with both match and modification, the substrings that we
                // examine next are the same: the substrings starting at
                // string1Index + 1 and string2Index + 1.
                computeWithRecursionAndMemoization(string1, string2,
                    string1Index + 1, string2Index + 1) +
                        if (string1[string1Index] == string2[string2Index])
                            0 else 1
            ).min()!!
        }
    }

    fun computeWithRecursionAndMemoization(
            string1: String, string2: String,
            string1Index: Int = 0, string2Index: Int = 0,
            cache: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()): Int {
        val indices = Pair(string1Index, string2Index)
        return cache[indices] ?: run {
            (when {
                string1Index > string1.lastIndex -> string2.length - string2Index
                string2Index > string2.lastIndex -> string1.length - string1Index
                else -> listOfNotNull(
                    // Insertion
                    computeWithRecursionAndMemoization(string1, string2,
                        string1Index, string2Index + 1) + 1,
                    // Modification or match
                    computeWithRecursionAndMemoization(string1, string2,
                        string1Index + 1, string2Index + 1) +
                            if (string1[string1Index] == string2[string2Index])
                                0 else 1
                ).min()!!
            }).also {
                cache[indices] = it
            }
        }
    }

    fun computeWithRecursionAndMemoization2(
            string1: String, string2: String,
            string1Index: Int = 0, string2Index: Int = 0,
            cache: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()): Int {
        val indices = Pair(string1Index, string2Index)
        return cache[indices] ?: run {
            (when {
                string1Index > string1.lastIndex &&
                        string2Index > string2.lastIndex -> 0
//                string2Index > string2.lastIndex -> string1.length - string1Index
                else -> listOfNotNull(
                    // Insertion
                    if (string2Index <= string2.lastIndex)
                        computeWithRecursionAndMemoization2(string1, string2,
                            string1Index, string2Index + 1) + 1
                    else null,
                    // Modification or match
                    if (string1Index <= string1.lastIndex)
                        computeWithRecursionAndMemoization2(string1, string2,
                            string1Index + 1, string2Index + 1) +
                                if (string1[string1Index] == string2[string2Index])
                                    0 else 1
                    else null
                ).min()!!
            }).also {
                cache[indices] = it
            }
        }
    }

    fun computeWithMemoizationBottomUp(
            string1: String, string2: String): Int {
        val cache = mutableMapOf<Pair<Int, Int>, Int>().apply {
            putAll(listOf(
                // Update or match
                Pair(-1, -1) to 0,
                // Insertion
                Pair(-1, 0) to 1))
        }
        val maxStringIndex = max(string1.lastIndex, string2.lastIndex) // 3
        var string1Index = 0
        var string2Index = string1Index // 0
        while (true) {
            if (listOf(string1Index, string2Index).all { // (0,0)
                        it > maxStringIndex }) break // false
            while (true) {
                if (string2Index + (string1.lastIndex - string1Index) > // 1 + (3 - 0) = 3
                        maxStringIndex) break // >= 3 = false
                Pair(string1Index, string2Index).let { key -> // (0,0)
                    val previousKeyByMatchOrUpdate =
                            Pair(string1Index - 1, string2Index - 1) // (-1,-1)
                    val previousKeyByInsert = Pair(string1Index, string2Index - 1) // (0,-1)
                    if (string1Index > string1.lastIndex || // 0 > 3 = false
                            string2Index > string2.lastIndex) { // 0 > 3 = false
                        cache[previousKeyByMatchOrUpdate]?.let { // 0
                            cache[key] = it + 1 // c(0,0) = 0 + 1 = 1
                        }
                    } else {
                        listOfNotNull(
                            cache[previousKeyByMatchOrUpdate],
                            cache[previousKeyByInsert]).min()?.let {
                            cache[key] = it + if (string1[string1Index] ==
                                    string2[string2Index]) 0 else 1
                        }
                    }
                }
                string2Index += 1 // 1
            }
            string1Index += 1
            string2Index = string1Index
        }
        println(cache)
        return cache[Pair(maxStringIndex, maxStringIndex)]!!
    }

}
