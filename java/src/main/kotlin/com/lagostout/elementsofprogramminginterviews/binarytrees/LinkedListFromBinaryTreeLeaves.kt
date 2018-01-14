package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import java.util.*

/**
 * Problem 10.14 page 169
 */
fun <T> createLinkedListFromBinaryTreeLeaves(
        root: BinaryTreeNode<T>): BinaryTreeNode<T> {
    data class Frame(val node: BinaryTreeNode<T>, val right: Boolean = false)
    var head: BinaryTreeNode<T>? = null
    var tail: BinaryTreeNode<T>? = null
    val stack = LinkedList<Frame>().apply {
            add(Frame(root))
    }
    // Don't put frame back on the stack if we're about to
    // explore its right branch.
    while (stack.isNotEmpty()) {
        val frame = stack.pop()
        with (frame) {
            when {
                node.isALeaf -> {
                    // Avoid creating a cycle when there's
                    // only one leaf i.e. the head is the tail.
                    tail?.let {
                        it.right = node
                    } ?: run { head = node }
                    tail = node
                }
                right -> {
                    node.right?.let {
                        stack.push(Frame(it))
                    }
                }
                // Go left
                else -> {
                    stack.push(frame.copy(right = true))
                    node.left?.let {
                        stack.push(Frame(it))
                    }
                }
            }
        }
    }
    return head!!
}