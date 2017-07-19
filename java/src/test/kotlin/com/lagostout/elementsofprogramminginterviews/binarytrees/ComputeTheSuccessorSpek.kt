package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import com.lagostout.datastructures.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek

/**
 * We'll allow for degenerate cases:
 * - root is null
 * - predecessor is null
 * - predecessor is not in tree
 * We'll only find the successor if the predecessor
 * is present.
 */
class ComputeTheSuccessorSpek : Spek({

}) {
    companion object {
        data class TestCase(val rawNodes: List<RawBinaryTreeNode<Int>>,
                            val predecessor: Int,
                            val expectedNodeIndex: Int? = null) {
            val root: BinaryTreeNode<Int>
            operator fun component4() = root
            val expectedSuccessor: BinaryTreeNode<Int>?
            operator fun component5() = expectedSuccessor
            init {
                val (root, nodes) = BinaryTreeNode.buildBinaryTree(rawNodes)
                this.root = root
                expectedSuccessor = expectedNodeIndex?.let { nodes[expectedNodeIndex] }
            }
        }
        val testCases = listOf(
                // tree empty
                TestCase(listOf(), 10, null),
                // predecessor is not in the tree and is less than range in the tree
                TestCase(listOf(
                        RawBinaryTreeNode(value = 10)
                ), 0, null),
                // predecessor is not in the tree and is greater than range in the tree
                TestCase(listOf(
                        RawBinaryTreeNode(value = 10)
                ), 20, null),
                // predecessor is not in the tree and is in the range in the tree
                TestCase(listOf(
                        RawBinaryTreeNode(leftChildIndex = 1, rightChildIndex = 2, value = 10),
                        RawBinaryTreeNode(value = 5, parentIndex = 0),
                        RawBinaryTreeNode(value = 15, parentIndex = 0)
                ), 12, null),
                TestCase(listOf(
                        RawBinaryTreeNode(leftChildIndex = 1, rightChildIndex = 2, value = 10),
                        RawBinaryTreeNode(value = 5, parentIndex = 0),
                        RawBinaryTreeNode(value = 15, parentIndex = 0)
                ), 7, null),
                // predecessor is in the tree and successor is right child
                TestCase(listOf(
                        RawBinaryTreeNode(leftChildIndex = 1, rightChildIndex = 2, value = 10),
                        RawBinaryTreeNode(value = 5, parentIndex = 0),
                        RawBinaryTreeNode(value = 15, parentIndex = 0)
                ), 10, 2),
                // predecessor is in the tree, has no right child, and is left child
                TestCase(listOf(
                        RawBinaryTreeNode(leftChildIndex = 1, rightChildIndex = 2, value = 10),
                        RawBinaryTreeNode(value = 5, parentIndex = 0),
                        RawBinaryTreeNode(value = 15, parentIndex = 0)
                ), 10, 2),
                null
        ).filterNotNull()
    }
}