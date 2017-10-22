package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import java.util.*

/**
 * Problem 10.12.1 page 166
 */
fun <T> reconstructBinaryTreeFromPreorderTraversal(
        inorderTraversal: List<T>, preorderTraversal: List<T>): BinaryTreeNode<T>? {
    if (inorderTraversal.isEmpty() || preorderTraversal.isEmpty())
        return null
    val preorderTraversalIterator = preorderTraversal.iterator()
    val root: BinaryTreeNode<T> = BinaryTreeNode(
            value = preorderTraversalIterator.next())
    val keyToInorderPositionMap = inorderTraversal.mapIndexed {
        index, element -> element to index }.toMap()
    val rightAncestors = LinkedList<BinaryTreeNode<T>>()
    var currentNode = root
    while (preorderTraversalIterator.hasNext()) {
        val key = preorderTraversalIterator.next()
        val nextNode = BinaryTreeNode(value = key)
        if (compareValues(keyToInorderPositionMap[key],
                keyToInorderPositionMap[currentNode.value]) < 0 ) {
            currentNode.left = nextNode
            rightAncestors.push(currentNode)
        } else {
            while (rightAncestors.isNotEmpty()) {
                val rightAncestor = rightAncestors.peek()
                if (compareValues(keyToInorderPositionMap[rightAncestor.value],
                        keyToInorderPositionMap[key]) > 0) break
                else currentNode = rightAncestors.pop()
            }
            currentNode.right = nextNode
        }
        nextNode.parent = currentNode
        currentNode = nextNode
    }
    return root
}