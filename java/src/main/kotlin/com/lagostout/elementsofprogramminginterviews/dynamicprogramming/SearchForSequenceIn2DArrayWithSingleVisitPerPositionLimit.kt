package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.common.down
import com.lagostout.common.left
import com.lagostout.common.right
import com.lagostout.common.up

/* Problem 17.5.2 page 322 */

object SearchForSequenceIn2DArrayWithSingleVisitPerPositionLimit {

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
                fun compute(start: Pair<Int, Int>, patternIndex: Int, visited: MutableSet<Pair<Int, Int>> = mutableSetOf()): Boolean {
                    return when {
                        grid[start.first][start.second] !=
                                pattern[patternIndex] -> false
                        patternIndex == pattern.lastIndex -> true
                        else -> start.run {
                            listOf(up, down, left, right)
                        }.filter {
                            it !in visited && it.first in (0..rowCount) &&
                                    it.second in (0..colCount)
                        }.map {
                            compute(it, patternIndex + 1,
                                visited.apply { add(start) })
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
                fun compute(
                        start: Pair<Int, Int>, patternIndex: Int,
                        visited: MutableSet<Pair<Int, Int>> = mutableSetOf()): Boolean {
                    val isValidPosition = start.first in (0..lastRow) &&
                                start.second in (0..lastCol)
                    return cache[start]?.get(patternIndex) ?: when {
                        !isValidPosition -> false
                        grid[start.first][start.second] !=
                                pattern[patternIndex] -> false
                        patternIndex == pattern.lastIndex -> true
                        else -> start.run {
                            listOf(up, down, left, right).filter {
                                it !in visited
                            }
                        }.map {
                            compute(it, patternIndex + 1, visited.apply { add(start) })
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
                return search(0)
            }
        }
    }

    fun computeBottomUpWithMemoization(
            grid: List<List<Int>>, pattern: List<Int>): Boolean {
        return when {
            grid.isEmpty() -> pattern.isEmpty()
            pattern.isEmpty() -> true
            else -> {
                val lastCacheRow = grid.lastIndex
                val lastCacheCol = grid.first().lastIndex
                val positions = (0..lastCacheRow).flatMap { row ->
                    (0..lastCacheCol).map { col ->
                        Pair(row, col)
                    }
                }
                val cache = positions.map { position ->
                    position to (-1..pattern.lastIndex).map { patternIndex ->
                        patternIndex to Pair(
                            if (patternIndex == -1) true else null,
                            mutableSetOf<Pair<Int, Int>>())
                    }.toMap().toMutableMap()
                }.toMap()
                (-1 until pattern.lastIndex).map { patternIndex ->
                    positions.filter {
                        cache[it]?.let {
                            it[patternIndex]?.first
                        } == true
                    }.forEach { position ->
                        val visited = cache[position]?.get(patternIndex)?.second!!
                        position.run {
                            if (patternIndex == -1) listOf(position)
                            else listOf(up, down, left, right)
                                    .filter { it !in visited }
                        }.forEach { nextPosition ->
                            cache[nextPosition]?.let {
                                it.merge(
                                    patternIndex + 1,
                                    Pair(pattern[patternIndex + 1] ==
                                            grid[nextPosition.first][nextPosition.second],
                                        visited.run {
                                            toMutableSet().apply { add(position) }
                                        }),
                                    { old, new ->
                                        if (new.first == true) {
                                            Pair(true, old.second.apply {
                                                addAll(new.second)
                                            })
                                        } else old
                                    })
                            }
                        }
                    }
                }
                cache.any { it.value[pattern.lastIndex]?.first == true }
            }
        }
    }

}