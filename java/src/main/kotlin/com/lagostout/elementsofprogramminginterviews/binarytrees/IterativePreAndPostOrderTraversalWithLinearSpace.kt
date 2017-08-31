package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.isLeftChild
import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.elementsofprogramminginterviews.binarytrees.IterativePreAndPostOrderTraversalWithLinearSpace.Operation.*

/**
 * Problem 10.11.2 page 166
 */
object IterativePreAndPostOrderTraversalWithLinearSpace {

    enum class Order {
        PRE {
            override fun onPreOrder(closure: () -> Unit) {
                closure()
            }
        }, POST {
            override fun onPostOrder(closure: () -> Unit) {
                closure()
            }
        };
        open fun onPreOrder(closure : () -> Unit) {}
        open fun onPostOrder(closure : () -> Unit) {}
    }

    enum class Operation {
        LEFT, RIGHT, PARENT
    }

    private fun <T : Comparable<T>> traversalPath(root: BinaryTreeNode<T>, order: Order): List<T> {
        val result = mutableListOf<T>()
        var node: BinaryTreeNode<T>? = root
        var operation = LEFT
        while (node != null) {
            when (operation) {
                LEFT -> {
                    node.value.let {
                        order.onPreOrder { result.add(it) }
                    }
                    if (node.left == null) {
                        operation = RIGHT
                    } else {
                        node = node.left
                    }
                }
                RIGHT -> {
                    if (node.right == null) {
                        operation = PARENT
                    } else {
                        node = node.right
                        operation = LEFT
                    }
                }
                PARENT -> {
                    node.value.let {
                        order.onPostOrder { result.add(it) }
                    }
                    if (node.isLeftChild) {
                        operation = RIGHT
                    }
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
