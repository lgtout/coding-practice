package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode

/**
 * Problem 15.2 page 263
 */
object FindFirstKeyGreaterThanValueInBst {

    fun firstKeyGreaterThanValueInBst(
            root: BinaryTreeNode<Int>, value: Int): Int? {
        var firstKeyGreaterThanValue: Int? = null
        var leftSubtreeUpperBound: Int? = null
        var node = root
        run runloop@ {
             while (true) {
                if (value >= node.value) {
                    node = node.right?: run {
                        firstKeyGreaterThanValue = leftSubtreeUpperBound
                        return@runloop
                    }
                } else {
                    node = node.left?.apply {
                        leftSubtreeUpperBound = value
                        left
                    }?: run {
                        firstKeyGreaterThanValue = value
                        return@runloop
                    }
                }
            }
        }
        return firstKeyGreaterThanValue
    }

}