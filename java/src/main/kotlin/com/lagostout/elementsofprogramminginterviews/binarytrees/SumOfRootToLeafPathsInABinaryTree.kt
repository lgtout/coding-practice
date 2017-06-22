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
            number = number shl 1
            when (frame.step) {
                0,1 -> {
                    number = when (frame.node.value) {
                        true -> number and 1
                        else -> number }
                    val nextNode = when (frame.step) {
                        0 -> frame.node.left
                        else -> frame.node.right }
                    frame.step++
                    stack.push(Frame(node = nextNode))
                }
                else -> {
                    sum += number
                    number ushr 1
                    stack.pop()
                }
            }
        }
        sum
    }())

}