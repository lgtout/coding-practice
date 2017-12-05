package com.lagostout.elementsofprogramminginterviews.graphs

/**
 * Problem 19.2 page 370
 */
fun <T> computeIfBipartiteGraphIsPossible(nodes: List<GraphNode<T>>): Boolean {
    var groups = listOf(mutableSetOf<GraphNode<T>>(),
            mutableSetOf())
    val nodesIterator = nodes.iterator()
    var currentLevelNodes = mutableSetOf<GraphNode<T>>()
    val exploredNodes = mutableSetOf<GraphNode<T>>()
    var bipartiteGraphIsPossible = true
    run {
        while (true) {
            if (currentLevelNodes.isEmpty()) {
                if (!nodesIterator.hasNext()) break
                val node = nodesIterator.next()
                if (node in exploredNodes) continue
                currentLevelNodes.add(node)
                groups[0].add(node)
                groups = groups.reversed()
            }
            val nextLevelNodes = mutableSetOf<GraphNode<T>>()
            currentLevelNodes.forEach { currentLevelNode ->
                exploredNodes.add(currentLevelNode)
                currentLevelNode.adjacentNodes.minus(currentLevelNode).also {
                    nextLevelNodes.addAll(it)
                    it.forEach {
                        if (it in groups[1]) {
                            bipartiteGraphIsPossible = false
                            return@run
                        } else groups[0].add(it)
                    }
                }
            }
            currentLevelNodes = nextLevelNodes
            groups = groups.reversed()
        }
    }
    return bipartiteGraphIsPossible
}