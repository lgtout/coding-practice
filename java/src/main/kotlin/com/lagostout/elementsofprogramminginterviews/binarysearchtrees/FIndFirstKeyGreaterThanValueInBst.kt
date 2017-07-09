package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.BinaryTreeNode
import java.util.*

/**
 * Problem 15.2 page 263
 */
fun firstKeyGreaterThanValueInBst(
        root: BinaryTreeNode<Int>, value: Int): Int? {
    var firstKeyGreaterThanValue: Int? = null
    var upperBound: Int? = null
    var node = root
    while (true) {
        if (node.value <= value) {
            if (node.right != null) {
                upperBound = node.value
                node = node.right
            } else {
                firstKeyGreaterThanValue = if (node.value > value) node.value
                else upperBound
                break
            }
        } else {
            if (node.left != null) {
                node = node.left
            } else {
                firstKeyGreaterThanValue = node.value
                break
            }
        }
    }
    return firstKeyGreaterThanValue
}