package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

/**
 * Problem 19.3 page 365
 */

typealias Cell = Point<Boolean>

fun computeEnclosedRegionsAndFill(booleanMatrix: List<MutableList<Boolean>>) {

    // Let's assume true = black, false = white
    val adjacencies = toGraph(booleanMatrix)
    val exploredCells = mutableSetOf<Cell>()

    // Find white border cells
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
    }.flatten().filter { it.value == false }.toSet()

    // Find components
    val components = mutableSetOf<Set<Cell>>()
    adjacencies.keys
            // Only white ones
            .filter { it.value == false }
            .forEach {
                val currentComponent = mutableSetOf<Cell>()
                val stack = LinkedList<Cell>().apply { add (it) }
                while (stack.isNotEmpty()) {
                    val currentCell = stack.pop()
                    if (exploredCells.contains(currentCell)) continue
                    exploredCells.add(currentCell)
                    currentComponent.add(currentCell)
                    adjacencies[currentCell]?.forEach {
                        stack.push(it)
                    }
                }
                if (currentComponent.isNotEmpty())
                    components.add(currentComponent)
            }
    val enclosedComponents = components.filterNot {
        it.any { whiteBorderCells.contains(it) }
    }

    // Replace white with black
    enclosedComponents.forEach { component ->
        component.forEach {
            booleanMatrix[it.row][it.column] = true
        }
    }
}