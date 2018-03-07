@file:Suppress("FunctionName")

package com.lagostout.bytebybyte.dynamicprogramming

object KnightProbability {

    fun computeWithRecursionAndBruteForce(
            height: Int, width: Int, start: Pair<Int, Int>,
            moves: Int): Float {
        return _computeWithRecursion(height, width, start, moves)
    }

    private fun _computeWithRecursion(
            height: Int, width: Int, start: Pair<Int, Int>,
            moves: Int): Float {
        return if (start.first !in (0 until height)  ||
                    start.second !in (0 until width)) 0f
        else if (moves == 0) 1f
        else {
            listOf(
                Pair(-2, -1),
                Pair(-1, -2),
                Pair(1, -2),
                Pair(2, -1),
                Pair(2, 1),
                Pair(1, 2),
                Pair(-1, 2),
                Pair(-2, 1)
            ).map {
                _computeWithRecursion(height, width,
                    Pair(start.first + it.first,
                        start.second + it.second), moves - 1)
            }.let {
                it.sum() / it.count()
            }
        }
    }

    fun computeWithRecursionAndMemoization(
            height: Int, width: Int, start: Pair<Int, Int>,
            moves: Int, cache: MutableMap<Pair<Int, Pair<Int, Int>>, Float> =
                    mutableMapOf()): Float {
        val key = Pair(moves, start)
        return cache[key] ?: run {
            if (start.first !in (0 until height)  ||
                    start.second !in (0 until width)) 0f
            else if (moves == 0) 1f
            else {
                listOf(
                    Pair(-2, -1),
                    Pair(-1, -2),
                    Pair(1, -2),
                    Pair(2, -1),
                    Pair(2, 1),
                    Pair(1, 2),
                    Pair(-1, 2),
                    Pair(-2, 1)
                ).map {
                    _computeWithRecursion(height, width,
                        Pair(start.first + it.first,
                            start.second + it.second), moves - 1)
                }.let {
                    it.sum() / it.count()
                }
            }
        }.also {
            cache[key] = it
        }
    }

    fun computeBottomUpWithMemoization(
        height: Int, width: Int, start: Pair<Int, Int>, moves: Int): Float {
        val squareMoves = listOf(Pair(-2, -1),
            Pair(-1, -2),
            Pair(1, -2),
            Pair(2, -1),
            Pair(2, 1),
            Pair(1, 2),
            Pair(-1, 2),
            Pair(-2, 1))
        val validSquares = (0 until height).flatMap { row ->
            (0 until width).map { col ->
                Pair(row, col)
            }
        }
        val cache = mutableMapOf<Int, MutableMap<Pair<Int,Int>, Float>>().apply {
            (0..moves).forEach { move ->
                validSquares.forEach { square ->
                    getOrPut(move, { mutableMapOf() })[square] = 0f
                }
            }
            get(0)?.put(start, 1f)
        }
        (1..moves).forEach { move ->
            cache[move]?.keys?.forEach { square ->
                squareMoves.forEach {
                    Pair(square.first + it.first, square.second + it.second).let {
                        cache[move - 1]?.get(it)?.let {
                            cache[move]?.merge(square,
                                it, { initial, update -> initial + (update / squareMoves.size) } )
                        }
                    }
                }
            }
        }
        return cache[moves]?.values?.sum() ?: 1f
    }
}
