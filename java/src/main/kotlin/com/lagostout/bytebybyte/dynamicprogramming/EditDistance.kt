package com.lagostout.bytebybyte.dynamicprogramming

import kotlin.math.max
import kotlin.math.min

/* Given two strings, write a function that determines the minimum edit distance
between the two strings. You can insert and modify characters.

eg.
editDistance("ABCD", "ACBD") = 2 (ABCD->ACCD->ACBD)
editDistance("AC", "ABCD") = 2 (AC->ABC->ABCD) */

@Suppress("NAME_SHADOWING")
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

    fun computeWithMemoizationBottomUp(
            string1: String, string2: String): Int {
        val (string1, string2) = listOf(string1, string2).sortedBy { it.length }
        val cache = mutableMapOf<Pair<Int, Int>, Int>().apply {
            put(Pair(-1, 0), string2.length)
            put(Pair(-1, -1), max(string1.length, string2.length))
        }
        var string1Index = string1.lastIndex
        cache.apply {
            while (string1Index >= 0) {
                var string2Index = string2.length + string1Index
                while (string2Index >= string1Index) {
                    if (string2Index > string2.lastIndex) {
                        string1.length - string1Index
                    } else {
                        listOfNotNull(
                            Pair(string1Index + 1, string2Index + 1).let {
                                (if (it.first > string1.lastIndex &&
                                        it.second > string2.lastIndex) 0
                                else if (it.first > string1.lastIndex)
                                    // Or, string2.size - it.second
                                    string2.lastIndex - it.second + 1
                                else cache[it]!!) +
                                        if (string1[string1Index] ==
                                                string2[string2Index]) 0 else 1
                            },
                            cache[Pair(string1Index, string2Index + 1)]!! + 1).min()
                    }?.let { cache[Pair(string1Index, string2Index)] = it }
                    string2Index -= 1
                }
                string1Index -= 1
            }
        }
        return cache[Pair(min(string1.lastIndex, 0),
            min(string2.lastIndex, 0))] ?: 0
    }

    fun computeWithMemoizationBottomUpWithGrid(
            string1: String, string2: String): Int {
        val lastColIndex = string1.length
        val lastRowIndex = string2.length
        val grid = Array(lastRowIndex + 1, { row -> Array(lastColIndex + 1,
            { col -> if (row == 0) col else if (col == 0) row else 0 })
        })
        (1..lastRowIndex).forEach { rowIndex ->
            (1..lastColIndex).forEach { colIndex ->
                grid[rowIndex][colIndex] = listOf(
                    grid[rowIndex - 1][colIndex - 1] +
                            if (string1[colIndex - 1] ==
                                    string2[rowIndex - 1]) 0 else 1,
                    // Insertion in string1
                    grid[rowIndex][colIndex - 1] + 1,
                    // Insertion in string2
                    grid[rowIndex - 1][colIndex] + 1).min()!!
            }
        }
        return grid.last().last()
    }
}
