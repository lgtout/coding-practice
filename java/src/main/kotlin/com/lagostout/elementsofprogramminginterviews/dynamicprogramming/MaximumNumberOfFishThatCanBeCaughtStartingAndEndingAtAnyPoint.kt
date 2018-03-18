package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.common.down
import com.lagostout.common.left
import com.lagostout.common.right
import com.lagostout.common.up
import kotlin.math.max

object MaximumNumberOfFishThatCanBeCaughtStartingAndEndingAtAnyPoint {

    fun computeWithRecursionAndBruteForce(
            sea: List<List<Int>>, origin: Pair<Int, Int> = Pair(0,0)): Int {
        return if (sea.isEmpty() || sea.first().isEmpty()) 0
        else {
            val limitPoint = Pair(sea.lastIndex, sea.first().lastIndex)
            fun compute(origin: Pair<Int, Int>): Int {
                return mutableListOf(sea[origin.first][origin.second]).run {
                    addAll(
                        listOf(origin.right, origin.down).filter {
                            it.first <= limitPoint.first && it.second <= limitPoint.second
                        }.map {
                            compute(it) + first()
                        })
                    max()!!
                }
            }
            (origin.first..limitPoint.first).flatMap { row ->
                (origin.second..limitPoint.second).map { col ->
                    Pair(row, col)
                }
            }.map { compute(it) }.max()!!
        }
    }

    fun computeWithRecursionAndMemoization(
            sea: List<List<Int>>, origin: Pair<Int, Int> = Pair(0,0)): Int {
        return if (sea.isEmpty() || sea.first().isEmpty()) 0
        else {
            val cache = mutableMapOf<Pair<Int, Int>, Int>()
            val limitPoint = Pair(sea.lastIndex, sea.first().lastIndex)
            fun compute(origin: Pair<Int, Int>): Int {
                return cache[origin] ?: run {
                    mutableListOf(sea[origin.first][origin.second]).apply {
                        addAll(
                            listOf(origin.right, origin.down).filter {
                                it.first <= limitPoint.first &&
                                        it.second <= limitPoint.second
                            }.map {
                                compute(it) + first()
                            })
                    }.max()!!
                }.also {
                    cache[origin] = it
                }
            }
            (origin.first..limitPoint.first).flatMap { row ->
                (origin.second..limitPoint.second).map { col ->
                    Pair(row, col)
                }
            }.map { compute(it) }.max()!!
        }
    }

    fun computeBottomUpWithMemoization(sea: List<List<Int>>): Int {
        return if (sea.isEmpty() || sea.first().isEmpty()) 0
        else {
            val lastColumn = sea.first().lastIndex
            val lastRow = sea.lastIndex
            val lastPossiblePoint = Pair(lastRow, lastColumn)
            var currentPoint = lastPossiblePoint.left
            val cache = mutableMapOf<Pair<Int, Int>, Int>().apply {
                set(lastPossiblePoint, sea[lastPossiblePoint.first]
                        [lastPossiblePoint.second])
            }
            while (currentPoint.first >= 0) {
                while (currentPoint.second >= 0) {
                    val currentValue = sea[currentPoint.first][currentPoint.second]
                    listOf(currentPoint.down, currentPoint.right).filter {
                        it.first <= lastPossiblePoint.first &&
                                it.second <= lastPossiblePoint.second
                    }.mapNotNull {
                        cache[it]?.let { it + currentValue }
                    }.max()?.let {
                        cache[currentPoint] = max(currentValue, it)
                    }
                    println(cache)
                    currentPoint = currentPoint.left
                }
                currentPoint = currentPoint.up.copy(second = lastColumn)
            }
            cache.values.max() ?: 0
        }
    }

}

