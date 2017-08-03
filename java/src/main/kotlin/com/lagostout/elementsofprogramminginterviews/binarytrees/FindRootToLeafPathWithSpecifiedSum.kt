package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import java.util.*

/**
 * Problem 10.6 page 160
 */
fun treeContainsRootToLeafPath(root: BinaryTreeNode<Int>, sum:Int): Boolean {
    data class Frame(var step: Int = 0, val node: BinaryTreeNode<Int>?) {
        val value = if (node == null) 0 else node.value
        operator fun component3() = value
    }
    val stack = LinkedList<Frame>()
    var pathWithSumFound = false
    stack.push(Frame(node = root))
    var runningSum = 0
    loop@ while (stack.isNotEmpty()) {
        val frame = stack.peek()
        val (step, node, value) = frame
        when (step) {
            0 -> {
                runningSum += value
                if (runningSum == sum) {
                    pathWithSumFound = true
                    break@loop
                }
                frame.step += 1
                node?.left?.let {
                    stack.push(Frame(node = node.left))
                }
            }
            1 -> {
                frame.step += 1
                node?.right?.let {
                    stack.push(Frame(node = node.right))
                }
            }
            2 -> {
                stack.pop()
                runningSum -= value
            }
        }
    }
    return pathWithSumFound
}