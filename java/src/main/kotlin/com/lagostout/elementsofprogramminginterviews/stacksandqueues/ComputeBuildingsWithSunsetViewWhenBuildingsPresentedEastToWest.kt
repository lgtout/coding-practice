package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import java.util.*

/**
 * Problem 9.6.1 page 141
 */
fun computeBuildingsWithSunsetViewWhenBuildingsPresentedEastToWest(
        buildings: List<Int>): List<Int> {
    if (buildings.isEmpty()) return emptyList()
    val stack = LinkedList<IndexedValue<Int>>()
    with (buildings.withIndex()) {
        forEach { currentBuilding ->
            while (true) {
                // The buildings in the stack are to the east
                // of the current building.
                // If the current building is the same or greater
                // height as the one in the stack, the current building
                // will block the latter's view.  So we remove the
                // building to the east from the stack.
                stack.peek()?.let {
                    if (it.value <= currentBuilding.value) {
                        stack.pop()
                    } else null
                } ?: break
            }
            stack.push(currentBuilding)
        }
    }
    return stack.map { it.index }.reversed()
}