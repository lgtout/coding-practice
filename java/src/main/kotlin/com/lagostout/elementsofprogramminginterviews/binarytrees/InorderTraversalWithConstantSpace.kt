package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.rightAncestor
import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.elementsofprogramminginterviews.binarytrees.Step.*

enum class Step {
    PROCESS_NODE, LEFT_DESCENDANT,
    RIGHT_DESCENDANT, RIGHT_ANCESTOR, BREAK
}

fun <T : Comparable<T>> inOrderTraversalWithConstantSpace(
        root: BinaryTreeNode<T>?): List<T> {
    var currentNode = root?: return emptyList()
    var step: Step = LEFT_DESCENDANT
    val traversalOrder = mutableListOf<T>()
    whileAllNodesHaveNotBeenExplored@ while (true) {
        when (step) {
            LEFT_DESCENDANT -> {
                step = currentNode.left?.run {
                    currentNode = this
                    LEFT_DESCENDANT
                } ?: PROCESS_NODE

            }
            PROCESS_NODE -> {
                traversalOrder.add(currentNode.value)
                step = RIGHT_DESCENDANT
            }
            RIGHT_DESCENDANT -> {
                step = currentNode.right?.run {
                    currentNode = this
                    LEFT_DESCENDANT
                } ?: RIGHT_ANCESTOR
            }
            RIGHT_ANCESTOR -> {
                step = currentNode.rightAncestor?.run {
                    currentNode = this
                    PROCESS_NODE
                } ?: run {
                    currentNode.parent?.run {
                        currentNode = this
                        RIGHT_ANCESTOR
                    }?: BREAK
                }
            }
            BREAK -> {
                break@whileAllNodesHaveNotBeenExplored
            }
        }
    }
    return traversalOrder
}
