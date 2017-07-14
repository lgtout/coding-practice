package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.BinaryTreeNode
import java.util.*

/**
 * Problem 15.2 page 263
 */
object FindFirstKeyGreaterThanValueInBst {

    fun firstKeyGreaterThanValueInBst(
            root: BinaryTreeNode<Int>, value: Int): Int? {
        val firstKeyGreaterThanValue: Int?
        var leftSubtreeUpperBound: Int? = null
        var node = root
        while (true) {
            if (value >= node.value) {
                if (node.right == null) {
                    firstKeyGreaterThanValue = leftSubtreeUpperBound
                    break
                } else node = node.right
            } else {
                if (node.left == null) {
                    firstKeyGreaterThanValue = node.value
                    break
                } else {
                    leftSubtreeUpperBound = node.value
                    node = node.left
                }
            }
        }
        return firstKeyGreaterThanValue
    }

}