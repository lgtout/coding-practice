package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.BinaryTreeNode
import java.util.*

fun findFirstKeyGreaterThanValueInBst(
        root: BinaryTreeNode<Int>, value: Int): Int? {
    data class Frame(val node: BinaryTreeNode<Int>,
                     var step: Int = 0,
                     var previousKey: Int? = null)
    val stack = LinkedList<Frame>()
    var firstKeyGreaterThanValue = null
    stack.add(Frame(root))
    while (stack.isNotEmpty()) {
        val frame = stack.peek()
        val (node, step, previousKey) = frame
        when (step) {
            0 -> {
                node.left.let {
                    stack.push(Frame(node.left))
                }
            }
            1 -> {
                val currentKey = node.value
                if (previousKey == null) {
                    if (currentKey < value) {

                    }
                }
            }
        }
        frame.step++
    }
    return firstKeyGreaterThanValue
}