package com.lagostout.common

import org.apache.commons.math3.random.RandomDataGenerator

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

fun <T : Comparable<T>> buildRandomBinarySearchTree(
        vertexCount: Int,
        random: RandomDataGenerator = RandomDataGenerator()):
        BinaryTreeNode<T>? {
    return null
}