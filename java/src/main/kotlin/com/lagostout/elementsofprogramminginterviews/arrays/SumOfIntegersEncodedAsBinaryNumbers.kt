package com.lagostout.elementsofprogramminginterviews.arrays

/**
 * Problem 6.2.2 page 66
 */
fun sumOfIntegersEncodedAsBinaryNumbers(firstInt: String, secondInt: String): String {
    var indexOffset = 0
    var excess = 0
    val stringBuilder = StringBuilder()
    run {
        while (true) {
            (listOf(firstInt, secondInt).map {
                Pair(it, it.lastIndex - indexOffset)
            }.also {
                if (it.all { it.second < 0 }) {
                    if (excess == 1) stringBuilder.append(1)
                    return@run
                }
            }.map { (integer, index) ->
                if (index < 0 || integer[index] != '1') 0
                else 1
            }.sum() + excess).let {
                excess = if (it > 1) 1 else 0
                stringBuilder.append(it % 2)
            }
            ++indexOffset
        }
    }
    return stringBuilder.reverse().toString()
}