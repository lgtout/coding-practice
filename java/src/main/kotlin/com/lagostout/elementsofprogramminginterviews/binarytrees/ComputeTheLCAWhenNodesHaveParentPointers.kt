package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode

/**
 * Problem 10.4 p156
 */
fun <T : Comparable<T>> computeTheLCAWhenNodesHaveParentPointers(
        firstNode: BinaryTreeNode<T>, secondNode: BinaryTreeNode<T>):
        BinaryTreeNode<T>? {
    val firstNodeAncestors = mutableSetOf<BinaryTreeNode<T>>()
    val secondNodeAncestors = mutableSetOf<BinaryTreeNode<T>>()
    var firstNodeAncestor:BinaryTreeNode<T>? = firstNode
    var secondNodeAncestor:BinaryTreeNode<T>? = secondNode
    var lca:BinaryTreeNode<T>? = null
    while (firstNodeAncestor != null || secondNodeAncestor != null) {
        when  {
            firstNodeAncestor != null -> {
                firstNodeAncestors.add(firstNodeAncestor)
                firstNodeAncestor = firstNodeAncestor.parent
            }
            secondNodeAncestor != null -> {
                secondNodeAncestors.add(secondNodeAncestor)
                secondNodeAncestor = secondNodeAncestor.parent
            }
        }
        val secondNodeAncestorsContainsFirstNode =
                secondNodeAncestors.contains(firstNodeAncestor)
        if (secondNodeAncestorsContainsFirstNode ||
                firstNodeAncestors.contains(secondNodeAncestor)) {
            lca = if (secondNodeAncestorsContainsFirstNode)
                firstNodeAncestor else secondNodeAncestor
            break
        }
    }
    return lca
}

