package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode

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
        var node: BinaryTreeNode<T>? = root
        var previousNode: BinaryTreeNode<T>? = null
        var finished = false
        var operation = Operation.LEFT
        var previousOperation: Operation? = operation
        while (!finished) {
//            println(node.value)
//            when (operation) {
//                Operation.LEFT -> {
//                    node?.apply {
//                        order.onBeforeLeft { result.add(value) }
//                    }
//                    previousOperation = operation
//                    node = node?.left
//                    node ?: run {
//                        operation = Operation.PARENT
//                    }
//                }
//                Operation.PARENT -> {
//                    node = node?.parent
//                    if (previousOperation == Operation.RIGHT)
//                }
//            }
            node?.apply {
                when (previousNode) {
                    node -> {
                        finished = true
                    }
                    parent -> {
                        order.onBeforeLeft { result.add(value) }
                        previousNode = this
                        node = left ?: right ?: this
                    }
                    left -> {
                        previousNode = this
                        node = right ?: parent
                    }
                    right -> {
                        order.onAfterRight { result.add(value) }
                        previousNode = this
                        node = parent
                    }
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
