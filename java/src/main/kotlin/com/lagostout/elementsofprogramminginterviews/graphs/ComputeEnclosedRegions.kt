package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

/**
 * Problem 19.3 page 365
 */

typealias Cell = Point<Boolean>

fun computeEnclosedRegionsAndFill(booleanMatrix: List<List<Boolean>>) {
    // Let's assume true = black, false = white
    val adjacencies = toGraph(booleanMatrix)
    val exploredCells = mutableSetOf<Cell>()
    val enclosedComponents = mutableSetOf<Set<Cell>>()
    val whiteBorderCells = booleanMatrix.withIndex().map { (index, list) ->
        when (index) {
            0, booleanMatrix.lastIndex -> {
                list.withIndex().map { (column, value) ->
                    Cell(column, index, value)
                }
            }
            else -> {
                listOf(Pair(list.first(), 0),
                        Pair(list.last(), list.lastIndex)).map {
                    (value, column) -> Cell(column, index, value)
                }
            }
        }
    }.flatten().filter { it.value == true }.toSet()
    adjacencies.keys.forEach {
        val currentComponent = mutableSetOf<Cell>()
        val stack = LinkedList<Cell>().apply { add (it) }
        while (stack.isNotEmpty()) {
            val currentCell = stack.pop()
            if (exploredCells.contains(it)) continue
            exploredCells.add(it)
            currentComponent.add(currentCell)
            adjacencies[currentCell]?.forEach {
                stack.push(it)
            }
        }
        if (currentComponent.any { whiteBorderCells.contains(it) })
            enclosedComponents.add(currentComponent)
    }
    // TODO Replace White with Black
}