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

    private fun rectangle(array: Array<Array<Boolean>>): Rectangle2 {
        return Rectangle2(
            0, if (array.isNotEmpty()) array[0].lastIndex
            else -1, 0, array.lastIndex)
    }

    @Suppress("FunctionName")
    fun computeWithBruteForceAndRecursion(
            array: Array<Array<Boolean>>,
            rectangle: Rectangle2 = rectangle(array)): Rectangle2? {
        return when (rectangle.size) {
            0 -> null
            1 -> if (array[rectangle.top][rectangle.left])
                rectangle else null
            else -> {
                var square = with (rectangle) {
                    if (isSquare) {
                        copy(right = left + width - 2,
                            bottom = top + height - 2)
                    } else {
                        min(height, width).let {
                            copy(right = left + it - 1,
                                bottom = top + it - 1)
                        }
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
            array: Array<Array<Boolean>>, rectangle: Rectangle2 = rectangle(array),
            cache: MutableMap<Rectangle2, Rectangle2?> = mutableMapOf()): Rectangle2? {
        return (if (cache.containsKey(rectangle)) {
            cache[rectangle]
        } else when (rectangle.size) {
            0 -> null
            1 -> if (array[rectangle.top][rectangle.left])
                rectangle else null
            else -> {
                var square = with (rectangle) {
                    if (isSquare) {
                        copy(right = left + width - 2,
                            bottom = top + height - 2)
                    } else {
                        min(height, width).let {
                            copy(right = left + it - 1,
                                bottom = top + it - 1)
                        }
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
        })
    }

    fun computeWithMemoizationBottomUp(
            array: Array<Array<Boolean>>,
            rectangle: Rectangle2 = rectangle(array)): Rectangle2? {
        val cache: MutableMap<Rectangle2, Rectangle2?> = mutableMapOf()
        subSquares(rectangle, Rectangle2(0, 0, 0, 0)).forEach {
            cache[it] = if (array[it.top][it.right]) it else null
        }
        println(cache)
        val maxSquareSideLength = min(rectangle.height, rectangle.width)
        (2..maxSquareSideLength).map {
            subSquares(rectangle, Rectangle2(0, it - 1, 0, it - 1))
                    .map { square ->
                        val subSquare = square.copy(
                            right = square.right - 1,
                            bottom = square.bottom - 1)
                        subSquares(square, subSquare)
                                .map { cache[it] }
                                .let { results ->
                                    results.filterNotNull().run {
                                        when {
                                            filter {
                                                it.size == subSquare.size
                                            }.size == results.size -> square
                                            isNotEmpty() -> maxBy { it.size }
                                            else -> null
                                        }
                                    }
                                }.also {
                                    cache[square] = it
                                }
                    }
        }
        return subSquares(rectangle, Rectangle2(0, maxSquareSideLength - 1,
            0, maxSquareSideLength - 1)).mapNotNull { cache[it] }.maxBy { it.size }
    }

    private fun subSquares(rectangle: Rectangle2, subSquare: Rectangle2): List<Rectangle2> {
        return rectangle.run {
            (top..(bottom - subSquare.height + 1)).flatMap {
                subSquare.run {
                    copy(top = it, bottom = it + height - 1).run {
                        (rectangle.left..(rectangle.right - width + 1)).map {
                            copy(left = it, right = it + width - 1)
                        }
                    }
                }
            }
        }
    }


}