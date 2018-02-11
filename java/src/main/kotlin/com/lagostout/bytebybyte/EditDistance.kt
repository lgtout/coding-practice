package com.lagostout.bytebybyte

import kotlin.math.max
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
    fun computeWithRecursionAndMemoizationBottomUp(
            string1: String, string2: String): Int {
        // We're converting string1 into string2.
        val cache: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
        var distanceWhenPreviousMoveWasInsert: Int
        var distanceWhenPreviousMoveWasModify: Int
        for (string1Index in min(0, string1.lastIndex)..string1.lastIndex) {
            /*
            It's impossible for a character in string1 to be aligned with one
            in string2 such that string1Index < string2Index. So we start comparing
            at string1Index within string2.  This is because only inserts or updates
            could have been applied to positions prior to string1Index, and both
            operations can only push any following characters right, such that they
            align with higher indices of string2 than they did prior to the operations.
            */
            for (string2Index in min(string1Index, string2.lastIndex)
                    ..max(string1.lastIndex, string2.lastIndex)) {
                /*
                Find out the possible edit distances we'd have to have incurred
                to reach the current alignment of the characters at string1Index
                and string2Index.  To get here, in the prior move, either we modified
                the character before string1Index in string1, or the character at
                string1Index was previously at string1Index - 1 and we did an insert
                which pushed the character to string1Index.  These are the only paths
                by which we the current alignment can be reached.
                */
                val previousPositionByInsert = Pair(string1Index, string2Index - 1)
                val previousPositionByModify = Pair(string1Index - 1, string2Index - 1)
                /*
                If string2Index is 0, it's not possible for the current alignment
                between the strings to have been reached by any series of inserts
                or updates to string1. So the edit distances by insert and modify
                are both 0.
                */
                if (listOf(previousPositionByInsert, previousPositionByModify).map {
                            it.second }.any { it < 0 }) {
                    distanceWhenPreviousMoveWasInsert = 0
                    distanceWhenPreviousMoveWasModify = 0
                } else {
                    distanceWhenPreviousMoveWasInsert = cache[previousPositionByInsert]!!
                    distanceWhenPreviousMoveWasModify = cache[previousPositionByModify]!!
                }
                /*
                If string1 is longer than string2 we must be careful not to access positions
                within string2 that are greater than string2.lastIndex.  If it is greater, we
                can assume we need to do an update to string1.  So we increment the edit distance.
                */
                cache[Pair(string1Index, string2Index)] = min(
                    distanceWhenPreviousMoveWasInsert, distanceWhenPreviousMoveWasModify) +
                        if ((string1Index > string2.lastIndex ||
                                        string2Index < 0 || string1Index < 0 ||
                                        string1[string1Index] != string1[string2Index])) 1 else 0
            }
        }
        /*
        After string1 is transformed to string2, the last position of string1 will be aligned
        with the last one of string2.  The edit distance at this alignment is the minimum edit
        distance for the entire transformation of string1.
        */
        return cache[Pair(string1.lastIndex, string2.lastIndex)]!!
    }

}
