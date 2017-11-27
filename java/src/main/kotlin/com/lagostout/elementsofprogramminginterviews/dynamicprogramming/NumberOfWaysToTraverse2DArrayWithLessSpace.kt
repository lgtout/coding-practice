package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.3.2 page 318
 */
fun numberOfWaysToTraverse2DArrayWithLessSpace(
        rowCount: Int, columnCount: Int): Int {
    val (shortSide, longSide) =
            listOf(rowCount, columnCount).sorted()
    return IntArray(shortSide) { 1 }
            .apply {
                (1 until longSide).forEach {
                    (1 until shortSide).forEach {
                        set(it, get(it-1) + get(it))
                    }
                }
            }.last()
}