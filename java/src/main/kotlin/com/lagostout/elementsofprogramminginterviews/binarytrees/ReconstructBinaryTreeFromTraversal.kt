package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode

/**
 * Problem 10.12.1 page 166
 */
fun <T> reconstructBinaryTreeFromTraversal(
        inorderTraversal: List<T>, preorderTraversal: List<T>): BinaryTreeNode<T>? {
    if (inorderTraversal.isEmpty() || preorderTraversal.isEmpty())
        return null
    val preorderTraversalIterator = preorderTraversal.iterator()
    val root: BinaryTreeNode<T> = BinaryTreeNode(
            value = preorderTraversalIterator.next())
    val keyToInorderPositionMap = inorderTraversal.mapIndexed {
        index, element -> element to index }.toMap()
    var currentNode = root
    while (preorderTraversalIterator.hasNext()) {
        val key = preorderTraversalIterator.next()
        val nextNode = BinaryTreeNode(value = key)
        if (compareValues(keyToInorderPositionMap[key],
                keyToInorderPositionMap[currentNode.value]) < 0 ) {
            currentNode.left = nextNode
        } else {
            while (!currentNode.isRoot) {
                if (compareValues(keyToInorderPositionMap[key],
                        keyToInorderPositionMap[currentNode.parent?.value]) < 0)
                    break
                currentNode = currentNode.parent!!
            }
            currentNode.right = nextNode
        }
        nextNode.parent = currentNode
        currentNode = nextNode
    }
    return root
}