package com.lagostout.elementsofprogramminginterviews.graphs

/**
 * Problem 19.4.3 page 369
 */
object GraphRemainsConnectedAfterRemovingAnyEdge {
    data class Node<out K>(val value: K)
    fun <K> graphRemainsConnectedAfterRemovingAnyEdge(
            graph: Map<Node<K>, List<Node<K>>>): Boolean {
        val nodeToIncomingEdgeCountMap = mutableMapOf<Node<K>, Int>()
        graph.values.forEach { adjacentNodes ->
            adjacentNodes.forEach { node ->
                nodeToIncomingEdgeCountMap.let {
                    it.put(node,
                            it.getOrPut(node, { 0 }) + 1)
                }
            }
        }
        nodeToIncomingEdgeCountMap.let {
            if (it.any { it.value == 0 })
                throw IllegalArgumentException("Graph must be connected, to begin with.")
            return it.any { it.value == 1 }
        }
    }
}