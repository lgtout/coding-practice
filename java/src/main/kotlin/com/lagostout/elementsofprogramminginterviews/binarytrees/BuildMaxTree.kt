package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.takeFrom
import com.lagostout.datastructures.BinaryTreeNode

/**
 * Problem 10.12.3 page 168
 */
fun buildMaxTree(values: List<Int>): BinaryTreeNode<Int>? {
    if (values.isEmpty()) return null
    var treeNode = BinaryTreeNode(value = values[0])
    values.takeFrom(1).forEach { value ->
        val node = BinaryTreeNode(value = value)
        var done = false
        while (!done) {
            if (treeNode.value > value) {
                // treeNode's right subtree contains all the nodes
                // to the left of node, between node and treeNode.
                // Because, as we move up the tree, we're moving
                // left in the values array.
                node.left = treeNode.right
                treeNode.right = node
                node.parent = treeNode
                treeNode = node
                done = true
            } else {
                treeNode.parent.let {
                    if (it != null && it.value <= value) {
                        // We're not at the tree root and we haven't
                        // reached a node in the tree that's greater
                        // than the one we want to fit into the tree.
                        // So let's keep climbing up it.
                        treeNode = it
                    } else {
                        // We're at the tree root or we've reached
                        // a node that's greater than the one we
                        // want to fit into the tree.  If we're at
                        // the root, then the node we want to fit
                        // is greater than the root node.  It becomes
                        // the new root.  If we're not at the root,
                        // we need to displace the tree node.  In
                        // this case we attach the node to fit as
                        // the right child of the tree node parent,
                        // since the node to fit is to the parent's
                        // right.
                        node.left = treeNode
                        it?.let {
                            it.right = node
                            node.parent = it
                        }
                        treeNode = node
                        done = true
                    }
                }
            }
        }
    }
    // Climb up to the root
    while (true) {
        treeNode.parent?.let {
            treeNode = it
        } ?: break
    }
    return treeNode
}
