package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.elementsofprogramminginterviews.binarytrees.ComputeExteriorOfBinaryTree.Step.*
import java.util.*

/* Problem 10.15 page 170 */

object ComputeExteriorOfBinaryTree {

    private enum class Step { LEFT, RIGHT, DONE }

    private data class Frame<T>(val node: BinaryTreeNode<T>,
                                val step: Step = LEFT,
                                val leftCount: Int = 0,
                                val rightCount: Int = 0)

    fun <T> computeExteriorOfBinaryTree(root: BinaryTreeNode<T>) :
            List<T> {
        val stack = LinkedList<Frame<T>>().apply {
            add(Frame(root, LEFT))
        }
        val exterior = mutableListOf<T>()
        while (stack.isNotEmpty()) {
            var frame = stack.pop()
            if (frame.step == LEFT) {
                if (frame.node.isNotALeaf) {
                    frame = frame.copy(step = RIGHT)
                    stack.push(frame)
                }
                if (frame.rightCount == 0 || frame.node.isALeaf)
                    exterior.add(frame.node.value)
                frame.node.left?.let { node ->
                    stack.push(Frame(node = node,
                            leftCount = frame.leftCount + 1,
                            rightCount = frame.rightCount))
                }
            } else if (frame.step == RIGHT)  {
                if (frame.node.isNotALeaf) {
                    frame = frame.copy(step = DONE)
                    stack.push(frame)
                }
                if (frame.node.isALeaf && frame.node != root)
                    exterior.add(frame.node.value)
                frame.node.right?.let { node ->
                    stack.push(Frame(node = node,
                            leftCount = frame.leftCount,
                            rightCount = frame.rightCount + 1))
                }
            } else if (frame.rightCount == 0 && frame.node != root) {
                exterior.add(frame.node.value)
            }
        }
        return exterior
    }

}
