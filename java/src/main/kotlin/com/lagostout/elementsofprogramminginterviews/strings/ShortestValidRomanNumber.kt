package com.lagostout.elementsofprogramminginterviews.strings

fun computeShortestValidRomanNumber(number: Int): String {
    // Idea: Use TreeSet to cut searching this down to O(log n).
    // Or apply binary search.
    val romanToDecimalMap = mapOf(
            'I' to 1, 'V' to 5, 'X' to 10,
            'L' to 50, 'C' to 100, 'D' to 500,
            'M' to 1000)
    val symbolOrderExceptionMap = mapOf(
            'I' to setOf('V', 'X'),
            'X' to setOf('L', 'C'),
            'C' to setOf('D','M')
    )
//    val exceptionSymbols =
    val symbols = romanToDecimalMap.keys
    val symbolToOrderMap = symbols.withIndex().map {
        (index, value) -> value to index
    }.toMap()
    var remainder = number
    val romanToDecimalList = romanToDecimalMap.toList()
    while (true) {
        romanToDecimalList.apply {
            binarySearch { it.second.compareTo(remainder) }.let {
                get(it)
            }
        }.let { (symbol, value) ->

        }
    }
    return ""
}