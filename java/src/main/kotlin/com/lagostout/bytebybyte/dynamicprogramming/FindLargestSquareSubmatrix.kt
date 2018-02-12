package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.bytebybyte.dynamicprogramming.FindLargestSquareSubmatrix.Position.Companion.p

/*
Given a 2D boolean array, find the largest square subarray of true values.
The return value should be the side length of the largest square subarray
subarray.
Eg.
squareSubmatrix([false, true, false, false]) = 2
                [true,  TRUE, TRUE,  true ]
                [false, TRUE, TRUE,  false]
(the solution is the submatrix in all caps)
*/

object FindLargestSquareSubmatrix {

    data class Position(val row: Int, val col: Int) {
        fun shiftRight(): Position {
            return copy(col = col + 1)
        }
        fun shiftDown(): Position {
            return copy(row = row + 1)
        }
        companion object {
            fun p(row: Int, col: Int): Position {
                return Position(row, col)
            }
        }
    }

    data class Rectangle(val topCorner: Position, val bottomCorner: Position) {
        val width = bottomCorner.col - topCorner.col + 1
        val height = bottomCorner.row - topCorner.row + 1
        val size = width * height
        val isFlat = width > height
        val isTall = width < height
        val isSquare = !isTall && !isFlat
        fun shiftRight(): Rectangle {
            return copy(topCorner = topCorner.shiftRight(),
                    bottomCorner = bottomCorner.shiftRight())
        }
        fun shiftDown(): Rectangle {
            return copy(topCorner = topCorner.shiftDown(),
                    bottomCorner = bottomCorner.shiftDown())
        }
        companion object {
            fun r(topCorner: Position, bottomCorner: Position): Rectangle {
                return Rectangle(topCorner, bottomCorner)
            }
        }
    }

    fun computeWithRecursion(array: Array<Array<Boolean>>): Rectangle? {
        return computeWithRecursion(
            Rectangle(p(0, 0), p(array.size - 1, array[0].size - 1)), array).second
    }

    private fun computeWithRecursion(
            rectangle: Rectangle, array: Array<Array<Boolean>>):
            Pair<Boolean, Rectangle?> {
        val maxCols = array[0].size
        val maxRows = array.size
        if (rectangle.size == 1) {
            return rectangle.bottomCorner.run {
                (array[row][col]).let {
                    Pair(it, if (array[row][col]) rectangle else null)
                }
            }
        }
        var subRectangle = rectangle.let {
            (when {
                it.isFlat -> it.bottomCorner.copy(col = it.topCorner.col + it.height - 1)
                it.isTall -> it.bottomCorner.copy(row = it.topCorner.row + it.width - 1)
                else -> it.bottomCorner.copy(col = it.bottomCorner.row - 1)
            }).let {
                    bottomCorner -> it.copy(bottomCorner = bottomCorner) }
        }
        var isAllTrue = true
        var largestSquare: Rectangle? = null
        while (true) {
            if ((rectangle.isFlat && subRectangle.bottomCorner.col >= maxCols) ||
                    subRectangle.bottomCorner.row >= maxRows) break
            val result = computeWithRecursion(subRectangle, array)
            isAllTrue = isAllTrue && result.first
            largestSquare = if (largestSquare == null ||
                    (result.second != null &&
                    result.second!!.size > largestSquare.size)) {
                result.second
            } else largestSquare
            subRectangle = if (rectangle.isFlat) { subRectangle::shiftRight}
            else {subRectangle::shiftDown }()
        }

        return Pair(isAllTrue, largestSquare)
    }

//    fun computeWithMemoizationBottomUp() {}

//    fun computeWithRecursion() {}
//    fun computeWithRecursionAndMemoization() {}
//    fun computeWithRecursionAndMemoization() {}
//    fun computeWithMemoizationBottomUp() {}
}