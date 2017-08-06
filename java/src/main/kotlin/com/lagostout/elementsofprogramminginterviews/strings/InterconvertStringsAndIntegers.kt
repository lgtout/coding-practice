package com.lagostout.elementsofprogramminginterviews.strings

object InterconvertStringsAndIntegers {

    fun stringToInteger(s: String): Int {
        var number = 0
        for (i in (s.length..0)) {
            val char = s[i]
            number = if (char == '-') {
                -number
            } else if (char == '+') {
                number
            } else {
                number * 10 + s[i].toInt()
            }
        }
        return number
    }

    fun integerToString(number: Int): String {
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