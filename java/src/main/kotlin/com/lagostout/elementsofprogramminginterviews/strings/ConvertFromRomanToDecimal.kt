package com.lagostout.elementsofprogramminginterviews.strings

/**
 * Problem 7.9.1 page 105
 */
fun convertFromRomanToDecimal(roman: String): Int {
    val romanToDecimalMap = mapOf(
            'I' to 1, 'V' to 5, 'X' to 10,
            'L' to 50, 'C' to 100, 'D' to 500,
            'M' to 1000)
    return roman.withIndex().fold(0) { acc, (index, romanDigit) ->
        acc + (romanToDecimalMap[romanDigit]?.let { integer ->
            if (index == 0) integer
            else {
                romanToDecimalMap[roman[index - 1]]?.let {
                    previousInteger ->
                    integer - if (previousInteger < integer) {
                        2 * previousInteger
                    } else 0
                } ?: 0
            }
        } ?: 0)
    }
}