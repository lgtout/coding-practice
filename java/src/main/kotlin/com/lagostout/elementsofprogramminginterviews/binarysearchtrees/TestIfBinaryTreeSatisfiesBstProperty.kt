package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.BinaryTreeNode
import java.util.*

/**
 * Problem 15.1 page 260
 */
fun binaryTreeSatisfiesBstProperty(root: BinaryTreeNode<Int>): Boolean {
    data class Range(var minimum: Int? = null, var maximum: Int? = null) {
        fun contains(value: Int): Boolean {
            return minimum?.let { value > it } ?: true &&
                    maximum?.let { value < it } ?: true
        }
    }
    data class Frame(var step: Int = 0, val node: BinaryTreeNode<Int>, val range: Range)
    var bstPropertyIsSatisfied = true
    val frames = LinkedList<Frame>()
    frames.push(Frame(node = root, range = Range()))
    while (frames.isNotEmpty() && bstPropertyIsSatisfied) {
        val frame = frames.peek()
        val (_, node, range)  = frame
        when (frame.step) {
            0 -> {
                if (!frame.range.contains(node.value)) {
                    bstPropertyIsSatisfied = false
                } else {
                    node.left?.let {
                        frames.push(Frame(node = node.left,
                                range = range.copy(maximum = node.value)))
                    }
                }
            }
            1 -> {
                if (!frame.range.contains(node.value)) {
                    bstPropertyIsSatisfied = false
                } else {
                    node.right?.let {
                        frames.push(Frame(node = node.right,
                                range = range.copy(minimum = node.value)))
                    }
                }
            }
            2 -> {
                frames.pop()
            }
        }
        frame.step++
    }
    return bstPropertyIsSatisfied
}