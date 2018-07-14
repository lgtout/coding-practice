package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode

/* Problem 10.16.1 page 172 */

object ComputeRightSiblingTree {

    fun <T> computeRightSiblingTree(root: BinaryTreeNode<T>) {
        var leftmostNode = root
        outer@ while  (true) {
            var currentNode: BinaryTreeNode<T>? = leftmostNode
            var previousNodeRight: BinaryTreeNode<T>? = null
            while (true) {
                if (currentNode == null) break
                if (currentNode.left == null) break@outer
                previousNodeRight?.let {
                    it.levelNext = currentNode!!.left
                }
                currentNode.left!!.levelNext = currentNode.right
                previousNodeRight = currentNode.right!!
                currentNode = currentNode.levelNext
            }
            leftmostNode = leftmostNode.left!!
        }
    }

}