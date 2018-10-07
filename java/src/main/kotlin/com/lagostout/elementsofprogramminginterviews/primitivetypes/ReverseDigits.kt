package com.lagostout.elementsofprogramminginterviews.primitivetypes

/* Problem 5.8 page 55 */

@Suppress("NAME_SHADOWING")
fun reverseDigits2(number: Int): Int {
    var number = number
    var result = 0
    while (true) {
        // -x % y = some negative number
        val digit = number % 10
        number /= 10
        // When number is negative, we're adding
        // negative numbers here, thus preserving
        // the negative sign.
        result += digit
        if (number == 0) break
        result *= 10
    }
    return result
}
