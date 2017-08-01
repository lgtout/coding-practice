package com.lagostout.common

/**
 * For breadth-first traversal.
 */
fun <T> nextLevel(graph: Map<T, Set<T>>, currentLevel: Set<T>, explored: Set<T>): Set<T> {
    return currentLevel.fold(mutableSetOf<T>()) {
        adjacentNodes, node ->
        graph[node]?.let {
            adjacentNodes.apply { addAll(filter {it !in explored }) }
        }
        adjacentNodes
    }
}
