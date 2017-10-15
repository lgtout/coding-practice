package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.isLeftChild
import com.lagostout.datastructures.BinaryTreeNode

/**
 * Problem 15.5 page 266
 */
fun <T : Comparable<T>> reconstructBSTFromPostorderTraversal(
        path: List<T>): BinaryTreeNode<T>? {
    if (path.isEmpty()) return null
    val pathIterator = path.reversed().iterator()
    val root = BinaryTreeNode(value = pathIterator.next())
    var treeNode = root
    while (true) {
        if (!pathIterator.hasNext()) break
        val node = BinaryTreeNode(value = pathIterator.next())
        if (node.value > treeNode.value) {
            treeNode.right = node
            node.parent = treeNode
        } else {
            // TODO
            // Not sure about this. How do we climb up the tree?
            // _node_ may be the left child of the _treeNode_, or
            // the left child of one of _treeNode_'s left ancestors.
            // But we don't want to have to find out if _treeNode_
            // has a left ancestor, every time we need to add a left
            // child. How do we avoid this?
            run {
                while (true) {
                    treeNode.parent?.let {
                        if (it.isLeftChild) return@run
                        treeNode = it
                    }
                }
            }
        }
    }
    return root
}

// TODO Redo like postorder, taking advantage of position in preorder. (Maybe not?)
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