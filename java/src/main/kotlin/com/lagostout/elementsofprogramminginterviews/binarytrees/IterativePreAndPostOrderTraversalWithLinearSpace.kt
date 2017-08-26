package com.lagostout.elementsofprogramminginterviews.binarytrees

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
        var previousNode: BinaryTreeNode<T>? = null
        var finished = false
//        var operation = LEFT
//        var previousOperation: Operation? = operation
        while (node != null) {
            node.apply {
                if (previousNode !in listOf(left, right)) {
                    order.onPreOrder { result.add(value) }
                }
                when (previousNode) {
                    parent -> node = left ?: right ?: run {
                        order.onPostOrder { result.add(value) }
                        parent
                    }
                    left -> node = right ?: run {
                        order.onPostOrder { result.add(value) }
                        parent
                    }
                    right -> node = parent ?: run {
                        order.onPostOrder { result.add(value) }
                        parent
                    }
                }
//                if (node == parent && right == null) {
//                    order.onPostOrder { result.add(value) }
//                }
                previousNode = this
            }
        }
        return result
    }

    fun <T : Comparable<T>> preOrderTraversalPath(root: BinaryTreeNode<T>): List<T>
            = traversalPath(root, Order.PRE)

    fun <T : Comparable<T>> postOrderTraversalPath(root: BinaryTreeNode<T>): List<T>
            = traversalPath(root, Order.POST)

}
