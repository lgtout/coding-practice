package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.elementsofprogramminginterviews.binarysearchtrees.BuildMinimumHeightBST.Step.*
import java.util.*
import kotlin.math.roundToInt

object BuildMinimumHeightBST {

    enum class Step {
        LEFT, RIGHT, DONE
    }

    data class Frame(val start: Int, val end: Int,
                     val step: Step = LEFT) {
        val center = ((end - start)/2.0).roundToInt() + start
    }

    fun <T : Comparable<T>> buildMinimumHeightBST(
            sortedValues: List<T>): BinaryTreeNode<T>? {
        if (sortedValues.isEmpty()) return null
        val stack = LinkedList<Frame>().apply {
            add(Frame(0, sortedValues.lastIndex))
        }
        val center = stack.peek().center
        val nodes = MutableList(sortedValues.size) {
            null as BinaryTreeNode<T>? }
        while (stack.isNotEmpty()) {
            stack.pop().also { frame ->
                if (frame.start <= frame.end) {
                    when (frame.step) {
                        LEFT -> {
                            nodes[frame.center] = BinaryTreeNode(
                                    value = sortedValues[frame.center])
                            stack.push(frame.copy(step = RIGHT))
                            stack.push(frame.copy(end = frame.center - 1, step = LEFT))
                        }
                        RIGHT -> {
                            stack.push(frame.copy(step = DONE))
                            stack.push(frame.copy(start = frame.center + 1, step = LEFT))
                        }
                        else -> {
                            if (stack.isNotEmpty()) {
                                stack.peek().center.let { parentIndex ->
                                    nodes[parentIndex]?.let { parent ->
                                        (if (frame.center < parentIndex)
                                            parent::left else parent::right)
                                                .set(nodes[frame.center])
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return nodes[center]
    }

}