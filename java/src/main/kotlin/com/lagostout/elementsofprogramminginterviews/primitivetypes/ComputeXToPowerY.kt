package com.lagostout.elementsofprogramminginterviews.primitivetypes

/* Problem 5.7 page 54 */

fun computeXToPowerY(x: Double, y: Int): Double {
    var result = 1.0
    var power = y
    var multipleOfX = 1.0
    while (power > 0) {
        multipleOfX *= (if (multipleOfX == 1.0)
            x else multipleOfX)
        if (power.and(1) == 1) {
            result *= multipleOfX
        }
        power = power shr 1
    }
    return result
}