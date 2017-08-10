package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode

fun <T : Comparable<T>> preOrderTraversal(root: BinaryTreeNode<T>): List<T> {
    var result = mutableListOf<T>()
    var node: BinaryTreeNode<T>? = root
    var previousNode: BinaryTreeNode<T>? = null
    while (node != null) {
        if (previousNode == node.parent) {
            result.add(node.value)
            node = node.left?: node.parent
        } else
        previousNode = node
    }
    return result
}

fun <T> postOrderTraversal(root: BinaryTreeNode<T>): List<T> {
    var result = emptyList<T>()
    return result
}
