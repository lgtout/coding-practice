package com.lagostout.elementsofprogramminginterviews.recursion

/* Problem 16.2.1 page 290 */

object GenerateAllNonAttackingPlacementsOfNQueens {

    data class Position(val row: Int, val col: Int)

    fun compute(n: Int): List<List<Position>> {
        val allPositions = (1..n).flatMap { row ->
            (1..n).map { col -> Position(row, col) }
        }
        return compute(n, n, allPositions.toMutableSet())
    }

    private fun compute(
            size: Int, queenCount: Int,
            freePositions: MutableSet<Position>): List<List<Position>> {
        if (queenCount == 0 || freePositions.isEmpty()) return mutableListOf()
        return freePositions.flatMap { freePosition ->
            val nextFreePositions = freePositions.toMutableSet()
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
                    nextFreePositions.remove(Position(row, leftCol.dec()))
                }
                if (rightCol <= size) {
                    nextFreePositions.remove(Position(row, rightCol.inc()))
                }
            }
            leftCol = freePosition.col - 1
            rightCol = freePosition.col + 1
            for (row in (freePosition.row + 1)..size) {
                if (leftCol >= 1) {
                    nextFreePositions.remove(Position(row, leftCol.dec()))
                }
                if (rightCol <= size) {
                    nextFreePositions.remove(Position(row, rightCol.inc()))
                }
            }
            compute(size, queenCount.inc(), nextFreePositions).map {
                it + freePosition
            }
        }
    }

}
