package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode

/* Problem 15.11 page 280 */

fun <T : Comparable<T>> bstNodesAreTotallyOrdered(
        firstAncestorOrDescendant: BinaryTreeNode<T>,
        secondAncestorOrDescendant: BinaryTreeNode<T>,
        middle: BinaryTreeNode<T>): Boolean {

    val ancestorOrDescendants = listOf(
        firstAncestorOrDescendant, secondAncestorOrDescendant)
    val allNodes = setOf(middle) + ancestorOrDescendants
    if (allNodes.size < 3) return false

    fun isDescendant(
            root: BinaryTreeNode<T>, possibleDescendant: BinaryTreeNode<T>): Boolean {
        var result = false
        var node = root
        while (true) {
            node = (when {
                possibleDescendant.value < node.value -> node.left
                possibleDescendant.value > node.value -> node.right
                else -> {
                    result = true
                    null
                }
            }) ?: break
        }
        return result
    }

    return (isDescendant(middle, firstAncestorOrDescendant) &&
        isDescendant(secondAncestorOrDescendant, middle)) ||
            (isDescendant(middle, secondAncestorOrDescendant) &&
                    isDescendant(firstAncestorOrDescendant, middle))
}