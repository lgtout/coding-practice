package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.takeFrom
import com.lagostout.datastructures.BinaryTreeNode

/**
 * Problem 10.12.3 page 168
 */
fun buildMaxTree(values: List<Int>): BinaryTreeNode<Int>? {
    if (values.isEmpty()) return null
    var treeNode = BinaryTreeNode(value = values[0])
    values.withIndex().takeFrom(1).forEach { (index, value) ->
        val node = BinaryTreeNode(value = value)
        while (true) {
            if (treeNode.value >= value) {
                node.left = treeNode.right
                treeNode.right = node
                treeNode = node
            } else {
                treeNode.parent.let {
                    if (it != null && it.value < value) {
                        treeNode = it
                    } else {
                        node.left = treeNode
                        it?.let {
                            it.right = node
                        }
                        treeNode = node
                    }
                }
            }
        }
    }
    return treeNode
}
