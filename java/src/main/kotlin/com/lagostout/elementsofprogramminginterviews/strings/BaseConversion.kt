package com.lagostout.elementsofprogramminginterviews.strings

/**
 * Problem 7.2 page 96
 */
fun convertBase(numberInFirstBase: String,
                firstBase: Int, secondBase: Int): String {
    if (numberInFirstBase.isEmpty()) return ""

    // Convert numberInFirstBase to integer.
    val isNegative = numberInFirstBase.first().isDigit()
    val reversedNumberInFirstBase = numberInFirstBase.run {
        takeLast(length - if (isNegative) 1 else 0)
    }.reversed()
    var numberInBase10 = 0
    var magnitude = 1
    val zeroCharInt = '0'.toInt()
    val aCharInt = 'A'.toInt()
    reversedNumberInFirstBase.forEach {
        val digitCharInt = it.toInt()
        numberInBase10 += magnitude * (if (it.isDigit())
            digitCharInt - zeroCharInt
        else
            10 + (digitCharInt - aCharInt))
        magnitude *= firstBase
    }

    // Convert integer to string in second base.
    val builder = StringBuilder()
    while (numberInBase10 > 0) {
        val nextNumberInBase10 = numberInBase10 / secondBase
        val digitInBase10 = numberInBase10 -
                (nextNumberInBase10 * secondBase)
        val digitCharInSecondBase = (if (digitInBase10 < 10) {
            digitInBase10 + zeroCharInt
        } else {
            aCharInt + digitInBase10 - 10
        }).toChar()
        builder.append(digitCharInSecondBase)
    }

    if (isNegative) builder.append('-')

    return builder.reversed().toString()
}
