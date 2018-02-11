package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.bytebybyte.dynamicprogramming.FindLargestSquareSubarray.Position.Companion.p

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

object FindLargestSquareSubarray {

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
            fun r(topRow: Int, topCol: Int, bottomRow: Int, bottomCol: Int): Rectangle {
                return Rectangle(p(topRow, topCol), p(bottomRow, bottomCol))
            }
        }
    }

    fun computeWithRecursion(array: Array<Array<Boolean>>): Rectangle? {
        return computeWithRecursion(
            Rectangle(p(0, 0), p(array.size - 1, array[0].size - 1)), array)?.let {
            println("result $it")
            (if (it.isFlat) it.bottomCorner.copy(col = it.topCorner.col + it.height - 1)
            else it.bottomCorner.copy(row = it.topCorner.row + it.width - 1)).let {
                bottomCorner -> it.copy(bottomCorner = bottomCorner)
            }
        }
    }

    private fun computeWithRecursion(
            rectangle: Rectangle, array: Array<Array<Boolean>>): Rectangle? {
        println(rectangle.size)
        println(rectangle)
        return if (rectangle.size == 1) {
            rectangle.topCorner.let {
                if (array[it.row][it.col]) rectangle else null
            }
        } else {
            val bottomCorner = when {
                rectangle.isFlat -> rectangle.bottomCorner.copy(
                        col = rectangle.topCorner.col + rectangle.width - 2)
                else -> rectangle.bottomCorner.copy(
                        row = rectangle.topCorner.row + rectangle.height - 2)
            }
            var subRectangle = rectangle.copy(bottomCorner = bottomCorner)
            var maxTrueSubRectangle: Rectangle? = null
            var isAllTrue = true
            while (true) {
                if (subRectangle.bottomCorner.col >= array[0].size ||
                        subRectangle.bottomCorner.row >= array.size) break
                val result = computeWithRecursion(subRectangle, array)
                if (result != subRectangle) isAllTrue = false
                maxTrueSubRectangle = maxTrueSubRectangle?.let { maxRectangle ->
                    result?.let {
                        if (it.size > maxRectangle.size) it else maxRectangle
                    } ?: maxTrueSubRectangle
                } ?: result
                subRectangle = if (rectangle.isFlat) { subRectangle::shiftRight }
                else { subRectangle::shiftDown }()
            }
            if (isAllTrue) rectangle else maxTrueSubRectangle
        }
    }

    fun computeWithRecursionAndMemoization(array: Array<Array<Boolean>>): Rectangle? {
        val dp = mutableMapOf<Rectangle, Rectangle?>()
        return computeWithRecursionAndMemoization(
            Rectangle(p(0, 0), p(array.size, array[0].size)), array, dp)
    }

    // TODO Use latest simple recursion implementation.
    private fun computeWithRecursionAndMemoization(
            rectangle: Rectangle, array: Array<Array<Boolean>>,
            dp: MutableMap<Rectangle, Rectangle?>): Rectangle? {
        return null
//        return if (dp.containsKey(rectangle)) {
//            dp[rectangle]
//        } else {
//            if (rectangle.size == 1) {
//                rectangle.topCorner.let {
//                    if (array[it.row][it.col]) rectangle else null
//                }
//            } else {
//                val isFlat = rectangle.width > rectangle.height
//                val bottomCorner = if (isFlat) {
//                    Position(rectangle.bottomCorner.row,
//                            rectangle.topCorner.col + rectangle.width - 1)
//                } else {
//                    Position(rectangle.topCorner.row + rectangle.height - 1,
//                            rectangle.bottomCorner.col)
//                }
//                var subRectangle = rectangle.copy(bottomCorner = bottomCorner)
//                var maxTrueSubRectangle: Rectangle? = null
//                var isAllTrue = true
//                while (true) {
//                    if (subRectangle.bottomCorner.col > array[0].size ||
//                            subRectangle.bottomCorner.row > array.size) break
//                    computeWithRecursion(subRectangle, array)?.let {
//                        if (it != subRectangle) isAllTrue = false
//                        maxTrueSubRectangle = maxTrueSubRectangle?.let { maxRectangle ->
//                            if (it.size > maxRectangle.size) it else maxRectangle
//                        } ?: it
//                    }
//                    subRectangle = if (isFlat) { subRectangle::shiftRight }
//                    else { subRectangle::shiftDown }()
//                }
//                if (isAllTrue) subRectangle else maxTrueSubRectangle
//            }.also {
//                dp[rectangle] = it
//            }
//        }
    }

    fun computeWithMemoizationBottomUp() {}

//    fun computeWithRecursion() {}
//    fun computeWithRecursionAndMemoization() {}
//    fun computeWithRecursionAndMemoization() {}
//    fun computeWithMemoizationBottomUp() {}
}