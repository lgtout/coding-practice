package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode

    enum class Step {
    PROCESS_NODE, GO_LEFT
}
fun <T : Comparable<T>> inorderTraversalWithConstantSpace(
        root: BinaryTreeNode<T>) {
    var currentNode = root
    var step: Step = Step.GO_LEFT
    while (true) {
        if (currentNode.left != null) {
            currentNode = currentNode.left
        } //else
    }
}
