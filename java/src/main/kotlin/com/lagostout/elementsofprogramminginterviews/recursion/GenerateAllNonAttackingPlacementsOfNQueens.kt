package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.common.Position

/* Problem 16.2.1 page 290 */

object GenerateAllNonAttackingPlacementsOfNQueens {

    fun compute(n: Int): Set<Set<Position>> {
        val allPositions = (1..n).flatMap { row ->
            (1..n).map { col -> Position(row, col) }
        }
        val cache = mutableMapOf<Pair<Int, Set<Position>>, Set<Set<Position>>>()
        return compute(n, n, allPositions.toSet(), cache)
                .filter { it.isNotEmpty() }.toSet()
    }

    private fun compute(
            size: Int, queenCount: Int,
            freePositions: Set<Position>,
            cache: MutableMap<Pair<Int, Set<Position>>,
                    Set<Set<Position>>>): Set<Set<Position>> {
        val key = Pair(queenCount, freePositions)
        cache[key]?.let { return it }
        if (queenCount == 0) return setOf(emptySet())
        else if (freePositions.isEmpty()) return emptySet()
        return freePositions.flatMap { freePosition ->
            val nextFreePositions = freePositions.toMutableSet()
            nextFreePositions.remove(freePosition)
            // Row and column
            (1..size).forEach {
                nextFreePositions.removeAll(
                    listOf(Position(freePosition.row, it),
                        Position(it, freePosition.col)))
            }
            // Diagonals
            var leftCol = freePosition.col - 1
            var rightCol = freePosition.col + 1
            for (row in (freePosition.row - 1 downTo 1)) {
                if (leftCol >= 1) {
                    nextFreePositions.remove(Position(row, leftCol))
                }
                if (rightCol <= size) {
                    nextFreePositions.remove(Position(row, rightCol))
                }
                leftCol -= 1
                rightCol += 1
            }
            leftCol = freePosition.col - 1
            rightCol = freePosition.col + 1
            for (row in (freePosition.row + 1)..size) {
                if (leftCol >= 1) {
                    nextFreePositions.remove(Position(row, leftCol))
                }
                if (rightCol <= size) {
                    nextFreePositions.remove(Position(row, rightCol))
                }
                leftCol -= 1
                rightCol += 1
            }
            compute(size, queenCount.dec(), nextFreePositions, cache).map {
                it + freePosition
            }
        }.toSet().also {
            cache[key] = it
        }
    }

}
