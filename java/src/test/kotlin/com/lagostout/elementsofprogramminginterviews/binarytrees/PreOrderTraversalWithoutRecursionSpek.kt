package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.datastructures.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class PreOrderTraversalWithoutRecursionSpek : Spek({
    describe("pathOfPreOrderTraversalWithoutRecursion") {
        testCases.forEach {
            given("binary tree ${it.root}") {
                it("computes the pre-order traversal " +
                        "path as ${it.expectedPath}") {
                    assertEquals(it.expectedPath,
                            pathOfPreOrderTraversalWithoutRecursion(it.root))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(private val rawTree: List<RawBinaryTreeNode<Int>> = emptyList(),
                            private val nodeIndicesOfExpectedPath: List<Int> = emptyList()) {
            val root: BinaryTreeNode<Int>?
            val expectedPath: List<BinaryTreeNode<Int>>
            init {
                val (root, expectedPath) = (fun (rawTree: List<RawBinaryTreeNode<Int>>):
                        Pair<BinaryTreeNode<Int>?, List<BinaryTreeNode<Int>>> {
                    val (root, nodes) = BinaryTreeNode.buildBinaryTree(rawTree)
                    val pathNodes = nodeIndicesOfExpectedPath.map {
                        nodes[it]
                    }
                    return Pair(root, pathNodes)
                })(rawTree)
                this.root = root
                this.expectedPath = expectedPath
            }
        }
        val testCases = listOf(
                TestCase(),
                TestCase(listOf(RawBinaryTreeNode(value = 0)), listOf(0)),
                TestCase(listOf(
                        RawBinaryTreeNode(value = 0, leftChildIndex = 1),
                        RawBinaryTreeNode(value = 1)),
                        listOf(0,1)),
                TestCase(listOf(
                        RawBinaryTreeNode(1, 2, value = 0),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 2)),
                        listOf(0,1,2)),
                TestCase(listOf(
                        RawBinaryTreeNode(rightChildIndex = 1, value = 0),
                        RawBinaryTreeNode(2, value = 1),
                        RawBinaryTreeNode(3, 4, value = 2),
                        RawBinaryTreeNode(value = 3),
                        RawBinaryTreeNode(value = 4)),
                        listOf(0,1,2,3,4)),
                TestCase(listOf(
                        RawBinaryTreeNode(1, 4, value = 0),
                        RawBinaryTreeNode(2, 3, value = 1),
                        RawBinaryTreeNode(value = 2),
                        RawBinaryTreeNode(value = 3),
                        RawBinaryTreeNode(5, 6, value = 4),
                        RawBinaryTreeNode(value = 5),
                        RawBinaryTreeNode(value = 6)),
                        listOf(0,1,2,3,4,5,6)),
                null
        ).filterNotNull()
    }
}