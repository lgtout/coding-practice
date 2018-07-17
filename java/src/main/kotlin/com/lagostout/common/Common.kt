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
    TODO("Implement when BinarySearchTree done")
//    return null
}

data class Position(val row: Int, val col: Int)

data class Quartet<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)

fun rdg(): RandomDataGenerator = RandomDataGenerator().apply { reSeed(1) }
