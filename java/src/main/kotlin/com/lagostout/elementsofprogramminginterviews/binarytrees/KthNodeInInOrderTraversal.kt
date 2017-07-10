package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode

/**
 * Problem 10.9 page 163
 */
fun kthNodeInInOrderTraversal(
        k: Int, root: BinaryTreeNode<Int>?):
        BinaryTreeNode<Int>? {
    var currentNode = root
    var relativeK = k + 1 // So we can compare positions and counts
    var currentNodePosition: Int
    while (currentNode != null) {
        currentNodePosition = (currentNode.left?.value ?: 0) + 1
        if (relativeK == currentNodePosition) {
            break
        } else if (relativeK < currentNodePosition) {
            currentNode = currentNode.left
        } else if (relativeK > currentNodePosition) {
            relativeK -= currentNodePosition
            currentNode = currentNode.right
        }
    }
    return currentNode
}