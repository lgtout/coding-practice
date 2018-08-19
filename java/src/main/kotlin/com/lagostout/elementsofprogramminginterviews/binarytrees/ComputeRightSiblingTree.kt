package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode

/* Problem 10.16.1 page 172 */

fun <T> computeRightSiblingTree(root: BinaryTreeNode<T>) {
    var node = root
    var levelNextPreviousNode: BinaryTreeNode<T>? = null
    var leftmostNodeOnLevel = node.left
    while (true) {
        node.left ?: break
        levelNextPreviousNode?.let {
            it.levelNext = node.left
        }
        node.left!!.levelNext = node.right
        levelNextPreviousNode = node.right
        node = node.levelNext ?: leftmostNodeOnLevel?.also {
            levelNextPreviousNode = null
            leftmostNodeOnLevel = it.left
        } ?: break
    }
}