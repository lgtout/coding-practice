package com.lagostout.bytebybyte.dynamicprogramming

/* Given two strings, write a function that determines the minimum edit distance
between the two strings. You can insert and modify characters.

eg.
editDistance("ABCD", "ACBD") = 2 (ABCD->ACCD->ACBD)
editDistance("AC", "ABCD") = 2 (AC->ABC->ABCD) */

object EditDistance {

    fun computeWithBruteForceAndRecursion(
            string1: String, string2: String,
            string1Index: Int = 0, string2Index: Int = 0): Int {
        return if (string1Index > string1.lastIndex) {
            string2.length - string2Index
        } else if (string2Index > string2.lastIndex){
            string1.length - string1Index
        } else {
            listOfNotNull(
                // Insertion
                computeWithRecursionAndMemoization(string1, string2,
                    string1Index, string2Index + 1) + 1,
                // Modification
                computeWithRecursionAndMemoization(string1, string2,
                    string1Index + 1, string2Index + 1) + 1,
                // Match
                if (string1[string1Index] == string2[string2Index])
                    computeWithBruteForceAndRecursion(string1, string2,
                        string1Index + 1, string2Index + 1) else null
            ).min()!!
        }
    }

    fun computeWithRecursionAndMemoization(
            string1: String, string2: String,
            string1Index: Int = 0, string2Index: Int = 0,
            cache: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()): Int {
        val indices = Pair(string1Index, string2Index)
        return cache[indices] ?: run {
            if (string1Index > string1.lastIndex) {
                string2.length - string2Index
            } else if (string2Index > string2.lastIndex){
                string1.length - string1Index
            } else {
                listOfNotNull(
                     // Insertion
                    computeWithRecursionAndMemoization(string1, string2,
                    string1Index, string2Index + 1) + 1,
                    // Modification
                    computeWithRecursionAndMemoization(string1, string2,
                        string1Index + 1, string2Index + 1) + 1,
                    // Match
                    if (string1[string1Index] == string2[string2Index])
                        computeWithBruteForceAndRecursion(string1, string2,
                            string1Index + 1, string2Index + 1) else null
                ).min()!!
            }.also {
                cache[indices] = it
            }
        }
    }

    fun computeWithMemoizationBottomUp(
            string1: String, string2: String): Int {
        val cache = mutableMapOf<Pair<Int, Int>, Int>()
        cache[Pair(-1, -1)] = 0
        cache[Pair(-1, 0)] = 1
        cache[Pair(0, -1)] = 1
        var key = Pair(0, 0)
        while (true) {
            while (true) {
                listOfNotNull(
                    // Insertion
                    cache[(key.copy(second = key.second - 1))],
                    // Modification or Match
                    cache[(Pair(key.first - 1, key.second - 1))]
                ).min()!! +
                        if (string1[key.first] == string2[key.second])
                            0 else 1
                if (key.second + 1 > string2.lastIndex) {
                    cache[key] = string1.length - key.first
                    break
                } else {
                    key = key.copy(second = key.second + 1)
                }
            }
            if (key.first + 1 > string1.lastIndex) {
                cache[key] = string2.length - key.second
                break
            } else {
                key = key.copy(first = key.first + 1)
            }
        }
        return cache[Pair(string1.lastIndex, string2.lastIndex)]!!
    }

}
