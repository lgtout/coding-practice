package com.lagostout.bytebybyte.recursion

import com.lagostout.datastructures.BinaryTreeNode

fun findAllPossibleArraysThatCouldResultInBinaryTree(
        root: BinaryTreeNode<Int>?): List<List<Int>> {
    if (root == null) return listOf(emptyList())
    val possibleArraysOfLeftSubtree =
            findAllPossibleArraysThatCouldResultInBinaryTree(root.left)
    val possibleArraysOfRightSubtree =
            findAllPossibleArraysThatCouldResultInBinaryTree(root.right)
    return possibleArraysOfLeftSubtree.flatMap { leftArray ->
        possibleArraysOfRightSubtree.flatMap { rightArray ->
            listOf(leftArray + rightArray, rightArray + leftArray).toSet().map {
                listOf(root.value) + it
            }
        }
    }
}