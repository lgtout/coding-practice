@file:Suppress("FunctionName")

package com.lagostout.bytebybyte.dynamicprogramming

import kotlin.math.absoluteValue
import kotlin.math.sign

object MatrixPath {

    fun computeWithRecursionAndBruteForce_sign(
            matrix: List<List<Int>>): Int {
        return if (matrix.isEmpty() || matrix.first().isEmpty()) 0
        else _computeWithRecursionAndBruteForce_sign(matrix)
    }

    /* Push accumulated sign down the recursive levels and multiply
    call result by absolute value of matrix square. */
    private fun _computeWithRecursionAndBruteForce_sign(
            matrix: List<List<Int>>,
            // (row, column)
            origin: Pair<Int, Int> = Pair(0,0),
            sign: Int = 1): Int {
        val lastRow = matrix.lastIndex
        val lastColumn = matrix.last().lastIndex
        return if (origin == Pair(lastRow, lastColumn))
            matrix[origin.first][origin.second] * sign
        else {
            listOf(origin.copy(first = origin.first + 1),
                origin.copy(second = origin.second + 1)).filter {
                it.first <= lastRow && it.second <= lastColumn
            }.map {
                (matrix[origin.first][origin.second]).let { value ->
                    _computeWithRecursionAndBruteForce_sign(
                        matrix, it, sign * value.sign) *
                            value.absoluteValue
                }
            }.max()!!
        }
    }

    fun computeWithRecursionAndBruteForce_minMax(
            matrix: List<List<Int>>): Int {
        return if (matrix.isEmpty() || matrix.first().isEmpty()) 0
        else _computeWithRecursionAndBruteForce_minMax(matrix).second
    }

    /* Compute and return min and max product for each recursive result */
    private fun _computeWithRecursionAndBruteForce_minMax(
            matrix: List<List<Int>>,
            // (row, column)
            origin: Pair<Int, Int> = Pair(0,0)): Pair<Int, Int> {
        val lastRow = matrix.lastIndex
        val lastColumn = matrix.last().lastIndex
        return if (origin == Pair(lastRow, lastColumn))
            (matrix[origin.first][origin.second]).let {
                Pair(it, it)
            }
        else {
            listOf(origin.copy(first = origin.first + 1),
                origin.copy(second = origin.second + 1)).filter {
                it.first <= lastRow && it.second <= lastColumn
            }.flatMap {
                _computeWithRecursionAndBruteForce_minMax(matrix, it).toList()
            }.map {
                it * matrix[origin.first][origin.second]
            }.sorted().let {
                Pair(it.first(), it.last())
            }
        }
    }

    fun computeWithRecursionAndMemoization(matrix: List<List<Int>>): Int {
        return if (matrix.isEmpty() || matrix.first().isEmpty()) 0
        else _computeWithRecursionAndMemoization(matrix,
            ((0..matrix.lastIndex).map {
                arrayOfNulls<Pair<Int, Int>>(matrix.first().size)
            }).toTypedArray()).second
    }

    private fun _computeWithRecursionAndMemoization(
        matrix: List<List<Int>>,
        cache: Array<Array<Pair<Int, Int>?>>,
        origin: Pair<Int, Int> = Pair(0,0)): Pair<Int, Int> {
        return cache[origin.first][origin.second]?.also {
            println(origin)
            println(cache.toList().map { it.toList() })
        } ?: run {
            val lastRow = matrix.lastIndex
            val lastColumn = matrix.last().lastIndex
            if (origin == Pair(lastRow, lastColumn))
                (matrix[origin.first][origin.second]).let {
                    Pair(it, it)
                }
            else {
                listOf(origin.copy(first = origin.first + 1),
                    origin.copy(second = origin.second + 1)).filter {
                    it.first <= lastRow && it.second <= lastColumn
                }.flatMap {
                    _computeWithRecursionAndMemoization(
                        matrix, cache, it).toList()
                }.map {
                    it * matrix[origin.first][origin.second]
                }.sorted().let {
                    Pair(it.first(), it.last())
                }
            }
        }.also {
            cache[origin.first][origin.second] = it
        }
    }

    fun computeBottomUpWithMemoization(matrix: List<List<Int>>): Int {
        if (matrix.isEmpty() || matrix.first().isEmpty()) return 0
        val cache = ((0..matrix.lastIndex).map {
            arrayOfNulls<Pair<Int, Int>>(matrix.first().size)
        }).toTypedArray()
        val lastRow = matrix.lastIndex
        val lastColumn = matrix.last().lastIndex
        cache[lastRow][lastColumn] = matrix[lastRow][lastColumn].let {
            Pair(it, it)
        }
        (lastRow downTo 0).forEach { row ->
            (lastColumn downTo 0).forEach columns@ { col ->
                if (row == lastRow && col == lastColumn) return@columns
                val origin = Pair(row, col)
                listOf(origin.copy(first = origin.first + 1),
                    origin.copy(second = origin.second + 1)).filter {
                    it.first <= lastRow && it.second <= lastColumn
                }.flatMap {
                    cache[it.first][it.second]!!.toList()
                }.map {
                    it * matrix[origin.first][origin.second]
                }.sorted().let {
                    Pair(it.first(), it.last())
                }.also {
                    cache[origin.first][origin.second] = it
                }
            }
        }
        return cache[0][0]?.second ?: 0
    }

}