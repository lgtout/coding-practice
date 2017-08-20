package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.isLeftChild
import com.lagostout.common.isRightChild
import com.lagostout.datastructures.BinaryTreeNode

/**
 * Problem 15.5 page 266
 */

fun <T : Comparable<T>> reconstructBSTFromPostorderTraversal(path: List<T>): BinaryTreeNode<T>? {
    if (path.isEmpty()) return null
    val pathIterator = path.reversed().iterator()
    val root = BinaryTreeNode(value = pathIterator.next())
    var previousNode = root
    pathIterator.forEach { value ->
        val currentNode = BinaryTreeNode(value = value)
        when {
            value > previousNode.value -> {
                currentNode.parent = previousNode
                previousNode.right = currentNode
            }
            value < previousNode.value -> {
                while (true) {
                    previousNode.parent?.let { parent ->
                        if (previousNode.isRightChild && value < parent.value)
                            previousNode = parent
                    } ?: break
                }
                currentNode.parent = previousNode
                previousNode.left = currentNode
            }
        }
    }
    return root
}

fun <T : Comparable<T>> reconstructBSTFromPreorderTraversal(path: List<T>): BinaryTreeNode<T>? {
    if (path.isEmpty()) return null
    val pathIterator = path.iterator()
    val root = BinaryTreeNode(value = pathIterator.next())
    var previousNode = root
    pathIterator.forEach { value ->
        val currentNode = BinaryTreeNode(value = value)
        when {
            value < previousNode.value -> {
                currentNode.parent = previousNode
                previousNode.left = currentNode
            }
            value > previousNode.value -> {
                while (true) {
                    previousNode.parent?.let { parent ->
                        if (previousNode.isLeftChild && value > parent.value)
                            previousNode = parent
                    } ?: break
                }
                currentNode.parent = previousNode
                previousNode.right = currentNode
            }
        }
        previousNode = currentNode
    }
    return root
}