package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.isLeftChild
import com.lagostout.common.isRightChild
import com.lagostout.datastructures.BinaryTreeNode

/* Problem 15.10.1 page 277 */

object BSTInsertionAndDeletion {

    private fun <T : Comparable<T>> find(
            root: BinaryTreeNode<T>,
            compareTo: (T) -> Int):
            BinaryTreeNode<T> {
        var currentNode = root
        while (true) {
            when (compareTo(currentNode.value)) {
                0 -> null
                1 -> {
                    currentNode.left?.let {
                        currentNode = it
                    }
                }
                else -> {
                    currentNode.right?.let {
                        currentNode = it
                    }
                }
            } ?: break
        }
        return currentNode
    }

    private fun <T : Comparable<T>> find(
            value: T, root: BinaryTreeNode<T>): BinaryTreeNode<T> {
        return find(root, { it.compareTo(value) })
    }

    fun <T : Comparable<T>> insert(value: T, root: BinaryTreeNode<T>) {
        val duplicateOrParent = find(value, root)
        if (duplicateOrParent.value == value) return
        duplicateOrParent.let {
            // We know the parent is a leaf, so we don't need to
            // consider its left/right children.
            (if (it.value > value) it::right
            else it::left).set(it)
        }
    }

    fun <T : Comparable<T>> delete(value: T, root: BinaryTreeNode<T>):
            BinaryTreeNode<T>? {
        val node =  find(value, root)
        if (node.value != value) return root
        // If there's a left subtree, find within it the node
        // with the max value.
        // Otherwise, if there's a right subtree, find within
        // it the node with the min value.
        val replacementNode = node.left?.let {
            find(it, { 1 })
        } ?: node.right?.let {
            find (it, { -1 })
        }
        return replacementNode?.let {
            // Detach the replacement node from its parent.
            it.parent?.let { parent ->
                (if (it.isRightChild)
                    parent::right else parent::left).set(null)
            }
            // Detach the node to delete from its parent.
            if (node.isRightChild) {
                node.parent?.right = replacementNode
            } else if (node.isLeftChild){
                node.parent?.left = replacementNode
            }
            // Attach the replacement node to its new parent.
            // Attach the deleted nodes subtrees to the replacement node.
            with (it) {
                right = node.right
                left = node.left
                parent = node.parent
            }
            // Return the root.
            // If replacement node was null, the node to delete is the
            // root, and we return null.
            root
        }
    }

}