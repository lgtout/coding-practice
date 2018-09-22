package com.lagostout.elementsofprogramminginterviews.recursion;

import com.lagostout.common.Position

/* Problem 16.2.3 page 292 */

fun smallestNumberOfQueensToAttackUncoveredSquares(dim: Int): Int {

    val lastSquare = dim * dim

    fun compute(square: Int, queenCount: Int, uncoveredSquares: MutableSet<Int>): Int? {

        if (uncoveredSquares.isEmpty()) return queenCount
        else if (square > lastSquare) return null

        val squarePosition = Position((square - 1) / dim + 1, square % dim)
        val coveredByCurrentSquare = uncoveredSquares.filter {
            val uncoveredSquarePosition = Position((it - 1) / dim + 1, it % dim)
            uncoveredSquarePosition.col == squarePosition.col ||
                    uncoveredSquarePosition.row == squarePosition.row ||
                    uncoveredSquarePosition.run { row + col } == squarePosition.run { row + col } ||
                    uncoveredSquarePosition.run { row - col } == squarePosition.run { row - col }
        }

        // Select the current square
        uncoveredSquares.removeAll(coveredByCurrentSquare)
        val numberOfQueensBySelectingSquare = compute(square + 1, queenCount + 1, uncoveredSquares)

        // Omit the current square
        uncoveredSquares.addAll(coveredByCurrentSquare)
        val numberOfQueensByIgnoringSquare = compute(square + 1, queenCount, uncoveredSquares)

        return listOfNotNull(numberOfQueensBySelectingSquare,
            numberOfQueensByIgnoringSquare).min()
    }

    return compute(1, 0, (1..lastSquare).toMutableSet())!!

}
