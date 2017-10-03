package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.isLeftChild
import com.lagostout.common.isRightChild
import com.lagostout.common.isRoot
import com.lagostout.datastructures.BinaryTreeNode

/**
 * Problem 15.5 page 266
 */
fun <T : Comparable<T>> reconstructBSTFromPostorderTraversal(
        path: List<T>): BinaryTreeNode<T>? {
    if (path.isEmpty()) return null
    val pathIterator = path.reversed().iterator()
    val root = BinaryTreeNode(value = pathIterator.next())
    var previousNode = root
    pathIterator.forEach { value ->
        val currentNode = BinaryTreeNode(value = value)
        if (previousNode.isRoot) {
            currentNode.parent = previousNode
            if (currentNode.value < previousNode.value) {
                previousNode.left = currentNode
            } else {
                previousNode.right = currentNode
            }
        } else if (previousNode.isLeftChild &&
                currentNode.value < previousNode.value) {
            previousNode.left = currentNode
            currentNode.parent = previousNode
        } else if (previousNode.isRightChild &&
                currentNode.value > previousNode.value) {
            previousNode.right = currentNode
            currentNode.parent = previousNode
        } else {
            while (true) {
                // Climb toward root.
                // Attach currentNode as left if previousNode is
                // rightChild, or as right if previousNode is
                // leftChild.
                // Attach when currentNode value is <
                // previousNode.parent value when previousNode is
                // leftChild.
                // Or, when currentNode value is >
                // previousNode.parent value when previousNode is
                // rightChild.
            }
//            previousNode.parent?.let {
//                if (currentNode.value < it.value) {
//                    previousNode.right = currentNode
//                    currentNode.parent = previousNode
//                } else {
//                    previousNode = it
//                }
//            }
//            if (currentNode.value > previousNode.value) {
//                previousNode.right = currentNode
//                currentNode.parent = previousNode
//            }
//            previousNode.parent?.let {
//                if (currentNode.value > it.value)
//                    previousNode = it
//            }
        }
        previousNode = currentNode
    }
    return root
}

fun <T : Comparable<T>> reconstructBSTFromPreorderTraversal(
        path: List<T>): BinaryTreeNode<T>? {
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