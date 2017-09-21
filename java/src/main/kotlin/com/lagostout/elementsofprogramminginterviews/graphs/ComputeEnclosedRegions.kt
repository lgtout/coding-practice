package com.lagostout.elementsofprogramminginterviews.graphs

/**
 * Problem 19.3 page 365
 */

typealias Cell = Point<Boolean>

fun computeEnclosedRegionsAndFill(booleanMatrix: List<List<Boolean>>) {
    // Let's assume true = black, false = white
    val adjacencies = toGraph(booleanMatrix)
    val exploredCells = mutableSetOf<Cell>()
    val whiteBorderCells = mutableSetOf<Cell>()
    val nonBorderWhiteCells = mutableSetOf<Cell>()
    val borderCells = mutableSetOf<Cell>()
    // TODO Create set of all border cells, then filter by value.
    booleanMatrix.forEachIndexed { index, list ->
        when (index) {
            0, booleanMatrix.lastIndex -> {
                list.forEachIndexed { column, value ->
                    if (!value) whiteBorderCells.add(Cell(column, index))
                }
            }
            else -> {
                listOf(Pair(list.first(), 0),
                        Pair(list.last(), list.lastIndex)).forEach {
                    (value, column) ->
                    if (!value) whiteBorderCells.add(Cell(column, index))
                }
            }
        }
    }
    // TODO Compute components
}