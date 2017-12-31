package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.isLeftChild
import com.lagostout.common.isRightChild
import com.lagostout.datastructures.BinaryTreeNode

object BSTInsertionAndDeletion {

    private fun <T : Comparable<T>> find(
            value: T, root: BinaryTreeNode<T>):
            BinaryTreeNode<T> {
        var currentNode = root
        run {
            while (true) {
                if (value == currentNode.value) break
                else if (currentNode.value > value) {
                    currentNode.left?.let {
                        currentNode = it
                    } ?: return@run
                } else {
                    currentNode.right?.let {
                        currentNode = it
                    } ?: return@run
                }
            }
        }
        return currentNode
    }
    fun <T : Comparable<T>> insert(value: T, root: BinaryTreeNode<T>) {
        val duplicateOrParent = find(value, root)
        if (duplicateOrParent.value == value) return
        duplicateOrParent.let {
            (if (it.value > value) it::right
            else it::left).set(it)
        }
    }

    fun <T : Comparable<T>> delete(value: T, root: BinaryTreeNode<T>): BinaryTreeNode<T>? {
        val node =  find(value, root)
        if (node.value != value) return root
        val parent = node.parent
        // Cases: Deleting
        // -- root
        // -- leaf
        // -- root AND leaf
        // Detach subtree
        if (node.isRightChild) {
            Pair(node.left, node.right).apply {
                node.left = null
            }
        } else if (node.isLeftChild) {
            Pair(node.right, node.left).apply {
                node.right = null
            }
        } else {
            // TODO
            // root
            null
        }?.let { (detachedNode, root) ->
            // Check for nulls firsts
        }
        return null
    }

}