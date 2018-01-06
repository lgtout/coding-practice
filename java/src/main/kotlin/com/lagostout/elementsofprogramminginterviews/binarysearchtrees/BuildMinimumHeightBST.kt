package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.elementsofprogramminginterviews.binarysearchtrees.BuildMinimumHeightBST.Step.*
import java.util.*

object BuildMinimumHeightBST {

    enum class Step {
        LOOK_LEFT, LOOK_RIGHT, DONE
    }

    data class Frame<T : Comparable<T>>(
            val start: Int, val center: Int, val end: Int, val step: Step = LOOK_LEFT,
            val left: BinaryTreeNode<T>? = null, val right: BinaryTreeNode<T>? = null,
            val root: BinaryTreeNode<T>? = null)

    fun <T : Comparable<T>> buildMinimumHeightBST(sortedValues: List<T>): BinaryTreeNode<T>? {
        val stack = LinkedList<Frame<T>>().apply {
//            Frame<T>(0, sortedValues.lastIndex)
        }
        val root: BinaryTreeNode<T>? = null
        while (stack.isNotEmpty()) {
            stack.pop().also { frame ->
                if (frame.start == frame.end) {
                    val node = BinaryTreeNode(value = sortedValues[frame.start])
                    stack.pop().copy(
                            left = if (frame.step == LOOK_LEFT) node else null,
                            right = if (frame.step == LOOK_RIGHT) node else null).let {
                       stack.push(it)
                    }
                } else if (frame.start < frame.end){
                    when (frame.step) {
                        LOOK_LEFT -> {
                            val center = (frame.start + frame.end)/2
                            stack.push(frame.copy(start = frame.start, end = center - 1))
                        }
                        LOOK_RIGHT -> {
                            val center = (frame.start + frame.end)/2
                            stack.push(frame.copy(start = center + 1, end = frame.end))
                        }
                        DONE -> {
//                            val node = BinaryTreeNode<T>(value = )
                        }
                    }
                }
            }
        }
        return root
    }

}