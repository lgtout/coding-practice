package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode

// TODO Does it make sense that root is nullable?
fun <T : Comparable<T>> findFirstInorderOccurrence(
        root: BinaryTreeNode<T>?, k: T): BinaryTreeNode<T>? {
    var firstOccurrence: BinaryTreeNode<T>? = null
    var currentNode: BinaryTreeNode<T>? = root
    while (currentNode != null) {
        currentNode = currentNode.run {
            when {
                value >= k -> {
                    if (value == k)
                        firstOccurrence = currentNode
                    left
                }
                else -> right
            }
        }
    }
    return firstOccurrence
}