package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import java.util.*

/**
 * Problem 10.12.2 page 168
 */
fun <T> reconstructBinaryTreeFromPostorderTraversal(
        inorderTraversal: List<T>, postorderTraversal: List<T>): BinaryTreeNode<T>? {
    if (inorderTraversal.isEmpty() || postorderTraversal.isEmpty())
        return null
    val postorderTraversalIterator = postorderTraversal.reversed().iterator()
    val root: BinaryTreeNode<T> = BinaryTreeNode(
            value = postorderTraversalIterator.next())
    val keyToInorderPositionMap = inorderTraversal.mapIndexed {
        index, element -> element to index }.toMap()
    val leftAncestors = LinkedList<BinaryTreeNode<T>>()
    var currentNode = root
    while (postorderTraversalIterator.hasNext()) {
        val key = postorderTraversalIterator.next()
        val nextNode = BinaryTreeNode(value = key)
        if (compareValues(keyToInorderPositionMap[key],
                keyToInorderPositionMap[currentNode.value]) > 0 ) {
            currentNode.right = nextNode
            leftAncestors.push(currentNode)
        } else {
            while (leftAncestors.isNotEmpty()) {
                val leftAncestor = leftAncestors.peek()
                if (compareValues(keyToInorderPositionMap[leftAncestor.value],
                        keyToInorderPositionMap[key]) > 0) break
                else currentNode = leftAncestors.pop()
            }
            currentNode.left = nextNode
        }
        nextNode.parent = currentNode
        currentNode = nextNode
    }
    return root
}
