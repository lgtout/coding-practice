package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode

/* Problem 15.10.1 page 277 */

object BSTInsertionAndDeletion {

    // We assume the tree is never empty i.e. the root is never null.

    fun <T : Comparable<T>> insert(value: T, root: BinaryTreeNode<T>) {
        val duplicateOrParent = find(value, root)
        if (duplicateOrParent.value == value) return
        duplicateOrParent.let {
            // We know the parent is a leaf, so we don't need to
            // consider its left/right children.
            (if (it.value > value) it::right else it::left).set(it)
        }
    }

    /* We see two possible approaches.  The first is to push the node we're
    deleting down the tree, till it's a leaf, then pruning it from the tree.
    The second is to replace the node-to-delete with either the node with the
    largest value in the node-to-delete's left subtree or the one with the
    smallest value in the node-to-delete's right subtree.  We choose to
    implement the second approach, as it seems simpler. */

    fun <T : Comparable<T>> delete(value: T, root: BinaryTreeNode<T>):
            BinaryTreeNode<T>? {
        var modifiedTreeRoot: BinaryTreeNode<T>? = root
        val nodeToDelete = find(value, root)
        if (nodeToDelete.value != value) return modifiedTreeRoot
        if (nodeToDelete == root) modifiedTreeRoot = nodeToDelete.left ?: nodeToDelete.right
        while (true) {
            val left: BinaryTreeNode<T>?
            val right: BinaryTreeNode<T>?
            val replacementNode: BinaryTreeNode<T>?
            if (nodeToDelete.hasLeft) {
                replacementNode = nodeToDelete.left
                right = nodeToDelete.right
                left = nodeToDelete
            } else if (nodeToDelete.hasRight) {
                replacementNode = nodeToDelete.right
                right = nodeToDelete
                left = nodeToDelete.left
            } else {
                nodeToDelete.parent = null
                break
            }
            replacementNode?.parent = nodeToDelete.parent
            nodeToDelete.parent = replacementNode
            nodeToDelete.left = replacementNode?.left
            nodeToDelete.right = replacementNode?.right
            replacementNode?.left = left
            replacementNode?.right = right
        }

        return modifiedTreeRoot
    }

    private fun <T : Comparable<T>> find(
            root: BinaryTreeNode<T>,
            compareTo: (T) -> Int):
            BinaryTreeNode<T> {
        var currentNode = root
        while (true) {
            when (compareTo(currentNode.value)) {
                0 -> currentNode
                1 -> {
                    currentNode.right?.let {
                        currentNode = it
                    }
                }
                -1 -> {
                    currentNode.left?.let {
                        currentNode = it
                    }
                }
                else -> null
            } ?: break
        }
        return currentNode
    }

    private fun <T : Comparable<T>> find(
            value: T, root: BinaryTreeNode<T>): BinaryTreeNode<T> {
        return find(root) { value.compareTo(it) }
    }

}