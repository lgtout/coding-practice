package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode

/**
 * Problem 10.10 page 164
 */
fun <T : Comparable<T>>computeTheSuccessor(
        predecessor: T, root: BinaryTreeNode<T>?): T? {
    return root?.let {
        //    if (root == null) return null
        val successor: T?
        var rightAncestor: BinaryTreeNode<T>? = null
        var currentNode = it
        // Find the predecessor and, along the way, track the
        // most recent right ancestor.
        // We will end up with a node whose value is ==, <,
        // or > the predecessor.
        while (true) {
            if (currentNode.value < predecessor) {
                if (currentNode.right == null) break
                currentNode = currentNode.right
            } else if (currentNode.value > predecessor) {
                if (currentNode.left == null) break
                rightAncestor = currentNode
                currentNode = currentNode.left
            } else break
        }
        successor = if (currentNode.value < predecessor ||
                (currentNode.value == predecessor &&
                        currentNode.right == null)) {
            rightAncestor?.value
        } else {
            if (currentNode.value == predecessor) {
                currentNode = currentNode.right
                while (currentNode.left != null) {
                    currentNode = currentNode.left
                }
            }
            currentNode.value
        }
        successor
    }
}