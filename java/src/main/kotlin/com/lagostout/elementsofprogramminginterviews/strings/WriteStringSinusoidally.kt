package com.lagostout.elementsofprogramminginterviews.strings

/* Problem 7.11 page 107 */

fun writeStringSinusoidally(string: String): String {
    val steps = listOf(Pair(1,4), Pair(0,2), Pair(3,4))
    val stringBuilder = StringBuilder()
    for ((start, step) in steps) {
        var index = start
        while (index <= string.lastIndex) {
            stringBuilder.append(string[index])
            index += step
        }
    }
    return stringBuilder.toString()
}