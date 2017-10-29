package com.lagostout.elementsofprogramminginterviews.graphs

/**
 * Problem 19.4.3 page 369
 */
// Might have been simpler and more appropriate to represent the
// graph with an edge list, instead of an adjacency list.
object GraphRemainsConnectedAfterRemovingAnyEdge {
    data class Node<out K>(val value: K)
    fun <K> graphRemainsConnectedAfterRemovingAnyEdge(
            graph: Map<Node<K>, Set<Node<K>>>): Boolean {
        val nodeToIncomingEdgeCountMap = mutableMapOf<Node<K>, Int>()
        graph.values.forEach { adjacentNodes ->
            adjacentNodes.forEach { node ->
                nodeToIncomingEdgeCountMap.let {
                    it.put(node, it.getOrPut(node, { 0 }) + 1)
                }
            }
        }
        return nodeToIncomingEdgeCountMap.let {
            if (it.any { it.value == 0 })
                throw IllegalArgumentException(
                        "Graph must be connected, to begin with.")
            graph.size == 1 || !it.containsValue(1)
        }
    }
}