package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import com.lagostout.common.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(JUnitPlatform::class)
class InOrderTraversalWithoutRecursionSpek : Spek({
    describe("pathTakenByInOrderTraversalWithoutRecursion") {
        testCases.forEach {
            given("binary tree ${it.root}") {
                it("computes the in order traversal path as ${it.expectedPath}") {
                    assertEquals(it.expectedPath,
                            pathTakenByInOrderTraversalWithoutRecursion(it.root))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(private val rawTree: List<RawBinaryTreeNode<Int>> = emptyList(),
                            private val nodeIndicesOfExpectedPath: List<Int> = emptyList()) {
            val root: BinaryTreeNode<Int>
            val expectedPath: List<BinaryTreeNode<Int>>
            init {
                val (root, expectedPath) = (fun (rawTree: List<RawBinaryTreeNode<Int>>):
                        Pair<BinaryTreeNode<Int>, List<BinaryTreeNode<Int>>> {
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
                        RawBinaryTreeNode(value=1)),
                        listOf(0,1)),
                TestCase())
    }
}