package com.lagostout.elementsofprogramminginterviews.searching

/**
 * Problem 12.6 page 199
 */
fun findInTwoDimensionSortedArray(entry: Int, array: List<List<Int>>): Pair<Int, Int>? {
    if (array.isEmpty() || array.first().isEmpty()) return null
    var columnIndex = array.first().lastIndex
    var rowIndex = 0
    var done = false
    var result: Pair<Int, Int>? = null
    while (!done && columnIndex >= 0 &&
            rowIndex <= array.lastIndex) {
        array[rowIndex][columnIndex].let {
            when {
                it > entry -> --columnIndex
                it < entry -> ++rowIndex
                else -> {
                    result = Pair(rowIndex, columnIndex)
                    done = true
                }
            }
        }
    }
    return result
}

