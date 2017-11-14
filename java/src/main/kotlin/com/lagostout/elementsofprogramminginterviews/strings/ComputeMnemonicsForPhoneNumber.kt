package com.lagostout.elementsofprogramminginterviews.strings

/**
 * Problem 7.7.1/2 page 103
 */
// Skipping this - it's too simple.
object ComputeMnemonicsForPhoneNumber {

    val digitToCharsMap = mapOf(2 to listOf('A','B','C'),
            3 to listOf('D','E','F'), 4 to listOf('G','H','I'),
            5 to listOf('J','K','L'), 6 to listOf('M','N','O'),
            7 to listOf('P','Q','R','S'), 8 to listOf('T','U','V'),
            9 to listOf('X','Y','Z'))

    fun computeMnemonicsForPhoneNumber(number: String): List<String> {
//        data class Frame()
//        val stack =
        digitToCharsMap

        return emptyList()
    }

}
