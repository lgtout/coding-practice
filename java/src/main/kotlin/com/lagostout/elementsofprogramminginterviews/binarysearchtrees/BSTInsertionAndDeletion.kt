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
            val node = BinaryTreeNode(value = value, parent = it)
            (if (value > it.value) it::right else it::left).set(node)

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
        val nodeToDelete = find(value, root)

        // Node to delete isn't in the tree.
        if (nodeToDelete.value != value) return root

        // Find the replacement node.
        val replacementNode = nodeToDelete.run {
            left?.let { it ->
                find(it) { 1 }
            } ?: right?.let { it ->
                find(it) { -1 }
            }
        }

        replacementNode?.apply {

            // Extract the replacement node, promoting its single child,
            // if it has one. The replacement node will always have <= 1
            // child nodes, because it will be the leftmost or rightmost
            // descendant (of the left or right subtree of the node to
            // delete).
            // If the replacement node is not null, it will have a parent.
            (left ?: right).let {
                it?.parent = parent
                (if (isLeftChild) parent!!::left
                else parent!!::right).set(it)
            }
            parent = null; left = null; right = null

        }

        // Swap the node to delete and the replacement node.

        when {
            nodeToDelete.isLeftChild -> nodeToDelete.parent?.left = replacementNode
            nodeToDelete.isRightChild -> nodeToDelete.parent?.right = replacementNode
        }
        replacementNode?.left = nodeToDelete.left
        replacementNode?.right = nodeToDelete.right
        nodeToDelete.left?.parent = replacementNode
        nodeToDelete.right?.parent = replacementNode

        // Clean up.
        nodeToDelete.parent = null

        // If the node to delete is the root then:
        // - If there's no replacement node (i.e. the root is the only node
        // in the tree) we return the replacement node, which is null.
        // - If there's a replacement node, we return it because it's the new root.
        // If the node to delete is not the root, we return the root.
        return if (nodeToDelete == root) replacementNode else root
    }

    /* We compare the node we're searching for with each node in the tree.  If
    the former's value is > the latter's, we go right.  If the value is <, we
    go left.  Otherwise, we've found the node we're searching for, or we've
    gone as far as we can and found a node that would be its parent if it was
    present. */
    private fun <T : Comparable<T>> find(
            root: BinaryTreeNode<T>, compareTo: (T) -> Int): BinaryTreeNode<T> {
        var currentNode = root
        while (true) {
            val comparison = compareTo(currentNode.value)
            when {
                comparison > 0 -> {
                    currentNode.right?.let {
                        currentNode = it
                    }
                }
                comparison < 0 -> {
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