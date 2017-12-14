package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.isLeftChild
import com.lagostout.common.isRightChild
import com.lagostout.datastructures.BinaryTreeNode
import org.apache.commons.collections4.iterators.PeekingIterator

/**
 * Problem 10.13.2 page 169
 */
fun <T: Comparable<T>> reconstructBinaryTreeFromPostorderWithMarkers(
        traversal: List<T?>): BinaryTreeNode<T>? {
    if (traversal.filterNotNull().isEmpty()) return null
    return PeekingIterator(traversal.reversed().iterator()).let { iterator ->
        val root = BinaryTreeNode(value = iterator.next()!!)
        var treeNode = root
        var right = true
        while (iterator.hasNext()) {
            iterator.next()?.let {
                BinaryTreeNode(value = it).also {
                    if (right) treeNode.right = it
                    else {
                        treeNode.left = it
                    }
                        right = !right
                    it.parent = treeNode
                    treeNode = it
                }
            } ?: run {
                if (!right) {
                    // Find closest left ancestor
                    while (true) {
                        if (treeNode.isRightChild) {
                            treeNode.parent?.let {
                                treeNode = it
                            }
                            break
                        } else if (treeNode.isLeftChild) {
                            treeNode.parent?.let {
                                treeNode = it
                            }
                        } else break // root
                    }
                } else right = !right
            }
        }
        root
    }
}