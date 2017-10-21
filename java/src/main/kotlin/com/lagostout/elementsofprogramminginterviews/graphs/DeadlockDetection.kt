package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

fun <T> detectDeadlock(digraph: List<GraphNode<T>>): Boolean {
    var deadlockFound = false
    val stack = LinkedList<GraphNode<T>>()
    val exploredNodes = mutableSetOf<GraphNode<T>>()
    val digraphIterator = digraph.iterator()
    while (digraphIterator.hasNext()) {
        stack.push(digraphIterator.next())
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            if (exploredNodes.contains(node)) {
                deadlockFound = true
                break
            }
            exploredNodes.add(node)
            node.adjacentNodes.forEach { stack.push(it) }
        }
        exploredNodes.clear()
        if (deadlockFound) break
    }
    return deadlockFound
}