package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import java.util.*

fun sumOfRootToLeafPaths(root: BinaryTreeNode<Boolean>): Int {

    data class Frame(var step: Int = 0, val node: BinaryTreeNode<Boolean>)

    val stack = ArrayDeque<Frame>()

    return ({
        stack.push(Frame(node = root))
        var sum = 0
        var number = 0
        while (stack.isNotEmpty()) {
            val frame = stack.peek()
            if (frame.step in listOf(0,1)) {
                number = when (frame.node.value) {
                    true -> number or 1
                    else -> number }
            }
            fun step0Or1(node: BinaryTreeNode<Boolean>?) {
                node?.let {
                    stack.push(Frame(node = node))
                    number = number shl 1
                }
            }
            when (frame.step) {
                0 -> step0Or1(frame.node.left)
                1 -> step0Or1(frame.node.right)
                2 -> {
                    if (frame.node.left == null && frame.node.right == null) {
                        sum += number
                    }
                }
                else -> {
                    number = number ushr 1
                    stack.pop()
                }
            }
            if (frame.step in listOf(0,1,2)) {
                frame.step++
            }
        }
        sum
    }())

}