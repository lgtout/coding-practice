package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.datastructures.BinaryTreeNode
import java.util.*

/**
 * Problem 9.7.1 page 144
 */
fun <T : Comparable<T>> computeBinaryTreeNodesInOrderOfIncreasingDepth(
        root: BinaryTreeNode<T>) : List<List<BinaryTreeNode<T>>> {
    val deque = LinkedList<BinaryTreeNode<T>>().apply {
        addLast(root)
    }
    val orderedNodes = mutableListOf<List<BinaryTreeNode<T>>>()
    while (deque.isNotEmpty()) {
        val nodes = mutableListOf<BinaryTreeNode<T>>()
        (0 until deque.size).forEach {
            deque.removeFirst().let {
                nodes.add(it)
                listOf(it.left, it.right).filterNotNull().forEach {
                    deque.addLast(it)
                }
            }
        }
        orderedNodes.add(nodes)
    }
    return orderedNodes
}