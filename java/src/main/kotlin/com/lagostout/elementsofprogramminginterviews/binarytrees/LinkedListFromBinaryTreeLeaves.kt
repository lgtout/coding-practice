package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.elementsofprogramminginterviews.linkedlists.LinkedListNode
import java.util.*

fun <T> createLinkedListFromBinaryTreeLeaves(
        root: BinaryTreeNode<T>): LinkedListNode<T> {
    data class Frame(val node: BinaryTreeNode<T>, val right: Boolean = false)
    var linkedList: LinkedListNode<T> = LinkedListNode()
    val stack = LinkedList<Frame>().apply {
        add(Frame(root))
    }
    // Don't put frame back on the stack if we're about to
    // explore its right branch.
    while (stack.isNotEmpty()) {
        val frame = stack.peek()
        if (!frame.right) {

        }
    }
    return linkedList
}