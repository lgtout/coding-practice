package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode
import java.util.*

/**
 * Problem 15.5 page 266
 */
// TODO Can postorder and preorder reconstruction share code?
// TODO This solution needs work.
fun <T : Comparable<T>> reconstructBSTFromPostorderTraversal(
        path: List<T>): BinaryTreeNode<T>? {
    if (path.isEmpty()) return null
//    val pathIterator = path.reversed().iterator()
    val pathIterator = path.iterator()
    val root = BinaryTreeNode(value = pathIterator.next())
    var treeNode = root
    val rightAncestors = LinkedList<BinaryTreeNode<T>>()
    while (true) {
        if (!pathIterator.hasNext()) break
        val node = BinaryTreeNode(value = pathIterator.next())
        if (node.value < treeNode.value) {
            treeNode.left = node
            node.parent = treeNode
            rightAncestors.push(treeNode)
        } else {
            var parent = treeNode
            while (rightAncestors.isNotEmpty()) {
                if (rightAncestors.peek().value > node.value) {
                    parent = rightAncestors.pop()
                } else break
            }
            node.parent = parent
            parent.right = node
        }
        treeNode = node
    }
    return root
}

// Alternative approach to using a stack to backtrack up the tree
// is to take advantage of what's revealed by the relative position
// of a pair of nodes in a preorder traversal: A node cannot be the
// right child of another node if the former appears before the
// latter in a preorder traversal.

// This solution is done!
fun <T : Comparable<T>> reconstructBSTFromPreorderTraversal(
        path: List<T>): BinaryTreeNode<T>? {
    if (path.isEmpty()) return null
    val pathIterator = path.iterator()
    val root = BinaryTreeNode(value = pathIterator.next())
    var treeNode = root
    val rightAncestors = LinkedList<BinaryTreeNode<T>>()
    while (true) {
        if (!pathIterator.hasNext()) break
        val node = BinaryTreeNode(value = pathIterator.next())
        if (node.value < treeNode.value) {
            treeNode.left = node
            node.parent = treeNode
            rightAncestors.push(treeNode)
        } else {
            var parent = treeNode
            while (rightAncestors.isNotEmpty()) {
                if (rightAncestors.peek().value > node.value) {
                    parent = rightAncestors.pop()
                } else break
            }
            node.parent = parent
            parent.right = node
        }
        treeNode = node
    }
    return root
}

