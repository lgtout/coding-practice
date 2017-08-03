package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import java.util.*

/**
 * Problem 10.7 page 161
 */
fun pathTakenByInOrderTraversalWithoutRecursion(
        root: BinaryTreeNode<Int>?): List<BinaryTreeNode<Int>>? {
    val path = mutableListOf<BinaryTreeNode<Int>>()
    if (root == null) return path
    data class Frame(var step: Int = 0, val node: BinaryTreeNode<Int>) {
        fun nextStep() {
            step++
        }
    }
    val stack = LinkedList<Frame>()
    stack.push(Frame(node = root))
    while (stack.isNotEmpty()) {
        val frame = stack.peek()
        when (frame.step) {
            0 -> {
                frame.node.left?.let {
                    stack.push(Frame(node = it))
                }
                frame.nextStep()
            }
            1 -> {
                path.add(frame.node)
                frame.nextStep()
            }
            2 -> {
                stack.pop()
                frame.node.right?.let {
                    stack.push(Frame(node = it))
                }
            }
        }
    }
    return path
}
