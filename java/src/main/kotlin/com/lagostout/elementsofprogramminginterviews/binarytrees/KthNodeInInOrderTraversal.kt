package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode

/**
 * Problem 10.9 page 163
 */
fun kthNodeInInOrderTraversal(k: Int, root: BinaryTreeNode<Int>):
        BinaryTreeNode<Int>? {
    fun position(node: BinaryTreeNode<Int>,
                 parentNodePosition: Int = 0): Int {
        return (node.value + 1) / 2 + parentNodePosition
    }
    var currentNode: BinaryTreeNode<Int>? = root
    var currentNodePosition = position(root)
    while (currentNode != null) {
        if (k < currentNodePosition) {
            if (currentNode.left != null)
                currentNodePosition = position(
                        currentNode.left, currentNodePosition)
            currentNode = currentNode.left
        } else if (k > currentNodePosition) {
            if (currentNode.right != null)
                currentNodePosition = position(
                        currentNode.right, currentNodePosition)
            currentNode = currentNode.right
        } else {
            break
        }
    }
    return currentNode
}