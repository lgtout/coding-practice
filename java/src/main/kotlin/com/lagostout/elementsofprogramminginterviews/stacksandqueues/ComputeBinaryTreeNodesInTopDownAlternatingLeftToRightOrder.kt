package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import com.lagostout.datastructures.BinaryTreeNode
import java.util.*

/**
 * Problem 9.7.2 page 146
 */
fun <T : Comparable<T>> computeBinaryTreeNodesInTopDownAlternatingLeftToRightOrder(
        root: BinaryTreeNode<T>) : List<List<BinaryTreeNode<T>>> {
    var deque = LinkedList<BinaryTreeNode<T>>().apply {
        add(root)
    }
    var leftToRight = false
    val orderedNodes = mutableListOf<List<BinaryTreeNode<T>>>().apply {
        add(LinkedList<BinaryTreeNode<T>>(listOf(root)))
    }
    while (true) {
        val nodes = LinkedList<BinaryTreeNode<T>>()
        val nextDeque = LinkedList<BinaryTreeNode<T>>()
        (0 until deque.size).forEach {
            deque.removeFirst().let {
                listOf(it.left, it.right)
                        .filterNotNull().forEach {
                    nextDeque.addLast(it)
                    if (leftToRight) {
                        nodes.addLast(it)
                    } else {
                        nodes.addFirst(it)
                    }
                }
            }
        }
        if (nextDeque.isEmpty()) break
        deque = nextDeque
        leftToRight = !leftToRight
        orderedNodes.add(nodes)
    }
    return orderedNodes
}
