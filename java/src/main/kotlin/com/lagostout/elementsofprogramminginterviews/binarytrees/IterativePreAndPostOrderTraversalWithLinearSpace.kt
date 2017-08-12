package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode

object IterativePreAndPostOrderTraversalWithLinearSpace {

    enum class Order {
        PRE, POST
    }

    fun <T : Comparable<T>> traversal(root: BinaryTreeNode<T>, order: Order): List<T> {
        val result = mutableListOf<T>()
        var node: BinaryTreeNode<T>? = root
        var previousNode: BinaryTreeNode<T>? = null
        while (node != null) {
            when (previousNode) {
                node.parent -> {
                    if (order == Order.PRE)
                        result.add(node.value)
                    previousNode = node
                    node = node.left?: node.parent
                }
                node.left -> {
                    node = node.right?: node.parent
                    previousNode = node
                }
                node.right -> {
                    if (order == Order.POST)
                        result.add(node.value)
                    previousNode = node
                    node = node.parent
                }
            }
        }
        return result
    }

    fun <T : Comparable<T>> preOrderTraversal(root: BinaryTreeNode<T>): List<T>
            = traversal(root, Order.PRE)

    fun <T : Comparable<T>> postOrderTraversal(root: BinaryTreeNode<T>): List<T>
            = traversal(root, Order.POST)

}
