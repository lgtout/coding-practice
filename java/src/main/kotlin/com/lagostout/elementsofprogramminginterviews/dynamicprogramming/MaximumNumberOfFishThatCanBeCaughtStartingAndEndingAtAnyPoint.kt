package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.common.down
import com.lagostout.common.right

object MaximumNumberOfFishThatCanBeCaughtStartingAndEndingAtAnyPoint {

    fun computeWithRecursionAndBruteForce(
            sea: List<List<Int>>, origin: Pair<Int, Int> = Pair(0,0)): Int {
        val limitPoint = Pair(sea.lastIndex, sea.first().lastIndex)
        fun compute(origin: Pair<Int, Int>): Int {
            return mutableListOf(sea[origin.first][origin.second]).run {
                addAll(
                    listOf(origin.right(), origin.down()).filter {
                        it.first <= limitPoint.first && it.second <= limitPoint.second
                    }.map {
                        compute(it) + first()
                    })
                max()!!
            }
        }
        return (origin.first..limitPoint.first).flatMap { row ->
            (origin.second..limitPoint.second).map { col ->
                Pair(row, col)
            }
        }.map { compute(it) }.max()!!
    }

    fun computeWithRecursionAndMemoization(
            sea: List<List<Int>>, origin: Pair<Int, Int> = Pair(0,0)): Int {
        val cache = mutableMapOf<Pair<Int, Int>, Int>()
        val limitPoint = Pair(sea.lastIndex, sea.first().lastIndex)
        fun compute(origin: Pair<Int, Int>): Int {
            return cache[origin] ?: mutableListOf(sea[origin.first][origin.second]).run {
                addAll(
                    listOf(origin.right(), origin.down()).filter {
                        it.first <= limitPoint.first && it.second <= limitPoint.second
                    }.map {
                        compute(it) + first()
                    })
                max()!!
            }.also {
                cache[origin] = it
            }
        }
        return (origin.first..limitPoint.first).flatMap { row ->
            (origin.second..limitPoint.second).map { col ->
                Pair(row, col)
            }
        }.map { compute(it) }.max()!!
    }

}

