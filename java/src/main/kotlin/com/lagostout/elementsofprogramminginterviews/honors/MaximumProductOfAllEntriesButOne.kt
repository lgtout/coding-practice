package com.lagostout.elementsofprogramminginterviews.honors

import com.lagostout.common.isOdd
import kotlin.math.sign

/* Problem 25.4.1 page 445 */

/* We apply some fancy logic, relying on what we know about the
 results of multiplying positive and negative numbers, and zero */

fun maximumProductOfAllEntriesButOne(entries: List<Int>): Int? {

    var zeroCount = 0
    var negativeNumberCount = 0
    var negativeNumberMin: Int? = null
    var negativeNumberMax: Int? = null
    var positiveNumberCount = 0
    var positiveNumberMin: Int? = null
    var positiveNumberMax: Int? = null

    entries.forEach { entry ->
        (when (entry.sign) {
            -1 -> {
                negativeNumberCount += 1
                negativeNumberMin = negativeNumberMin?.let {
                    minOf(it, entry)
                } ?: entry
                negativeNumberMax = negativeNumberMax?.let {
                    maxOf(it, entry)
                } ?: entry
            }
            1 -> {
                positiveNumberCount += 1
                positiveNumberMin = positiveNumberMin?.let {
                    minOf(it, entry)
                } ?: entry
                positiveNumberMax = positiveNumberMax?.let {
                    maxOf(it, entry)
                } ?: entry
            }
            else -> {
                zeroCount += 1
            }
        })
    }

    return when {
        (zeroCount > 0) -> {
            if (negativeNumberCount.isOdd) {
                // Remove any negative number.
                negativeNumberMin
            } else 0
        }
        else -> {
            when {
                (negativeNumberCount.isOdd) -> negativeNumberMax
                (positiveNumberCount == 0) -> negativeNumberMin
                else -> positiveNumberMin
            }
        }
    }?.let {
        // Pair(product, excludeNumber)
        entries.fold(Pair(null as Int?, true)) { acc, curr ->
            if (acc.second && curr == it) acc.copy(second = false)
            else acc.copy(first = curr * (acc.first ?: 1))
        }.first
    }

}

