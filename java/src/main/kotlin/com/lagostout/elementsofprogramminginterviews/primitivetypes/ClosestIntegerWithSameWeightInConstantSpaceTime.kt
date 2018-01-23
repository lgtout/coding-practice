package com.lagostout.elementsofprogramminginterviews.primitivetypes

/* Problem 5.4.2 page 50 */

fun findClosestIntegerWithSameWeightInConstantSpaceTime(number: Int): Int {
    if (number == 0) return 0
    val rightmostZeroBit = number.let {
        (it + 1) and it.inv()
    }
    return if (rightmostZeroBit == 1) {
        val rightmostOneBit = number.let {
            it and (it - 1).inv()
        }
        (number xor rightmostOneBit) or
                (rightmostOneBit ushr 1)
    } else {
        val leftmostOneBit = rightmostZeroBit ushr 1
        (number xor leftmostOneBit) or rightmostZeroBit
    }
}