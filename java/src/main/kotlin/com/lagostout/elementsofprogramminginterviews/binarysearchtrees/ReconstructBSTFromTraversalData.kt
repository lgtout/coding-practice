package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.isLeftChild
import com.lagostout.datastructures.BinaryTreeNode

/**
 * Problem 15.5 page 266
 */
fun <T : Comparable<T>> reconstructBSTFromPostorderTraversal(
        path: List<T>): BinaryTreeNode<T>? {
    if (path.isEmpty()) return null
    val elementToPostorderIndex = path.withIndex().map {
        it.value to it.index
    }.toMap()
    val pathIterator = path.iterator()
    var root: BinaryTreeNode<T>? = null
    var treeNode = BinaryTreeNode(value = pathIterator.next())
    while (true) {
        if (!pathIterator.hasNext()) break
        val node = BinaryTreeNode(value = pathIterator.next())
        compareValues(elementToPostorderIndex[node.value],
                elementToPostorderIndex[treeNode.value]).let {
            when (it) {
                1 -> {
                    treeNode.parent?.let {
                        treeNode = it
                    }?: run {
                        node.left = treeNode
                        treeNode.parent = node
                        root = node
                    }
                }
                else -> {
                    if (node.value < treeNode.value) {
                        treeNode.left = node
                        node.parent = treeNode
                    } else {
                        treeNode.right = node
                        node.parent = treeNode
                    }
                    treeNode = node
                }
            }
        }
    }
    return root
}

// TODO Redo like postorder, taking advantage of position in preorder.
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