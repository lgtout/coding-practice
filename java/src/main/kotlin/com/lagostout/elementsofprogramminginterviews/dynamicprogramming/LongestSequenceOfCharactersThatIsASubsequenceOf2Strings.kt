package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.2.3 page 316
 */
fun longestSequenceOfCharactersThatIsASubsequenceOf(
        string1: String, string2: String): String {
    data class Position(val row: Int, val column: Int) {
        val aboveLeft
            get() = copy(row = row - 1, column = column - 1)
        val left
            get() = copy(column = column - 1)
        val above
            get() = copy(row = row - 1)
        val possiblePreviousPositions
            get() = listOf(left, aboveLeft, above)
    }
    val levenshteinDistanceCache = levenshteinDistanceCache(string1, string2)
    val longestSequenceCache = Array(string2.length + 1,
            { IntArray(string1.length + 1) })
    (1..longestSequenceCache.size).forEach { rowIndex ->
        val row = longestSequenceCache[rowIndex]
        (1..row.size).forEach { columnIndex ->
            listOf(Position(rowIndex - 1, columnIndex - 1), // above-left
                    Position(rowIndex - 1, columnIndex), // above
                    Position(rowIndex, columnIndex - 1) // left
            ).filter {
                levenshteinDistanceCache[it.row][it.column] <=
                        levenshteinDistanceCache[rowIndex][columnIndex]
            }.maxBy { longestSequenceCache[it.row][it.column] }?.let {
                longestSequenceCache[rowIndex][columnIndex] +=
                        if (string1[it.column] == string2[it.row]) 1 else 0
            }
        }
    }
    var longestSequenceLength = longestSequenceCache.last().last()
    var rowIndex = longestSequenceCache.lastIndex
    var columnIndex = longestSequenceCache.last().lastIndex
    while (true) {
        val sequenceLength = longestSequenceCache[rowIndex][columnIndex]
        if (sequenceLength == 0) break
        val position = Position(rowIndex, columnIndex)
        position.possiblePreviousPositions.filter {
            Math.abs(sequenceLength - longestSequenceCache[it.row][it.column]) <= 1
        }
        // TODO
//                . {
//            longestSequenceCache[it.row][it.column] <= longestSequenceCache[rowIndex][columnIndex]
//        }
    }
    return ""
}