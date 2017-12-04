package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.3.1 page 317
 */
fun numberOfWaysToTraverse2DArray(rowCount: Int, columnCount: Int): Int {
    val array = mutableListOf<MutableList<Int>>().apply {
        val row = mutableListOf<Int>().apply {
            (0 until columnCount).forEach {
                (if (it == 0) 1 else 0).let {
                    add(it)
                }
            }
        }
        addAll((0 until rowCount).map { row.toMutableList() })
    }.apply {
        get(0).apply { (1 until columnCount).forEach { set(it, 1) } }
    }
    (1 until rowCount).forEach { rowIndex ->
        (1 until columnCount).forEach { columnIndex ->
            array[rowIndex][columnIndex] = array[rowIndex - 1][columnIndex] +
                    array[rowIndex][columnIndex - 1]
        }
    }
    return array.last().last()
}