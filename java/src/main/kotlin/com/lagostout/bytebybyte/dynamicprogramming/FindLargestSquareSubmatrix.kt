package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.bytebybyte.dynamicprogramming.FindLargestSquareSubmatrix.Position.Companion.p

/*
Given a 2D boolean array, find the largest square subarray of true values.
The return value should be the side length of the largest square subarray
subarray.
Eg.
squareSubmatrix([false, true, false, false]) = 2
                [true,  TRUE, TRUE,  true ]
                [false, TRUE, TRUE,  false]
(the solution is the submatrix in all caps)
*/

object FindLargestSquareSubmatrix {

    data class Position(val row: Int, val col: Int) {
        fun shiftRight(): Position {
            return copy(col = col + 1)
        }
        fun shiftLeft(): Position {
            return copy(col = col - 1)
        }
        fun shiftDown(): Position {
            return copy(row = row + 1)
        }
        fun shiftUp(): Position {
            return copy(row = row - 1)
        }
        companion object {
            fun p(row: Int, col: Int): Position {
                return Position(row, col)
            }
        }
    }

    data class Rectangle(private val topCorner: Position,
                         val bottomCorner: Position) {
        val width = bottomCorner.col - topCorner.col + 1
        val height = bottomCorner.row - topCorner.row + 1
        val size = width * height
        val isFlat = width > height
        val isTall = width < height
        val left = topCorner.col
        val right = bottomCorner.col
        val top = topCorner.row
        val bottom = bottomCorner.row
        val isSquare = width == height
        fun shiftRight(): Rectangle {
            return copy(topCorner = topCorner.shiftRight(),
                    bottomCorner = bottomCorner.shiftRight())
        }
        // TODO Remove?
//        fun shiftLeft(): Rectangle {
//            return copy(topCorner = topCorner.shiftLeft(),
//                bottomCorner = bottomCorner.shiftLeft())
//        }
        fun shiftDown(): Rectangle {
            return copy(topCorner = topCorner.shiftDown(),
                    bottomCorner = bottomCorner.shiftDown())
        }
        // TODO Remove?
//        fun shiftUp(): Rectangle {
//            return copy(topCorner = topCorner.shiftUp(),
//                bottomCorner = bottomCorner.shiftUp())
//        }
        companion object {
            fun r(topCorner: Position, bottomCorner: Position): Rectangle {
                return Rectangle(topCorner, bottomCorner)
            }
        }
    }

    fun computeWithBruteForceAndRecursion(
            array: Array<Array<Boolean>>,
            rectangle: Rectangle = Rectangle(p(0, 0),
                p(array.lastIndex, array[0].lastIndex))):
            Rectangle? {
        var subSquare = if (rectangle.isSquare) { rectangle }
        else {
            rectangle.let {
                if (it.isFlat) it.bottomCorner.copy(col = it.left + it.height - 1)
                else it.bottomCorner.copy(row = it.top + it.width - 1)
            }.let { rectangle.copy(bottomCorner = it) }
        }
        var largestSquare: Rectangle? = null
        while (subSquare.bottom <= rectangle.bottom &&
                subSquare.right <= rectangle.right) {
            _computeWithBruteForceAndRecursion(array, subSquare).let { result ->
                largestSquare = largestSquare?.let { ls ->
                    result?.let {
                        if (it.size > ls.size) it else null
                    } ?: ls
                } ?: result
            }
            subSquare = with (subSquare) {
                (if (rectangle.isFlat) ::shiftRight else ::shiftDown)() }
        }
        return largestSquare
    }

    @Suppress("FunctionName")
    private fun _computeWithBruteForceAndRecursion(
            array: Array<Array<Boolean>>, square: Rectangle): Rectangle? {
        return if (square.size == 1) {
            square.bottomCorner.run {
                if (array[row][col]) square else null
            }
        } else {
            val subSquare: Rectangle = square.copy(
                bottomCorner = square.bottomCorner.shiftUp().shiftLeft())
            listOf(subSquare, subSquare.shiftRight(), subSquare.shiftDown(),
                subSquare.shiftDown().shiftRight()).mapNotNull {
                _computeWithBruteForceAndRecursion(array, it) }.let {
                if (it.size == 4 && it.all { it.size == subSquare.size }) square
                else it.maxBy { it.size }
            }
        }
    }

