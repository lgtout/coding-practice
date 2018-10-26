package com.lagostout.elementsofprogramminginterviews.arrays

/* Problem 6.16 page 86 */

fun sudokuIsValid(grid: List<List<Int>>): Boolean {

    fun isValid(list: List<Int>): Boolean {
        var result = true
        val checkedElements = mutableSetOf<Int>()
        for (element in list) {
            if (element != 0) {
                if (element in checkedElements) {
                    result = false
                    break
                } else checkedElements.add(element)
            }
        }
        return result
    }

    val dim = grid.size

    // Check each row contains distinct elements.
    var result = true
    for (row in grid) {
        if (!isValid(row)) {
            result = false
            break
        }
    }

    // Check each column contains distinct elements.
    if (result) {
        for (columnIndex in 0 until dim) {
            val column = (0 until dim).map { grid[it][columnIndex] }
            if (!isValid(column)) {
                result = false
                break
            }
        }
    }

    // Check each subarray contains distinct elements.
    if (result) {
        for (rowStartIndex in 0 until dim step 3) {
            for (colStartIndex in 0 until dim step 3) {
                val elements = mutableListOf<Int>()
                for (rowIndex in 0 until 3) {
                    for (colIndex in 0 until 3) {
                        elements.add(grid[rowStartIndex + rowIndex][colStartIndex + colIndex])
                    }
                }
                if (!isValid(elements)) {
                    result = false
                    break
                }
            }
        }
    }

    return result
}