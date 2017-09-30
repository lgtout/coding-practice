package com.lagostout.elementsofprogramminginterviews.dynamicprogramming


// TODO Implement recursive solution to improve understanding of DP solution.
fun recursiveLongestSequenceOfCharactersThatIsASubsequenceOfStrings() {

}

/**
 * Problem 17.2.3 page 316
 */
fun longestSequenceOfCharactersThatIsASubsequenceOfStrings(
        string1: String, string2: String): String {
    data class Position(val row: Int, val column: Int) {
        val belowRight
            get() = below.right
        val right
            get() = copy(column = column + 1)
        val below
            get() = copy(row = row + 1)
        val possiblePreviousPositions
            get() = listOf(right, below, belowRight)
    }
    // Build cache
    val cache = mutableListOf<MutableList<Int>>().apply {
        val row = mutableListOf<Int>().apply {
            (0..(string2.length + 1)).forEach { add(0) }
        }
        (0..(string1.length + 1)).forEach {
            add(mutableListOf(*row.toTypedArray()))
        }
    }
    cache.indices.reversed().forEach { rowIndex ->
        cache[rowIndex].reversed().forEach { colIndex ->
            val currentCellPosition = Position(rowIndex, colIndex)
            // TODO Reduce duplication
            val rightCellMatchCount = currentCellPosition.right.run {
                cache[row][column]
            }
            val belowCellMatchCount = currentCellPosition.below.run {
                cache[row][column]
            }
            val belowRightCellMatchCount = if (string1[currentCellPosition.column] == string2[currentCellPosition.row]) {
                currentCellPosition.belowRight.run { cache[row][column] }
            } else null
        }
    }

    // Build common sequence from matrix

    return ""

}