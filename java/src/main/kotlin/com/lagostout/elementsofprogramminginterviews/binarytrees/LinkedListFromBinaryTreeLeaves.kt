package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import java.util.*

/**
 * Problem 10.14 page 169
 */
fun <T> createLinkedListFromBinaryTreeLeaves(
        root: BinaryTreeNode<T>): BinaryTreeNode<T> {
    data class Frame(val node: BinaryTreeNode<T>, val right: Boolean = false)
    val head: BinaryTreeNode<T> = root
    var tail: BinaryTreeNode<T> = head
    val stack = LinkedList<Frame>().apply {
            add(Frame(root))
    }
    // Don't put frame back on the stack if we're about to
    // explore its right branch.
    while (stack.isNotEmpty()) {
        val frame = stack.pop()
        with (frame) {
            when {
                node.isLeaf -> {
                    tail.right = node
                    tail = node
                }
                right -> {
                    node.right?.let {
                        stack.push(Frame(it))
                    }
                }
                else -> {
                    stack.push(frame.copy(right = true))
                    node.left?.let {
                        stack.push(Frame(it))
                    }
                }
            }
        }
    }
    return head.right ?: head
}