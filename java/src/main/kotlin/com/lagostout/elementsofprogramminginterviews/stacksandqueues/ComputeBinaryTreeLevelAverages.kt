package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import com.lagostout.datastructures.BinaryTreeNode
import java.util.*

/**
 * Problem 9.7.4 page 146
 */
fun computeBinaryTreeLevelAverages(
        root: BinaryTreeNode<Int>): List<Double> {
    val queue = LinkedList<List<BinaryTreeNode<Int>>>().apply {
        add(listOf(root))
    }
    val averages = mutableListOf<Double>()
    while (!queue.isEmpty()) {
        val level = queue.remove()
        level.run {
            sumBy {
                it.value
            }.toDouble() / size
        }.let {
            averages.add(it)
        }
        level.flatMap {
            listOf(it.left, it.right)
                    .filterNotNull()
        }.let {
            if (!it.isEmpty())
                queue.add(it)
        }
    }
    return averages
}