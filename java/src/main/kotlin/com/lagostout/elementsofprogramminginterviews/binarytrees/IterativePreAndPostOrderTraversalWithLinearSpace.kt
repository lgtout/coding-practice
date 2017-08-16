package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode

object IterativePreAndPostOrderTraversalWithLinearSpace {

    enum class Order {
        PRE {
            override fun onParent(closure: () -> Unit) {
                closure()
            }
        }, POST {
            override fun onRightChild(closure: () -> Unit) {
                closure()
            }
        };
        open fun onParent(closure : () -> Unit) {}
        open fun onRightChild(closure : () -> Unit) {}
    }

    private fun <T : Comparable<T>> traversalPath(root: BinaryTreeNode<T>, order: Order): List<T> {
        val result = mutableListOf<T>()
        var node: BinaryTreeNode<T>? = root
        var previousNode: BinaryTreeNode<T>? = null
        while (node != null) {
            println(node.value)
            when (previousNode) {
                node.parent -> {
                    order.onParent { result.add(node!!.value) }
                    previousNode = node
                    node = node.left?: node.parent
                }
                node.left -> {
                    previousNode = node
                    node = node.right?: node.parent
                }
                node.right -> {
                    order.onRightChild { result.add(node!!.value) }
                    previousNode = node
                    node = node.parent
                }
            }
        }
        return result
    }

    fun <T : Comparable<T>> preOrderTraversalPath(root: BinaryTreeNode<T>): List<T>
            = traversalPath(root, Order.PRE)

    fun <T : Comparable<T>> postOrderTraversalPath(root: BinaryTreeNode<T>): List<T>
            = traversalPath(root, Order.POST)

}
