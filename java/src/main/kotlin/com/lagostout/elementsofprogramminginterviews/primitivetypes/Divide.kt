package com.lagostout.elementsofprogramminginterviews.primitivetypes

/* Problem 5.6 page 53 */

fun divide(x: Int, y: Int): Int? {
    if (y == 0) return null
    if (x < y) return 0
    var dividend = x
    var divisor = y
    // Shift divisor left till its leftmost bit
    // is aligned with that of dividend.
    while (true) {
        val nextDivisor = divisor shl 1
        if (nextDivisor > dividend) break
        divisor = nextDivisor
    }
    var quotient = 0
    while (divisor >= y) {
        quotient = quotient shl 1
        if (dividend >= divisor) {
            quotient += 1
            dividend -= divisor
        }
        divisor = divisor shr 1
    }
    return quotient
}
