package com.lagostout.elementsofprogramminginterviews.strings

/**
 * Problem 7.1 page 95
 */
object InterconvertStringsAndIntegers {

    fun stringToInteger(s: String): Int? {
        if (s.isEmpty()) return null
        val isNegative = s.first() == '-'
        val start = if (isNegative) 1 else 0
        // TODO Is there a way to do this check without converting to string?
        // I imagine it would involve some check in the forEach loop where
        // number is being updated.
        // This might be low priority because the problem stipulates that the string
        // will encode an integer.  On the other hand, it doesn't specify if the
        // integer will fit into 32 bits.
        if ((isNegative && s.length > Int.MIN_VALUE.toString().length) ||
                (s.length > Int.MIN_VALUE.toString().length)) {
            throw IllegalArgumentException("The string provided will not fit into a 32 bit Int")
        }
        var number = 0
        val zeroInt = '0'.toInt()
        (start..s.lastIndex).forEach {
            val charInt = s[it].toInt() - zeroInt
            number = number * 10 + charInt
        }
        return number * (if (isNegative) -1 else 1)
    }

    fun integerToString(number: Int?): String {
        if (number == null) return ""
        val builder = StringBuilder()
        var absNumber = Math.abs(number)
        do {
            val nextAbsNumber = (absNumber  / 10)
            val digit = absNumber  - (nextAbsNumber * 10)
            builder.append(digit)
            absNumber  = nextAbsNumber
        } while (absNumber  > 0)
        if (number < 0) builder.append('-')
        return builder.reverse().toString()
    }

}