package com.lagostout.bytebybyte.dynamicprogramming

import kotlin.math.absoluteValue
import kotlin.math.min

/* Given two strings, write a function that determines the minimum edit distance
between the two strings. You can insert and modify characters.

eg.
editDistance("ABCD", "ACBD") = 2 (ABCD->ACCD->ACBD)
editDistance("AC", "ABCD") = 2 (AC->ABC->ABCD) */

object EditDistance {

    fun computeWithRecursion(string1: String, string2: String): Int {
        return computeWithRecursion(string1, string2, 0, 0)
    }

    private fun computeWithRecursion(string1: String, string2: String,
                                     string1Index: Int, string2Index: Int): Int {
        return if (string1Index > string1.lastIndex) {
            string2.length - string2Index
        } else if (string2Index > string2.lastIndex){
            string1.length - string1Index
        } else {
            if (string1[string1Index] != string2[string2Index]) {
                min(computeWithRecursion(string1, string2,
                    string1Index, string2Index + 1), // Insertion
                    computeWithRecursion(string1, string2,
                        string1Index + 1, string2Index + 1)) + 1 // Modification
            } else {
                computeWithRecursion(string1, string2,
                    string1Index + 1, string2Index + 1) // Match
            }
        }
    }

    fun computeWithRecursionAndMemoization(
            string1: String, string2: String): Int {
        return computeWithRecursionAndMemoization(
            string1, string2, 0, 0, mutableMapOf())
    }

    private fun computeWithRecursionAndMemoization(
            string1: String, string2: String,
            string1Index: Int, string2Index: Int,
            cache: MutableMap<Pair<Int, Int>, Int>): Int {
        val indices = Pair(string1Index, string2Index)
        return cache[indices] ?: run {
            if (string1Index > string1.lastIndex) {
                string2.length - string2Index
            } else if (string2Index > string2.lastIndex){
                string1.length - string1Index
            } else {
                if (string1[string1Index] != string2[string2Index]) {
                    min(computeWithRecursion(string1, string2,
                        string1Index, string2Index + 1), // Insertion
                        computeWithRecursion(string1, string2,
                            string1Index + 1, string2Index + 1)) + 1 // Modification
                } else {
                    computeWithRecursion(string1, string2,
                        string1Index + 1, string2Index + 1) // Match
                }
            }.also {
                cache[indices] = it
            }
        }
    }

    // TODO Matrix cache implementation
    fun computeWithMemoizationBottomUp(
            string1: String, string2: String): Int {
        //  TODO Redo
        if (string1.isEmpty() || string2.isEmpty()) {
            return (string1.length - string2.length).absoluteValue
        }
        // We're converting string1 into string2.
        val cache: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
        var string1Index = if (string1.isEmpty()) - 1 else 0
        var string2Index = if (string2.isEmpty()) - 1 else 0
        while (true) {
            if (string1Index > string1.lastIndex &&
                    string2Index > string2.lastIndex) break
            val key = Pair(string1Index, string2Index)
            listOf(Pair(string1Index - 1, string2Index),
                Pair(string1Index, string2Index)).filter {
                it.toList().all { it >= 0 }
            }
        }
        return 0
    }

}
