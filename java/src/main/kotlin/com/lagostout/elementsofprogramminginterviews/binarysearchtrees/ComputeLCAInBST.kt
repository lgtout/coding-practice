package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.BinaryTreeNode

fun <T : Comparable<T>> computeLCAInBST(
        root: BinaryTreeNode<T>?,
        firstDescendant: T,
        secondDescendant: T): BinaryTreeNode<T>? {
    var lca: BinaryTreeNode<T>? = null
    var currentNode = root?: return null
    val descendants = listOf(firstDescendant, secondDescendant).sorted()
    while (true) {
        if (!descendants.contains(currentNode.value)) break
//        currentNode = currentNode.run {
//            if (descendants[0] > value) right
//            else if (descendants[1] < value ) left
//            else
    }
    return lca
}