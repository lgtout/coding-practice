package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode

fun <T : Comparable<T>>computeTheSuccessor(
        predecessor: T, root: BinaryTreeNode<T>): Int? {
    var successor = null
    var currentNode = root
    while (true) {
        if (currentNode.value < predecessor) {
            if (currentNode.right != null) {
                currentNode = currentNode.right
            } else break
        } else if (currentNode.value > predecessor) {
            if (currentNode.left != null) {
                currentNode = currentNode.left
            } else break
        } else break
    }
    if (currentNode.value <= predecessor) {
        // Find right child left descendant.
        // If none, find right ancestor.
        // If none, no successor.
    } else {
        // Current node is successor.
    }
    return successor
}