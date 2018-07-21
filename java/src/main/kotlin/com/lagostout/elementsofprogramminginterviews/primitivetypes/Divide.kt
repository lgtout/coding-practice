package com.lagostout.elementsofprogramminginterviews.primitivetypes

/* Problem 5.6 page 53 */

fun divide(x: Int, y: Int): Int {
    var multiplier = 1
    var dividend = x
    var divisor = y
    while (multiplier shl 1 <= x) {
        multiplier = multiplier shl 1
        divisor = divisor shl 1
    }
    var quotient = 0
    while (dividend >= y) {
        if (divisor <= dividend) {
            quotient += multiplier
            dividend -= divisor
        }
        divisor = divisor shr 1
        multiplier = multiplier shr 1
    }
    return quotient
}
