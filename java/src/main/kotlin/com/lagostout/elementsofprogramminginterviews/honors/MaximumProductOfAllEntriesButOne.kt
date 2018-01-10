package com.lagostout.elementsofprogramminginterviews.honors

import kotlin.math.sign

/* Problem 25.4.1 page 445 */

fun maximumProductOfAllEntriesButOne(entries: List<Int>) {
    var leastPositiveNumber: Int? = null
    var leastNegativeNumber: Int? = null
    var mostNegativeNumber: Int? = null
    var product = 1
    var zeroCount = 0
    entries.forEach { entry ->
        when (entry.sign) {
            -1 -> {
                leastNegativeNumber = leastNegativeNumber?.let {
                    (it < entry).let { swap ->
                        product *= if (swap) it else entry
                        if (swap) entry else leastNegativeNumber
                    }
                } ?: entry
                mostNegativeNumber = mostNegativeNumber?.let {
                    (it > entry).let { swap ->
                        product *= if (swap) it else entry
                        if (swap) entry else mostNegativeNumber
                    }
                } ?: entry
            }
            1 -> {
                leastPositiveNumber = leastPositiveNumber?.let {
                    (it > entry).let { swap ->
                        product *= if (swap) it else entry
                        if (swap) entry else mostNegativeNumber
                    }
                } ?: entry
            }
            0 -> {
                ++zeroCount
            }
        }
    }
    // TODO Revisit
    when (zeroCount) {
        0 -> {
            when (product.sign) {
                1 -> {

                }
            }
        }
        else -> {

        }
    }
}