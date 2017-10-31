package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import java.util.*

/**
 * Problem 9.6.2 page 143
 */
fun computeBuildingsWithSunsetViewWhenBuildingsPresentedWestToEast(
        buildings: List<Int>): List<Int> {
    if (buildings.isEmpty()) return emptyList()
    val stack = LinkedList<IndexedValue<Int>>()
    with (buildings.withIndex()) {
        forEach { currentBuilding ->
            (stack.peek()?.let {
                currentBuilding.value > it.value
            } ?: true).let {
                if (it) stack.push(currentBuilding)
            }
        }
    }
    return stack.map { it.index }.reversed()
}
