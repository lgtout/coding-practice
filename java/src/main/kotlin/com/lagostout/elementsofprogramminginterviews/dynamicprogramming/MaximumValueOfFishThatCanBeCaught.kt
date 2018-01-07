package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/* Problem 17.3.4 page 318 */

fun maximumValueOfFishThatCanBeCaught(sea: List<List<Int>>): Int {
    if (sea.isEmpty() || sea[0].isEmpty()) return 0
    val values = List(sea.size) { MutableList(sea[0].size) { 0 } }
    fun belowValue(rowIndex: Int, colIndex: Int): Int? {
        return if (rowIndex < values.lastIndex)
            values[rowIndex + 1][colIndex] else null
    }
    fun rightValue(rowIndex: Int, colIndex: Int): Int? {
        return if (colIndex < values[rowIndex].lastIndex)
            values[rowIndex][colIndex + 1] else null
    }
    sea.withIndex().reversed().forEach { (rowIndex, row) ->
        row.withIndex().reversed().forEach { (colIndex, value) ->
            values[rowIndex][colIndex] =
                    listOfNotNull(belowValue(rowIndex, colIndex),
                            rightValue(rowIndex, colIndex)).sorted().let {
                        if (it.isNotEmpty()) it.last() else 0
                    } + value
        }
    }
    return values[0][0]
}
