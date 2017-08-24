package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.elementsofprogramminginterviews.binarytrees.IterativePreAndPostOrderTraversalWithLinearSpace.Operation.*
/**
 * Problem 10.11.2 page 166
 */
object IterativePreAndPostOrderTraversalWithLinearSpace {

    enum class Order {
        PRE {
            override fun onBeforeLeft(closure: () -> Unit) {
                closure()
            }
        }, POST {
            override fun onAfterRight(closure: () -> Unit) {
                closure()
            }
        };
        open fun onBeforeLeft(closure : () -> Unit) {}
        open fun onAfterRight(closure : () -> Unit) {}
    }

    enum class Operation {
        LEFT, RIGHT, PARENT
    }

    private fun <T : Comparable<T>> traversalPath(root: BinaryTreeNode<T>, order: Order): List<T> {
        val result = mutableListOf<T>()
        var node: BinaryTreeNode<T> = root
        var previousNode: BinaryTreeNode<T>? = null
        var finished = false
        var operation = LEFT
        var previousOperation: Operation? = operation
        while (!finished) {
//            println(node.value)
            when (operation) {
                LEFT -> {
                    order.onBeforeLeft { result.add(node.value) }
                    previousOperation = operation
                    node.left?.apply {
                        operation = LEFT
                        node = this
                    } ?: run {
                        operation = PARENT
                    }
                }
                PARENT -> {
                    node.parent?.apply {
                        node = this
                        operation = when (previousOperation) {
                            LEFT -> {
                                RIGHT
                            }
                            RIGHT, PARENT -> {
                                order.onAfterRight { result.add(value) }
                                PARENT
                            }
                            else -> { throw IllegalStateException() }
                        }
                    } ?: run {
                        finished = true
                    }
                }
            }
//            node?.apply {
//                when (previousNode) {
//                    node -> {
//                        parent?.let {
//                            node = it
//                        } ?: run { finished = true }
//                    }
//                    parent -> {
//                        order.onBeforeLeft { result.add(value) }
//                        previousNode = this
//                        node = left ?: right ?: this.apply {
//                            order.onAfterRight { result.add(value) }
//                        }
//                    }
//                    left -> {
//                        previousNode = this
//                        node = right ?: run {
//                            order.onAfterRight { result.add(value) }
//                            parent
//                        }
//                    }
//                    right -> {
//                        order.onAfterRight { result.add(value) }
//                        previousNode = this
//                        node = parent
//                    }
//                }
//            }
        }
        return result
    }

    fun <T : Comparable<T>> preOrderTraversalPath(root: BinaryTreeNode<T>): List<T>
            = traversalPath(root, Order.PRE)

    fun <T : Comparable<T>> postOrderTraversalPath(root: BinaryTreeNode<T>): List<T>
            = traversalPath(root, Order.POST)

}
