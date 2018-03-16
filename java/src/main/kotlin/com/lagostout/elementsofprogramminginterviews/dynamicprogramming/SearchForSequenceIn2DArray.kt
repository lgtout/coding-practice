package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.common.down
import com.lagostout.common.left
import com.lagostout.common.right
import com.lagostout.common.up

/* Problem 17.5.1 page 320 */

object SearchForSequenceIn2DArray {

    fun computeWithRecursionAndBruteForce(
            grid: List<List<Int>>, pattern: List<Int>): Boolean {
        return when {
            grid.isEmpty() -> pattern.isEmpty()
            pattern.isEmpty() -> true
            else -> {
                val rowCount = grid.lastIndex
                val colCount = grid.first().lastIndex
                val positions = grid.withIndex().flatMap { (rowIndex, row) ->
                    row.indices.map {
                        Pair(rowIndex, it)
                    }
                }
                fun compute(start: Pair<Int, Int>, patternIndex: Int): Boolean {
                    return when {
                        grid[start.first][start.second] !=
                                pattern[patternIndex] -> false
                        patternIndex == pattern.lastIndex -> true
                        else -> start.run {
                            listOf(up, down, left, right)
                        }.filter {
                            it.first in (0..rowCount) &&
                                    it.second in (0..colCount)
                        }.map {
                            compute(it, patternIndex + 1)
                        }.any { it }
                    }
                }
                fun search(positionsIndex: Int): Boolean {
                    return if (positionsIndex > positions.lastIndex) false
                    else search(positionsIndex + 1) ||
                            compute(positions[positionsIndex], 0)
                }
                return search(0)
            }
        }
    }

    fun computeWithRecursionAndMemoization(
            grid: List<List<Int>>, pattern: List<Int>): Boolean {
        return when {
            grid.isEmpty() -> pattern.isEmpty()
            pattern.isEmpty() -> true
            else -> {
                val lastRow = grid.lastIndex
                val lastCol = grid.first().lastIndex
                val positions = grid.withIndex().flatMap { (rowIndex, row) ->
                    row.indices.map {
                        Pair(rowIndex, it)
                    }
                }
                val cache = mutableMapOf<Pair<Int, Int>, MutableList<Boolean?>>()
                fun compute(start: Pair<Int, Int>, patternIndex: Int): Boolean {
                    val isValidPosition = start.first in (0..lastRow) &&
                                start.second in (0..lastCol)
                    return cache[start]?.get(patternIndex)?.also {
//                        println("hit start: $start, patternIndex: $patternIndex")
                    } ?: when {
                        !isValidPosition -> false
                        grid[start.first][start.second] !=
                                pattern[patternIndex] -> false
                        patternIndex == pattern.lastIndex -> true
                        else -> start.run {
                            listOf(up, down, left, right)
                        }.map {
                            compute(it, patternIndex + 1)
                        }.any { it }
                    }.also {
                        cache.getOrPut(start){
                            MutableList(pattern.size) { null }
                        }[patternIndex] = it
                    }
                }
                fun search(positionsIndex: Int): Boolean {
                    return if (positionsIndex > positions.lastIndex) false
                    // Swapping the OR operands short-circuits the search,
                    // completing as soon as the first sequence is found.
                    // This prevents the cache from being useful.  Having
                    // them as follows makes use of the cache, as every
                    // possible sequence in the grid is explored.
                    else search(positionsIndex + 1) ||
                            compute(positions[positionsIndex], 0)

                }
                println(positions)
                return search(0).also {
//                    println(cache)
                }
            }
        }
    }

    fun computeBottomUpWithMemoization(
            grid: List<List<Int>>, pattern: List<Int>): Boolean {
        return when {
            grid.isEmpty() -> pattern.isEmpty()
            pattern.isEmpty() -> true
            else -> {
                val lastCacheRow = grid.size
                val lastCacheCol = grid.first().size
                val positions = (-1..lastCacheRow).flatMap { row ->
                    (-1..lastCacheCol).map { col ->
                        Pair(row, col)
                    }
                }
                val cache = positions.map { position ->
                    position to (-1..pattern.lastIndex).map { patternIndex ->
                        patternIndex to when {
                            patternIndex == -1 -> true
                            position.first in setOf(-1, lastCacheRow) ||
                                    position.second in setOf(-1, lastCacheCol) -> false
                            else -> null
                        }
                    }.toMap().toMutableMap()
                }.toMap()
                (1..pattern.lastIndex).map { patternIndex ->
                    (0 until lastCacheRow).forEach { row ->
                        (0 until lastCacheCol).forEach { col ->
                            val position = Pair(row, col)
                            cache[position]?.put(patternIndex, position.run {
                                listOf(up, down, left, right).map {
                                    cache[it]!![patternIndex - 1]!!
                                }.any { it }
                            })
                        }
                    }
                }
                cache.any { it.value[pattern.lastIndex] == true }
            }
        }
    }

}