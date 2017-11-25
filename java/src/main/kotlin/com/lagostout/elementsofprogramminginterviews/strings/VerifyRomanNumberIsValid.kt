package com.lagostout.elementsofprogramminginterviews.strings

/**
 * Problem 7.9.2 page 106
 */
fun romanNumberIsValid(number: String): Boolean {
    val romanToDecimalMap = mapOf(
            'I' to 1, 'V' to 5, 'X' to 10,
            'L' to 50, 'C' to 100, 'D' to 500,
            'M' to 1000)
    val symbolToAntecedenceExceptionMap = mapOf(
            'I' to setOf('V', 'X'),
            'X' to setOf('L', 'C'),
            'C' to setOf('D','M')
    )
    val symbols = romanToDecimalMap.keys
    val symbolToOrderMap = symbols.withIndex().map {
        (index, value) -> value to index
    }.toMap()
    var firstChar = number.first()
    if (firstChar !in symbols) {
        return false
    }
    var numberIsValid = true
    var secondIndex = 1
    while (numberIsValid) {
        if (secondIndex > number.lastIndex) break
        val secondChar = number[secondIndex]
        if (secondChar !in symbols) {
            numberIsValid = false
            break
        }
        if (compareValues(symbolToOrderMap[firstChar],
                symbolToOrderMap[secondChar]) < 0) {
            symbolToAntecedenceExceptionMap[firstChar]?.let {
                allowedAntecedentSymbols ->
                if (secondChar !in allowedAntecedentSymbols) {
                    numberIsValid = false
                }
            }
        }
        firstChar = secondChar
        ++secondIndex
    }
    return numberIsValid
}

