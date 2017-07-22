package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.BinaryTreeNode
import java.util.*

fun <T : Comparable<T>> findKLargestElementsInBST(
        root: BinaryTreeNode<T>?, k: Int): List<T> {
    val kLargestElements  = mutableListOf<T>()
    root?.let {
        data class Frame(val node: BinaryTreeNode<T>, var step: Int = 0)
        val stack = LinkedList<Frame>().apply { add(Frame(it)) }
        while (kLargestElements.size < k && stack.isNotEmpty()) {
            val frame = stack.peek()
            val (currentNode, step) = frame
            when (step) {
                0 -> {
                    currentNode.right?.apply {
                        stack.push(Frame(this))
                    }
                }
                1 -> {
                    kLargestElements.add(currentNode.value)
                }
                2 -> {
                    currentNode.left?.apply {
                        stack.push(Frame(this))
                    }
                }
                else -> {
                    stack.pop()
                }
            }
            frame.step++
        }
    }
    return kLargestElements
}