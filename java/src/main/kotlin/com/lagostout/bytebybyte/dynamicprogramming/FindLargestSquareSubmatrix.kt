package com.lagostout.bytebybyte.dynamicprogramming

import kotlin.math.min

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

    data class Rectangle2(val left: Int, val right: Int,
                          val top: Int, val bottom: Int) {
        val width = right - left + 1
        val height = bottom - top + 1
        val size = width * height
        val isSquare = width == height
        companion object {
            fun r2(left: Int, right: Int, top: Int, bottom: Int): Rectangle2 {
                return Rectangle2(left, right, top, bottom)
            }
        }
    }

    fun computeWithBruteForceAndRecursion(array: Array<Array<Boolean>>): Rectangle2? {
        return _computeWithBruteForceAndRecursion(array,
            Rectangle2(0, if (array.isNotEmpty()) array[0].lastIndex else -1,
                0, array.lastIndex))
    }

    @Suppress("FunctionName")
    private fun _computeWithBruteForceAndRecursion(
            array: Array<Array<Boolean>>, rectangle: Rectangle2 ): Rectangle2? {
        return when(rectangle.size) {
            0 -> null
            1 -> if (array[rectangle.top][rectangle.left])
                rectangle else null
            else -> {
                val squareSideLength = min(rectangle.height, rectangle.width).let {
                    it - if (rectangle.isSquare) 1 else 0
                }
                var square = rectangle.copy(
                    right = rectangle.left + squareSideLength - 1,
                    bottom = rectangle.top + squareSideLength - 1)
                val subsquares = mutableListOf<Rectangle2?>()
                while (square.bottom <= rectangle.bottom) {
                    val startSquare = square.copy()
                    while (square.right <= rectangle.right) {
                        subsquares.add(_computeWithBruteForceAndRecursion(
                            array, square))
                        square = square.run {
                            copy(left = left + 1, right = right + 1)
                        }
                    }
                    square = startSquare.run {
                        copy(top = top + 1, bottom = bottom + 1)
                    }
                }
                (if (subsquares.none { it == null || it.size < square.size }) {
                    rectangle
                } else subsquares.filterNotNull().maxBy { it.size })?.let {
                    // A non-square rectangle may be returned.  If so, this
                    // trims it to a square.  Otherwise, it has no effect on
                    // the size of the returned rectangle.
                    val sideLength = min(it.height, it.width)
                    it.copy(right = it.left + sideLength - 1,
                        bottom = it.top + sideLength - 1)
                }
            }
        }
    }

    // TODO Redo like above
    fun computeWithRecursionAndMemoization(array: Array<Array<Boolean>>): Rectangle2? {

        return null
    }

    // TODO Bottom up

//    fun computeWithBruteForceAndRecursion() {}
//    fun computeWithRecursionAndMemoization() {}
//    fun computeWithRecursionAndMemoization() {}
//    fun computeWithMemoizationBottomUp() {}
}