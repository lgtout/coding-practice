package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import com.lagostout.common.RawBinaryTreeNode
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
                    assertEquals(kthNodeInInOrderTraversal(k, root), expected)
                }
            }
        }
    }
}) {
    private companion object {
        private data class TestCase(val expectedNodeIndex: Int = 0,
                                    val rawNodes: List<RawBinaryTreeNode<Int>> = emptyList(),
                                    val k: Int = 1)
        {
            val root: BinaryTreeNode<Int>?
            operator fun component4() = root
            val expected: BinaryTreeNode<Int>?
            operator fun component5() = root
            init {
                val result = BinaryTreeNode.buildBinaryTree(rawNodes)
                root = result.left
                val nodes = result.right
                expected = if (nodes != null && nodes.size > expectedNodeIndex)
                    nodes[expectedNodeIndex] else null
            }
        }
        private val testCases: List<TestCase> = listOf(
                TestCase(),
                TestCase(rawNodes = listOf(RawBinaryTreeNode(value = 1)),
                        expectedNodeIndex = 0),
                null).filterNotNull()
    }
}