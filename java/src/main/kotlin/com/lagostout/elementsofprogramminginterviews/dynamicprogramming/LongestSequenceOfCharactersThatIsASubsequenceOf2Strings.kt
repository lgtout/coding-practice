package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.2.3 page 316
 */
fun longestSequenceOfCharactersThatIsASubsequenceOf(
        string1: String, string2: String): String {
    data class Position(val row: Int, val column: Int)
    val levenshteinDistanceCache = levenshteinDistanceCache(string1, string2)
    val longestSequenceCache = Array(string2.length, { IntArray(string1.length) })
    (1..longestSequenceCache.size).forEach { rowIndex ->
        val row = longestSequenceCache[rowIndex]
        (1..row.size).forEach { columnIndex ->
            val candidateNeighborPositions = listOf(
                    Position(rowIndex - 1, columnIndex - 1),
                    Position(rowIndex - 1, columnIndex),
                    Position(rowIndex, columnIndex - 1)).filter {
                levenshteinDistanceCache[it.row][it.column] <=
                        levenshteinDistanceCache[rowIndex][columnIndex]
            }.maxBy { longestSequenceCache[it.row][it.column] }?.apply {
//                longestSequenceCache[rowIndex][columnIndex] =
//                        longestSequenceCache[rowIndex][columnIndex] +
//                                if () 1 else
            }
        }
    }
    return ""
}