package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.common.Position

/* Problem 16.2.2 page 292 */

object GenerateNumberOfNonAttackingPlacementsOfNQueens {

    /* Introducing caching does not provide any run-time
    benefits because the two recursive subcalls to compute
    never have overlapping inputs i.e. parameters. */

    fun compute(n: Int): Int {
        if (n == 0) return 0
        val allPositions = (1..n).flatMap { row ->
            (1..n).map { col -> Position(row, col) }
        }
        return compute(n, emptySet(), allPositions.toSet())
    }

    @Suppress("NAME_SHADOWING")
    private fun compute(
            size: Int,
            selectedPositions: Set<Position>,
            freePositions: Set<Position>): Int {

        if (selectedPositions.count() == size) return 1
        else if (freePositions.isEmpty()) return 0

        return freePositions.first().let { currentPosition ->
            val nextFreePositions = freePositions.toMutableSet()

            // Exclude row and column
            (1..size).forEach {
                nextFreePositions.removeAll(
                    listOf(Position(currentPosition.row, it),
                        Position(it, currentPosition.col)))
            }

            // Exclude diagonals
            var leftCol = currentPosition.col - 1
            var rightCol = currentPosition.col + 1
            for (row in (currentPosition.row - 1 downTo 1)) {
                if (leftCol >= 1) {
                    nextFreePositions.remove(Position(row, leftCol))
                }
                if (rightCol <= size) {
                    nextFreePositions.remove(Position(row, rightCol))
                }
                leftCol -= 1
                rightCol += 1
            }
            leftCol = currentPosition.col - 1
            rightCol = currentPosition.col + 1
            for (row in (currentPosition.row + 1)..size) {
                if (leftCol >= 1) {
                    nextFreePositions.remove(Position(row, leftCol))
                }
                if (rightCol <= size) {
                    nextFreePositions.remove(Position(row, rightCol))
                }
                leftCol -= 1
                rightCol += 1
            }

            compute(size, selectedPositions + currentPosition, nextFreePositions) +
                    compute(size, selectedPositions, freePositions - currentPosition)
        }

    }

}
