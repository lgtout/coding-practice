package com.lagostout.elementsofprogramminginterviews.strings

fun numericEncodingOfColumn(alphabeticEncoding: String): Int {
    if (alphabeticEncoding.isEmpty())
        throw IllegalArgumentException(
                "Alphabetic encoding must be one or more characters")
    alphabeticEncoding.find { !it.isDigit() }.apply {
        throw IllegalArgumentException(
                "Alphabetic encoding must contain only digits") }
    var magnitude = 1
    var numericEncoding = 0
    alphabeticEncoding.capitalize().reversed().forEach {
        numericEncoding += (it - 'A' + 1) * magnitude
        magnitude *= 26
    }
    return numericEncoding
}