package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

fun <T> detectDeadlock(graph: Map<T, List<T>>): Boolean {
    val nodeIterator = graph.keys.iterator()
    val stack = LinkedList<T>()
    val nodesInStack = mutableSetOf<T>()
    val explored = mutableSetOf<T>()
    var cycleDetected = false
    while (nodeIterator.hasNext() && !cycleDetected) {
        var node = nodeIterator.next()
        // This will prevent exploring nodes that
        // are part of components that have already
        // been explored.
        if (node in explored) continue
        stack.push(node)
        nodesInStack.add(node)
        while (!stack.isEmpty() && !cycleDetected) {
            node = stack.pop()
            nodesInStack.remove(node)
            // This will prevent adjacent nodes from
            // being considered a cycle.
            if (node in explored) continue
            explored.add(node)
            run {
                graph[node]?.forEach {
                    if (node in nodesInStack) {
                        cycleDetected = true
                        return@run
                    }
                    stack.push(node)
                    nodesInStack.add(node)
                }
            }
        }
    }
    return cycleDetected
}