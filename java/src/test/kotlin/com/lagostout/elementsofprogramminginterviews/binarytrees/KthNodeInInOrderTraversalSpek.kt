package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.datastructures.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class KthNodeInInOrderTraversalSpek : Spek({
    describe("kthNodeInInOrderTraversal") {
        testCases.forEach {
            (_, _, k, root, expected) ->
            given("binary tree $root") {
                it("computes $expected as the $k-th node") {
                    assertEquals(expected, kthNodeInInOrderTraversal(k, root))
                }
            }
        }
    }
}) {
    private companion object {
        private data class TestCase(
                val expectedNodeIndex: Int = 0,
                val rawNodes: List<RawBinaryTreeNode<Int>> = emptyList(),
                val k: Int = 0) {
            val root: BinaryTreeNode<Int>?
            operator fun component4() = root
            val expected: BinaryTreeNode<Int>?
            operator fun component5() = expected
            init {
                val result = BinaryTreeNode.buildBinaryTree(rawNodes)
                root = result.first
                val nodes = result.second
                expected = if (nodes != null && nodes.size > expectedNodeIndex)
                    nodes[expectedNodeIndex] else null
            }
        }
        private val testCases: List<TestCase> = listOf(
                TestCase(),
                TestCase(rawNodes = listOf(RawBinaryTreeNode(value = 1)),
                        expectedNodeIndex = 0),
                TestCase(rawNodes = listOf(
                        RawBinaryTreeNode(value = 2, leftChildIndex = 1),
                        RawBinaryTreeNode(value = 1)
                ), expectedNodeIndex = 1),
                TestCase(rawNodes = listOf(
                        RawBinaryTreeNode(value = 3, leftChildIndex = 1, rightChildIndex = 2),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 1)
                ), expectedNodeIndex = 1),
                TestCase(rawNodes = listOf(
                        RawBinaryTreeNode(value = 3, leftChildIndex = 1, rightChildIndex = 2),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 1)
                ), expectedNodeIndex = 0, k = 1),
                TestCase(rawNodes = listOf(
                        RawBinaryTreeNode(value = 3, leftChildIndex = 1, rightChildIndex = 2),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 1)
                ), expectedNodeIndex = 2, k = 2),
                TestCase(rawNodes = listOf(
                        RawBinaryTreeNode(value = 4, leftChildIndex = 1, rightChildIndex = 2),
                        RawBinaryTreeNode(value = 2, leftChildIndex = 3),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 1)
                ), expectedNodeIndex = 3, k = 3),
                TestCase(rawNodes = listOf(
                        RawBinaryTreeNode(value = 4, leftChildIndex = 1, rightChildIndex = 2),
                        RawBinaryTreeNode(value = 2, leftChildIndex = 3),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 1)
                ), expectedNodeIndex = 0, k = 2),
                TestCase(rawNodes = listOf(
                        RawBinaryTreeNode(value = 5, leftChildIndex = 1, rightChildIndex = 2),
                        RawBinaryTreeNode(value = 2, leftChildIndex = 3),
                        RawBinaryTreeNode(value = 2, rightChildIndex = 4),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 1)
                ), expectedNodeIndex = 4, k = 4),
                TestCase(rawNodes = listOf(
                        RawBinaryTreeNode(value = 5, leftChildIndex = 1, rightChildIndex = 2),
                        RawBinaryTreeNode(value = 2, leftChildIndex = 3),
                        RawBinaryTreeNode(value = 2, rightChildIndex = 4),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 1)
                ), expectedNodeIndex = 0, k = 2),
                TestCase(rawNodes = listOf(
                        RawBinaryTreeNode(value = 7, leftChildIndex = 1, rightChildIndex = 2),
                        RawBinaryTreeNode(value = 2, leftChildIndex = 3),
                        RawBinaryTreeNode(value = 4, rightChildIndex = 4),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 3, leftChildIndex = 5),
                        RawBinaryTreeNode(value = 2, rightChildIndex = 6),
                        RawBinaryTreeNode(value = 1)
                ), expectedNodeIndex = 5, k = 4),
                TestCase(rawNodes = listOf(
                        RawBinaryTreeNode(value = 9, leftChildIndex = 1, rightChildIndex = 2),
                        RawBinaryTreeNode(value = 3, leftChildIndex = 3, rightChildIndex = 7),
                        RawBinaryTreeNode(value = 5, leftChildIndex = 8, rightChildIndex = 4),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 3, leftChildIndex = 5),
                        RawBinaryTreeNode(value = 2, rightChildIndex = 6),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 1)
                ), expectedNodeIndex = 0, k = 3),
                TestCase(rawNodes = listOf(
                        RawBinaryTreeNode(value = 9, leftChildIndex = 1, rightChildIndex = 2),
                        RawBinaryTreeNode(value = 3, leftChildIndex = 3, rightChildIndex = 7),
                        RawBinaryTreeNode(value = 5, leftChildIndex = 8, rightChildIndex = 4),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 3, leftChildIndex = 5),
                        RawBinaryTreeNode(value = 2, rightChildIndex = 6),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 1)
                ), expectedNodeIndex = 8, k = 4),
                null).filterNotNull()
    }
}