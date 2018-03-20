package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.common.down
import com.lagostout.common.left
import com.lagostout.common.right
import com.lagostout.common.up

/* Problem 17.5.3 page 322 */

object EnumerateOccurrencesOfSequenceIn2DArrayWithSingleVisitPerPositionLimit {

    fun computeWithRecursionAndBruteForce(
            grid: List<List<Int>>, pattern: List<Int>):
            Set<List<Pair<Int, Int>>> {
        return when {
            grid.isEmpty() || pattern.isEmpty() -> emptySet()
            else -> {
                val rowCount = grid.lastIndex
                val colCount = grid.first().lastIndex
                val positions = grid.withIndex().flatMap {
                        (rowIndex, row) ->
                    row.indices.map {
                        Pair(rowIndex, it)
                    }
                }
                fun compute(start: Pair<Int, Int>, patternIndex: Int):
                        Set<List<Pair<Int, Int>>> {
                    return when {
                        grid[start.first][start.second] !=
                                pattern[patternIndex] -> emptySet()
                        patternIndex == pattern.lastIndex ->
                            setOf(listOf(start))
                        else -> start.run {
                            listOf(up, down, left, right)
                        }.filter {
                            it.first in (0..rowCount) &&
                                    it.second in (0..colCount)
                        }.flatMap {
                            compute(it, patternIndex + 1).filter {
                                start !in it
                            }.map {
                                listOf(start) + it
                            }
                        }.toSet()
                    }
                }
                fun search(positionsIndex: Int): Set<List<Pair<Int, Int>>> {
                    return if (positionsIndex > positions.lastIndex) emptySet()
                    else search(positionsIndex + 1).toMutableSet().apply {
                        addAll(compute(positions[positionsIndex], 0))
                    }
                }
                return search(0)
            }
        }
    }

    fun computeWithRecursionAndMemoization(
            grid: List<List<Int>>, pattern: List<Int>): Set<List<Pair<Int, Int>>> {
        return when {
            grid.isEmpty() || pattern.isEmpty() -> emptySet()
            else -> {
                val lastRow = grid.lastIndex
                val lastCol = grid.first().lastIndex
                val positions = grid.withIndex().flatMap { (rowIndex, row) ->
                    row.indices.map {
                        Pair(rowIndex, it)
                    }
                }
                val cache = mutableMapOf<Pair<Int, Int>,
                        MutableList<Set<List<Pair<Int, Int>>>?>>()
                fun compute(start: Pair<Int, Int>, patternIndex: Int):
                        Set<List<Pair<Int, Int>>> {
                    return cache[start]?.get(patternIndex) ?: when {
                        grid[start.first][start.second] !=
                                pattern[patternIndex] -> emptySet()
                        patternIndex == pattern.lastIndex -> setOf(listOf(start))
                        else -> run {
                            start.run {
                                listOf(up, down, left, right).filter {
                                    it.first in (0..lastRow) &&
                                            it.second in (0..lastCol)
                                }.flatMap {
                                    compute(it, patternIndex + 1).filter {
                                        start !in it
                                    }.map {
                                        listOf(start) + it
                                    }
                                }.toSet()
                            }
                        }
                    }.also { paths ->
                        cache.getOrPut(start) {
                            MutableList(pattern.size) { null }
                        }.let {
                            it[patternIndex] = paths
                        }
                    }
                }
                fun search(positionsIndex: Int): Set<List<Pair<Int, Int>>> {
                    return if (positionsIndex > positions.lastIndex) emptySet()
                    else search(positionsIndex + 1) +
                            compute(positions[positionsIndex], 0)

                }
                return search(0)
            }
        }
    }

    fun computeBottomUpWithMemoization(
            grid: List<List<Int>>, pattern: List<Int>):
            Set<List<Pair<Int, Int>>> {
        return when {
            grid.isEmpty() || pattern.isEmpty() -> emptySet()
            else -> {
                val lastRow = grid.lastIndex
                val lastCol = grid.first().lastIndex
                val positions = grid.withIndex().flatMap { (rowIndex, row) ->
                    row.indices.map {
                        Pair(rowIndex, it)
                    }
                }
                val cache = mutableMapOf<Pair<Int, Int>,
                        MutableList<Set<List<Pair<Int, Int>>>?>>()
                (pattern.indices).reversed().forEach { patternIndex ->
                    positions.forEach { start ->
                        val paths = when {
                            grid[start.first][start.second] !=
                                    pattern[patternIndex] -> emptySet()
                            patternIndex == pattern.lastIndex -> setOf(listOf(start))
                            else -> start.run {
                                listOf(up, down, left, right).filter {
                                    it.first in (0..lastRow) &&
                                            it.second in (0..lastCol)
                                }.flatMap {
                                    cache[it]?.get(patternIndex + 1)?.filter {
                                        start !in it
                                    }?.map {
                                        listOf(start) + it
                                    } ?: emptyList()
                                }.toSet()
                            }
                        }
                        cache.getOrPut(start) {
                            MutableList(pattern.size) { null }
                        }.let {
                            it[patternIndex] = paths
                        }
                    }
                }
                return cache.values.flatMap {
                    it[0] ?: emptySet()
                }.toSet()
            }
        }
    }

}