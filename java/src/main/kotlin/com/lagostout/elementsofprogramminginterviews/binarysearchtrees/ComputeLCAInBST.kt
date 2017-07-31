package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.BinaryTreeNode

/**
 * Problem 15.4 page 265
 */
fun <T : Comparable<T>> computeLCAInBST(
        root: BinaryTreeNode<T>?,
        firstDescendant: T,
        secondDescendant: T): BinaryTreeNode<T>? {
    var lca: BinaryTreeNode<T>? = null
    var currentNode: BinaryTreeNode<T>? = root?: return null
    val descendants = listOf(firstDescendant, secondDescendant).sorted()
    val (lowerDescendant, higherDescendant) = descendants
    while (currentNode != null) {
        if (lowerDescendant > currentNode.value) {
            currentNode = currentNode.right
        } else if (higherDescendant < currentNode.value) {
            currentNode = currentNode.left
        } else if ((lowerDescendant <= currentNode.value)
                and (higherDescendant >= currentNode.value)) {
            @Suppress("NAME_SHADOWING")
            fun find(root: BinaryTreeNode<T>, value: T): BinaryTreeNode<T>? {
                var node = root
                while ((node.value != null) and
                        (node.value != node.value)) {
                    node = if (value < node.value) {
                        node.left
                    } else {
                        node.right
                    }
                }
                return node
            }
            if (find(currentNode, lowerDescendant) != null &&
                    find(currentNode, higherDescendant) != null) {
                // Not found. No LCA.
                lca = currentNode
            }
            break
        }
    }
    return lca
}