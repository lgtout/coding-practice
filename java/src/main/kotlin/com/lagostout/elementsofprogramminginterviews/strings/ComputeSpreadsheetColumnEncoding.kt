package com.lagostout.elementsofprogramminginterviews.strings

fun numericEncodingOfColumn(alphabeticEncoding: String): Int {
    if (alphabeticEncoding.isEmpty())
        throw IllegalArgumentException(
                "Alphabetic encoding must be one or more characters")
    alphabeticEncoding.find { !it.isLetter() }?.apply {
        println("****")
        println(alphabeticEncoding)
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