package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import com.lagostout.datastructures.BinaryTreeNode
import java.util.*

/**
 * Problem 9.7.3 page 146
 */
fun <T> computeBinaryTreeNodesInBottomUpLeftToRightOrder(root: BinaryTreeNode<T>): List<List<BinaryTreeNode<T>>> {
    val queue = LinkedList<List<BinaryTreeNode<T>>>().apply {
        add(listOf(root))
    }
    while (true) {
        queue.peekLast().let {
            val nextLevel = mutableListOf<BinaryTreeNode<T>>()
            it.forEach {
                listOfNotNull(it.left, it.right).forEach {
                    nextLevel.add(it)
                }
            }
            if (nextLevel.isEmpty()) null
            else queue.add(nextLevel)
        }?: break
    }
    val nodes = mutableListOf<List<BinaryTreeNode<T>>>()
    while (!queue.isEmpty()) {
        nodes.add(queue.removeLast())
    }
    return nodes
}