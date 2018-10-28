package com.lagostout.elementsofprogramminginterviews.arrays

/* Problem 6.17.1 page 88 */

fun spiralOrdering(matrix: List<List<Int>>): List<Int> {
    if (matrix.size == 1 && matrix.first().size == 1) return listOf(matrix[0][0])
    else if (matrix.isEmpty()) return emptyList()
    val ordering = mutableListOf<Int>()
    var startRow = 0
    var endRow = matrix.lastIndex
    var startCol = 0
    var endCol = matrix.first().lastIndex
    while (startCol < endCol || startRow < endRow) {
        // Top row
        for (col in (startCol..endCol)) {
            ordering.add(matrix[startRow][col])
        }
        // Right column
        for (row in ((startRow + 1)..endRow)) {
            ordering.add(matrix[row][endCol])
        }
        // Bottom row
        // Prevent repeating if the matrix contains a single row.
        if (endRow > startRow) {
            for (col in ((endCol - 1) downTo startCol)) {
                ordering.add(matrix[endRow][col])
            }
        }
        // Left column
        // Prevent repeating if the matrix contains a single column.
        if (startCol < endCol) {
            for (row in ((endRow - 1) downTo (startRow + 1))) {
                ordering.add(matrix[row][startCol])
            }
        }
        startCol += 1
        endCol -= 1
        startRow += 1
        endRow -= 1
    }
    return ordering
}
