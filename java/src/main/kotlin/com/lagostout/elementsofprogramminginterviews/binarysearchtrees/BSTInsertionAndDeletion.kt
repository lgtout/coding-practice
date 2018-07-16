package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode
import kotlin.reflect.KMutableProperty1

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

    fun <T : Comparable<T>> delete(value: T, root: BinaryTreeNode<T>):
            BinaryTreeNode<T>? {

        fun extractNode(
                node: BinaryTreeNode<T>,
                comparisonResult: (T) -> Int,
                leftOrRight: KMutableProperty1<
                        BinaryTreeNode<T>, BinaryTreeNode<T>?>): BinaryTreeNode<T>? {
            // Find the node with the smallest/largest value in the
            // right/left subtree.  It will only have a right/left
            // subtree, if any.
            val replacementNode = find(leftOrRight(node)!!, comparisonResult)
            // Detach the replacement and promote its left/right subtree.
            val parent = replacementNode.parent
            leftOrRight(replacementNode)?.parent = parent
            replacementNode.parent?.let {
                leftOrRight.set(it, leftOrRight(replacementNode))
            }
            return replacementNode
        }

        @Suppress("NAME_SHADOWING")
        val node =  find(value, root)
        // No such node in tree.
        if (node.value != value) return root

        // TODO Is this right?

        val replacementNode = if (node.hasLeft) {
            extractNode(node, { 1 }, BinaryTreeNode<T>::left)
        } else {
            extractNode(node, { -1 }, BinaryTreeNode<T>::right)
        }
        node.left = null
        node.right = null
        node.parent = null
        return if (root == node) replacementNode else root
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
        return find(root) { it.compareTo(value) }
    }

}