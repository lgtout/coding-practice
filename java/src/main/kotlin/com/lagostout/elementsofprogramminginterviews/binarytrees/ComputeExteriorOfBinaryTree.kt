package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.elementsofprogramminginterviews.binarytrees.ComputeExteriorOfBinaryTree.Step.*
import java.util.*

/* Problem 10.15 page 170 */

object ComputeExteriorOfBinaryTree {

    private enum class Step { LEFT, RIGHT, DONE }

    private data class Frame<T>(val node: BinaryTreeNode<T>,
                                val step: Step,
                                val inLeftSubtree: Boolean,
                                val inRightSubtree: Boolean)

    fun <T> computeExteriorOfBinaryTree(root: BinaryTreeNode<T>) :
            List<BinaryTreeNode<T>>? {
        val stack = LinkedList<Frame<T>>().apply {
            add(Frame(root, LEFT, true, false))
        }
        val exterior = mutableListOf<BinaryTreeNode<T>>()
        while (stack.isNotEmpty()) {
            stack.pop().let {
                var frame = it
                frame.apply {
                    listOf(!inRightSubtree && node.isNotALeaf,
                            !inLeftSubtree && step == DONE && !node.isRoot,
                            node.isALeaf).any { it }.let {
                        exterior.add(node)
                    }
                }
                if (frame.node.isRoot && frame.step == RIGHT) {
                    frame = frame.copy(frame.node,
                            inLeftSubtree = false,
                            inRightSubtree = true)
                }
                frame.apply {
                    when (step) {
                        LEFT -> {
                            stack.push(copy(step = RIGHT))
                            node.left?.let {
                                stack.push(Frame(it, LEFT,
                                        true, inRightSubtree))
                            }
                        }
                        RIGHT -> {
                            stack.push(copy(step = DONE))
                            node.right?.let {
                                stack.push(Frame(it, LEFT,
                                        inLeftSubtree, true))
                            }
                        }
                        else -> Unit
                    }
                }
            }
        }
        return null
    }

}
