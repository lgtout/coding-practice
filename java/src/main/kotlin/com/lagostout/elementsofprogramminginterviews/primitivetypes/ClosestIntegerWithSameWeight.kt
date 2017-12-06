package com.lagostout.elementsofprogramminginterviews.primitivetypes

/**
 * Problem 5.4.1 page 50
 */
fun findClosestIntegerWithSameWeight(number: Int): Int {
    // Find position of rightmost 0
    val rightMostZeroBitMask = (number.inv() - 1).let {
        it.inv().and(it)
    }
    if (rightMostZeroBitMask.and(1) > 0) {
        // Rightmost 0 bit is rightmost bit.
        // Find position of rightmost 1 bit.
        
    } else {

    }
    return 0
}