package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import com.lagostout.common.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class InOrderTraversalWithoutRecursionSpek : Spek({
    describe("pathTakenByInOrderTraversalWithoutRecursion") {
        testCases.forEach {
            (_, _, root, expected) ->
            given("binary tree $root") {
                it("computes the in order traversal path as $expected") {
                    assertEquals(expected, pathTakenByInOrderTraversalWithoutRecursion(root))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val rawTree: List<RawBinaryTreeNode<Int>> = emptyList(),
                            val nodeIndicesOfExpectedPath: List<Int> = emptyList()) {
            var root: BinaryTreeNode<Int>
            var expectedPath: List<BinaryTreeNode<Int>>
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
            operator fun component3() = root
            operator fun component4() = expectedPath
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