    data class Result(val isAllTrue: Boolean, val largestRectangle: Rectangle?)

    // TODO Redo like above
    fun computeWithRecursionAndMemoization(
            array: Array<Array<Boolean>>,
            rectangle: Rectangle = Rectangle(p(0, 0),
                p(array.lastIndex, array[0].lastIndex)),
            cache: MutableMap<Rectangle, Result> = mutableMapOf()): Result {
        return cache[rectangle] ?: run {
            // Base case
            if (rectangle.size == 1) {
                return rectangle.bottomCorner.run {
                    (array[row][col]).let {
                        Result(it, if (array[row][col]) rectangle else null)
                    }
                }
            }
            // Determine the dimensions of the subsection we'll examine next
            var subRectangle = rectangle.let {
                (when {
                    it.isFlat -> it.bottomCorner.copy(col = it.left + it.height - 1)
                    it.isTall -> it.bottomCorner.copy(row = it.top + it.width - 1)
                    // Shrink vertically, if rectangle is square.
                    else -> it.bottomCorner.copy(row = it.bottomCorner.row - 1)
                }).let {
                        bottomCorner -> it.copy(bottomCorner = bottomCorner) }
            }
            // Examine all the subsections of the current rectangle
            var isAllTrue = true
            var largestSquare: Rectangle? = null
            while (true) {
                val result = computeWithRecursionAndMemoization(
                    array, subRectangle, cache)
                isAllTrue = isAllTrue && result.isAllTrue
                largestSquare = if (largestSquare == null ||
                        (result.largestRectangle != null &&
                                result.largestRectangle.size > largestSquare.size)) {
                    result.largestRectangle
                } else largestSquare
                subRectangle = if (rectangle.isFlat) { subRectangle::shiftRight}
                // If rectangle is square or tall, shift subRectangle vertically.
                else { subRectangle::shiftDown }()
                if ((subRectangle.bottomCorner.col > rectangle.right) ||
                        subRectangle.bottomCorner.row > rectangle.bottom) break
            }
            if (isAllTrue && rectangle.isSquare) largestSquare = rectangle
            Result(isAllTrue, largestSquare).also {
                cache[rectangle] = it
            }
        }
    }

    // TODO
    fun computeWithMemoizationBottomUp(array: Array<Array<Boolean>>) {
        val cache: MutableMap<Rectangle, Result> = mutableMapOf()
        var squareWidth = 1
        val bottomRow = array.lastIndex
        val rightCol = array[0].lastIndex
        var square = Rectangle(Position(0,0), Position(0,0))
        while (true) {
            var squareStart = square
            while (square.bottom <= bottomRow) {
                while (square.right <= rightCol) {
                    // Compute largest subsquare
                    val subResults = square.copy(
                        bottomCorner = Position(square.bottom - 1, square.right - 1)).let {
                        listOf(it, it.shiftRight(), it.shiftDown(), it.shiftRight().shiftDown())
                    }.map { cache[it] }
                    cache[square] = if (subResults.all { it?.isAllTrue == true })
                        Result(true, square)
                    else subResults.filterNotNull().mapNotNull {
                        it.largestRectangle }.sortedBy { it.size }.last().let {
                        Result(false, square) }
                    square = square.shiftRight()
                }
                // Shift square to next row
                square = squareStart.shiftDown().also {
                    squareStart = it
                }
            }
        }
    }

//    fun computeWithBruteForceAndRecursion() {}
//    fun computeWithRecursionAndMemoization() {}
//    fun computeWithRecursionAndMemoization() {}
//    fun computeWithMemoizationBottomUp() {}
}