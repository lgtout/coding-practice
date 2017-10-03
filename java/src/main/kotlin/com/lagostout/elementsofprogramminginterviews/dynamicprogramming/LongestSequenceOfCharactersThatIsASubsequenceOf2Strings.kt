package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.2.3 page 316
 */
fun longestCommonSubsequence(
        string1: String, string2: String): String {
    data class Position(val row: Int, val column: Int) {
        val belowRight
            get() = below.right
        val right
            get() = copy(column = column + 1)
        val below
            get() = copy(row = row + 1)
    }

    // Build grid cache
    // string1 -> vertical, string2 -> horizontal
    val cache = mutableListOf<MutableList<Int>>().apply {
        val row = mutableListOf<Int>().apply {
            (0..string2.length).forEach { add(0) }
        }
        (0..string1.length).forEach {
            add(row.toMutableList())
        }
    }

    // Compute grid cache match counts
    cache.indices.reversed().forEach { rowIndex ->
        cache[rowIndex].reversed().forEach { colIndex ->
            val currentCellPosition = Position(rowIndex, colIndex)
            val rightCellMatchCount = currentCellPosition.right.run {
                cache[row][column]
            }
            val belowCellMatchCount = currentCellPosition.below.run {
                cache[row][column]
            }
            val belowRightCellMatchCount =
                    if (string1[currentCellPosition.column] ==
                            string2[currentCellPosition.row]) {
                        currentCellPosition.belowRight.run { cache[row][column] } + 1
                    } else 0
            cache[rowIndex][colIndex] = listOf(rightCellMatchCount,
                    belowCellMatchCount, belowRightCellMatchCount).max()!!
        }
    }

    // Build common sequence from walking the grid
    var position = Position(0,0)
    val longestSequence = StringBuilder()
    run {
        while (true) {
            with (position) {
                val currentPositionMatchCount = cache[row][column]
                if (currentPositionMatchCount == 0) return@run
                position = if (string1[row] == string2[column]) {
                    longestSequence.append(string1[row])
                    position.belowRight
                } else {
                    listOf(position.below, position.right).find { (r, c) ->
                        cache[r][c] == currentPositionMatchCount
                    }!!
                }
            }
        }
    }

    return longestSequence.reverse().toString()

}