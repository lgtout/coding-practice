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

    @Suppress("FunctionName")
    fun computeWithBruteForceAndRecursion(
            array: Array<Array<Boolean>>, rectangle: Rectangle2 =
            Rectangle2(0, if (array.isNotEmpty()) array[0].lastIndex else -1,
                0, array.lastIndex)): Rectangle2? {
        return when (rectangle.size) {
            0 -> null
            1 -> if (array[rectangle.top][rectangle.left])
                rectangle else null
            else -> {
                var square = min(rectangle.height, rectangle.width).let {
                    rectangle.copy(right = rectangle.left + it - 1,
                        bottom = rectangle.top + it - 1).let {
                        if (it == rectangle) {
                            it.copy(
                                right = it.left + it.width - 2,
                                bottom = it.top + it.height - 2)
                        } else it
                    }
                }
                val subsquares = mutableListOf<Rectangle2?>()
                while (square.bottom <= rectangle.bottom) {
                    val startSquare = square.copy()
                    while (square.right <= rectangle.right) {
                        subsquares.add(computeWithBruteForceAndRecursion(
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
                    if (!it.isSquare) {
                        val sideLength = min(it.height, it.width)
                        it.copy(right = it.left + sideLength - 1,
                            bottom = it.top + sideLength - 1)
                    } else it
                }
            }
        }
    }

    fun computeWithRecursionAndMemoization(
            array: Array<Array<Boolean>>,
            rectangle: Rectangle2 = Rectangle2(0,
                if (array.isNotEmpty()) array[0].lastIndex else -1, 0, array.lastIndex),
            cache: MutableMap<Rectangle2, Rectangle2?> = mutableMapOf()): Rectangle2? {
        return (if (cache.containsKey(rectangle)) {
            cache[rectangle].also {
//                println()
//                println("hit")
//                println("$rectangle $it")
//                println(cache)
//                println()
            }
        } else when (rectangle.size) {
            0 -> null
            1 -> if (array[rectangle.top][rectangle.left])
                rectangle else null
            else -> {
                var square = min(rectangle.height, rectangle.width).let {
                    rectangle.copy(right = rectangle.left + it - 1,
                        bottom = rectangle.top + it - 1).let {
                        if (it == rectangle) {
                            it.copy(
                                right = it.left + it.width - 2,
                                bottom = it.top + it.height - 2)
                        } else it
                    }
                }
                val subsquares = mutableListOf<Rectangle2?>()
                while (square.bottom <= rectangle.bottom) {
                    val startSquare = square.copy()
                    while (square.right <= rectangle.right) {
                        subsquares.add(computeWithRecursionAndMemoization(
                            array, square, cache))
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
                    if (!it.isSquare) {
                        val sideLength = min(it.height, it.width)
                        it.copy(right = it.left + sideLength - 1,
                            bottom = it.top + sideLength - 1)
                    } else it
                }
            }
        }.also {
            cache[rectangle] = it
        }).also {
            println(cache)
        }
    }

    // TODO Bottom up

//    fun computeWithBruteForceAndRecursion() {}
//    fun computeWithRecursionAndMemoization() {}
//    fun computeWithRecursionAndMemoization() {}
//    fun computeWithMemoizationBottomUp() {}
}