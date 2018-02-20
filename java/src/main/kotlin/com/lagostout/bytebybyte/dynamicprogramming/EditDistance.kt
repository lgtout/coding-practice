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
        val cache = mutableMapOf<Pair<Int, Int>, Int>()

        // TODO Figure out how to translate recursion with memoization to bottom-up.
        (max(string1.lastIndex, string2.lastIndex)..0).forEach {

        }

        return cache[Pair(string1.lastIndex, string2.lastIndex)]!!
    }

}
