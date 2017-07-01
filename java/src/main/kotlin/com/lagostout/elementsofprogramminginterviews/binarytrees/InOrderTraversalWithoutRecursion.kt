package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import java.util.*

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
                val left = frame.node.left
                left.let {
                    stack.push(Frame(node = frame.node.left))
                }
                frame.nextStep()
            }
            1 -> {
                frame.nextStep()
            }
            2 -> {
                val right = frame.node.right
                right.let {
                    stack.push(Frame(node = frame.node.right))
                }
                frame.nextStep()
                stack.pop()
            }
        }
    }
    return path
}
