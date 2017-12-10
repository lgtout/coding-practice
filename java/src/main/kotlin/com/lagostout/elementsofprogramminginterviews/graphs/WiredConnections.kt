package com.lagostout.elementsofprogramminginterviews.graphs

/**
 * Problem 19.2 page 370
 */
fun <T> computeIfBipartiteGraphIsPossible(nodes: List<GraphNode<T>>): Boolean {
    var groups = listOf(mutableSetOf<GraphNode<T>>(), mutableSetOf())
    val nodesIterator = nodes.iterator()
    var currentLevelNodes = setOf<GraphNode<T>>()
    val exploredNodes = mutableSetOf<GraphNode<T>>()
    var bipartiteGraphIsPossible = true
    run {
        while (true) {
//            println(currentLevelNodes)
//            println(exploredNodes)
            if (currentLevelNodes.isEmpty()) {
                if (!nodesIterator.hasNext()) break
                val node = nodesIterator.next()
                if (node in exploredNodes) continue
                currentLevelNodes = setOf(node)
                exploredNodes.add(node)
                groups[0].add(node)
                groups = groups.reversed()
            }
            val nextLevelNodes = currentLevelNodes
                    .flatMap { it.adjacentNodes }.toSet()
            if (nextLevelNodes.any { it in groups[1] }) {
                bipartiteGraphIsPossible = false
                break
            }
            currentLevelNodes = nextLevelNodes.filter {
                node -> node !in groups[0]
            }.also {
                groups[0].addAll(it)
            }.toSet()
            exploredNodes.addAll(currentLevelNodes)
            groups = groups.reversed()
        }
    }
    return bipartiteGraphIsPossible
}