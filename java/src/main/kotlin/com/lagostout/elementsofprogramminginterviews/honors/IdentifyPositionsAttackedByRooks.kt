package com.lagostout.elementsofprogramminginterviews.honors

/* Problem 25.7 page 452 */

fun identifyPositionsAttackedByRooks(board: MutableList<MutableList<Int>>) {
    var rookIsInFirstRow = false
    var rookIsInFirstColumn = false
    val rowCount = board.size
    val colCount = board[0].size
    for (rowIndex in (0 until rowCount)) {
        val row = board[rowIndex]
        val firstRow = rowIndex == 0
        for (columnIndex in (0 until colCount)) {
            val square = row[columnIndex]
            val rookIsAtSquare = square == 0
            if (firstRow) {
                rookIsInFirstRow = rookIsInFirstRow || rookIsAtSquare
            }
            val firstColumn = columnIndex == 0
            if (firstColumn) {
                rookIsInFirstColumn = rookIsInFirstColumn || rookIsAtSquare
            }
            if (rookIsAtSquare) {
                board[0][columnIndex] = 0
                board[rowIndex][0] = 0
                continue
            }
        }
    }
    for (rowIndex in (1 until rowCount)) {
        for (columnIndex in (1 until colCount)) {
            board[rowIndex][columnIndex] =
                    listOf(board[0][columnIndex], board[rowIndex][0])
                            .firstOrNull { it == 0 } ?: 1
        }
    }
    if (rookIsInFirstRow) {
        for (colIndex in (0 until colCount)) {
            board[0][colIndex] = 0
        }
    }
    if (rookIsInFirstColumn) {
        for (rowIndex in (0 until rowCount)) {
            board[rowIndex][0] = 0
        }
    }
}