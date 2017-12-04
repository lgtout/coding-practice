package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.NumberOfWaysToTraverse2DArrayWithObstacles.Side.Column
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.NumberOfWaysToTraverse2DArrayWithObstacles.Side.Row

/**
 * Problem 17.3.3 page 318
 */
object NumberOfWaysToTraverse2DArrayWithObstacles {
    sealed class Side(val size: Int) {
        class Row(size: Int) : Side(size)
        class Column(size: Int) : Side(size)
    }
    fun numberOfWaysToTraverse2DArrayWithObstacles(
            obstacles: List<List<Boolean>>): Int {
        val rowCount: Int = obstacles.size
        val columnCount: Int = if (rowCount > 0)
            obstacles.first().size else 0
        val (shortSide, longSide) =
                listOf(Row(rowCount), Column(columnCount))
                        .sortedBy { it.size }
        return IntArray(shortSide.size).apply {
            (0 until longSide.size).forEach { longIndex ->
                (0 until shortSide.size).forEach { shortIndex ->
                    (if (obstacles[if (longSide is Row) longIndex else shortIndex][
                            if (shortSide is Column) shortIndex else longIndex]) 0
                    else if (longIndex == 0) {
                        if (shortIndex == 0) 1
                        else get(shortIndex - 1)
                    } else {
                        get(shortIndex) + if (shortIndex == 0) 0
                        else get(shortIndex - 1)
                    }).let { set(shortIndex, it) }
                }
            }
        }.last()
    }
}
