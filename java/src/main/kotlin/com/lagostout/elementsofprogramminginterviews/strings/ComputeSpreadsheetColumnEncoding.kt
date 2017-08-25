package com.lagostout.elementsofprogramminginterviews.strings

/**
 * Problem 7.3.1 page 98
 */
fun numericEncodingOfColumn(alphabeticEncoding: String): Int {
    if (alphabeticEncoding.isEmpty())
        throw IllegalArgumentException(
                "Alphabetic encoding must contain at least one character")
    alphabeticEncoding.find { !it.isLetter() }?.apply {
        throw IllegalArgumentException(
                "Alphabetic encoding must contain only letters") }
    var magnitude = 1
    var numericEncoding = 0
    alphabeticEncoding.capitalize().reversed().forEach {
        numericEncoding += (it - 'A' + 1) * magnitude
        magnitude *= 26
    }
    return numericEncoding
}