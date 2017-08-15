package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode

/**
 * Problem 15.2.2 page 264
 */
fun <T : Comparable<T>> findFirstInorderOccurrence(
        root: BinaryTreeNode<T>, k: T): BinaryTreeNode<T>? {
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