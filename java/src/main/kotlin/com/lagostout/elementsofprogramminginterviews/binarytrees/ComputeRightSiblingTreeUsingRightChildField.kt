package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode

/* Problem 10.16.2 page 173 */

fun <T> computeRightSiblingTreeUsingRightChildField(root: BinaryTreeNode<T>) {

    fun setLeftChildLevelNext(node: BinaryTreeNode<T>) {
        if (node.isALeaf) return
        setLeftChildLevelNext(node.left!!)
        node.left?.right = node.right
        if (node.hasRight)
            setLeftChildLevelNext(node.right!!)
        node.right = null
    }

    fun connectLevels(node: BinaryTreeNode<T>) {
        if (!node.hasLeft) return
        var levelNode: BinaryTreeNode<T>? = node
        while (levelNode != null) {
            levelNode.left?.right?.right = levelNode.right?.left
            levelNode = levelNode.right
        }
        connectLevels(node.left!!)
    }

    if (root.isALeaf) return
    setLeftChildLevelNext(root)
    connectLevels(root.left!!)

}