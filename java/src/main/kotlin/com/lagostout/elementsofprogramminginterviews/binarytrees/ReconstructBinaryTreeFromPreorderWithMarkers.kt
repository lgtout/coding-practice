package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.takeFrom
import com.lagostout.datastructures.BinaryTreeNode

// Assumptions:
// - The preorder is not empty.
// - The first entry in the preorder is not null.
// - The preorder has an entry (which may be null) for both children of every non-null node.
fun <T> reconstructBinaryTreeFromPreorderWithMarkers(preorder: List<T?>):
        BinaryTreeNode<T> {
    val root = BinaryTreeNode(value = preorder.first()!!)
    var node = root
    var leftChild = true
    preorder.takeFrom(1).forEach {
        if (leftChild) {
            it?.let {
                node.left = BinaryTreeNode(parent = node, value = it).also {
                    node = it
                }
            } ?: run {
                leftChild = false
            }
        } else {
            it?.let {
                node.right = BinaryTreeNode(parent = node, value = it).also {
                    node = it
                }
                leftChild = true
            } ?: run {
                while (true) {
                    node.parent?.let {
                        node = it
                    } ?: break
                    if (node.right == null) break
                }
            }
        }
    }
    return root
}