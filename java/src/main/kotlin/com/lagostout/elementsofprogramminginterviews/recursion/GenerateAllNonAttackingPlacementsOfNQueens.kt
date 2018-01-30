package com.lagostout.elementsofprogramminginterviews.recursion

object GenerateAllNonAttackingPlacementsOfNQueens {

    data class Position(val row: Int, val col: Int)

    fun generateAllNonAttackingPlacementsOfNQueens(n: Int): Set<Set<Position>> {
        // TODO How do we call the recursive function?  Should we be iterating over the board?
//        generateAllNonAttackingPlacementsOfNQueens()
        return emptySet()
    }

    private fun generateAllNonAttackingPlacementsOfNQueens(queenCount: Int,
            attackedPositions: Set<Position>, freePositions: Set<Position>): Set<Position> {
        val currentAttackedPositions = mutableSetOf<Position>()
        val lastRowOrCol = queenCount - 1
        for (freePosition in freePositions) {
            // Compute attacked positions
            (0 until queenCount).forEach {
                // Row and column
                currentAttackedPositions.addAll(
                    listOf(Position(freePosition.row, it),
                    Position(it, freePosition.col)))
            }
            // Diagonal
            var leftCol = freePosition.col - 1
            var rightCol = freePosition.col + 1
            // TODO Consolidate for loops.
            for (row in (freePosition.row - 1)..0 step -1) {
                if (leftCol >= 0) {
                    currentAttackedPositions.add(Position(row, leftCol++))
                }
                if (rightCol <= lastRowOrCol) {
                    currentAttackedPositions.add(Position(row, rightCol++))
                }
            }
            for (row in (freePosition.row + 1)..lastRowOrCol) {
                if (leftCol >= 0) {
                    currentAttackedPositions.add(Position(row, leftCol++))
                }
                if (rightCol <= lastRowOrCol) {
                    currentAttackedPositions.add(Position(row, rightCol++))
                }
            }
        }
        return emptySet()
    }

}
