package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.isLeftChild
import com.lagostout.common.isRightChild
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

    fun <T : Comparable<T>> delete(value: T, root: BinaryTreeNode<T>):
            BinaryTreeNode<T>? {

        @Suppress("NAME_SHADOWING")
        var root = root
        val node =  find(value, root)
        if (node.value != value) return root

        // If there's a left subtree, find within it the node
        // with the max value.  Otherwise, use the right node
        // as the replacement.

        val replacementNode = node.left?.let {
            find(it) { -1 }
        } ?: node.right

        // If replacement node is null, the node to delete is the
        // root, so we return null.

        return replacementNode?.let {

            // Detach the node to delete from its parent,
            // swapping it with the replacement.  If the
            // node to delete is the root, this operation
            // has no effect.

            if (node.isRightChild) {
                node.parent?.right = replacementNode
            } else if (node.isLeftChild){
                node.parent?.left = replacementNode
            }

            // Attach the deleted node's subtrees to the replacement node.
            // Attach the replacement node to its new parent.

            with (it) {

                // Prevent a cycle from being created on replacement node
                // when the replacement node is the child of node to
                // delete.

                if (node.right != this) right = node.right
                if (node.left != this) left = node.left
                parent = node.parent.apply {

                    // If we're deleting the root, let's make the
                    // replacement node the new root.

                    if (node == root) root = it
                }
            }

            // Detach the replacement node from its parent.

            it.parent?.let { parent ->
                (if (it.isRightChild)
                    parent::right else parent::left).set(null)
            }

            // Return the root.

            root
        }
    }

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
        return find(root) { it.compareTo(value) }
    }

}