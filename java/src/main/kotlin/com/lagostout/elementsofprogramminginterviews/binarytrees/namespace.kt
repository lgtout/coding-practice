package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import java.util.*

fun <T> preorderTraversal(root: BinaryTreeNode<T>): List<Pair<Int, T>> {
    val traversal = mutableListOf<Pair<Int, T>>()
    val stack = LinkedList<BinaryTreeNode<T>>().apply {
        push(root)
    }
    while (stack.isNotEmpty()) {
        stack.pop()?.let {
            traversal.add(Pair(it.id, it.value))
            listOf(it.left, it.right).filterNotNull().forEach {
                stack.push(it)
            }
        }
    }
    return traversal
}

fun <T> postorderTraversal(root: BinaryTreeNode<T>): List<Pair<Int, T>> {
    data class Frame(val node: BinaryTreeNode<T>, var action:Int = 0)
    val traversal = mutableListOf<Pair<Int, T>>()
    val stack = LinkedList<Frame>().apply {
        push(Frame(root))
    }
    while (true) {
        stack.pollFirst()?.let { (node, action) ->
            when (action) {
                0 -> {
                    stack.push(Frame(node, action + 1))
                    with (node) {
                        listOf(left, right).filterNotNull().forEach {
                            stack.push(Frame(it))
                        }
                    }
                }
                else -> node.apply { traversal.add(Pair(id, value)) }
            }
        } ?: break
    }
    return traversal
}

fun <T> inorderTraversal(root: BinaryTreeNode<T>): List<Pair<Int, T>> {
    data class Frame(val node: BinaryTreeNode<T>, var action:Int = 0)
    val traversal = mutableListOf<Pair<Int, T>>()
    val stack = LinkedList<Frame>().apply {
        push(Frame(root))
    }
    while (true) {
        stack.pollFirst()?.let { (node, action) ->
            when (action) {
                0 -> {
                    stack.push(Frame(node, action + 1))
                    with (node) {
                        left?.let {
                            stack.push(Frame(it))
                        }
                    }
                }
                else -> {
                    node.apply { traversal.add(Pair(id, value)) }
                    with (node) {
                        right?.let { stack.push(Frame(it)) }
                    }
                }
            }
        } ?: break
    }
    return traversal
}
