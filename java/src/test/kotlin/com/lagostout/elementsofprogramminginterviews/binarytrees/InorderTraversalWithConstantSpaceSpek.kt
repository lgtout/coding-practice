package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.datastructures.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class InOrderTraversalWithConstantSpaceSpek: Spek({
    describe("pathTakenByInOrderTraversalWithoutRecursion") {
        testCases.forEach {
            given("binary tree ${it.root}") {
                it("computes the in order traversal " +
                        "path as ${it.expectedValuesInOrderTraversed}") {
                    assertEquals(it.expectedValuesInOrderTraversed,
                            inOrderTraversalWithConstantSpace(it.root))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(private val rawTree: List<RawBinaryTreeNode<Int>> = emptyList(),
                            private val nodeIndicesOfExpectedPath: List<Int> = emptyList()) {
            val root: BinaryTreeNode<Int>?
            val expectedValuesInOrderTraversed: List<Int>
            init {
                val (root, expectedValuesInOrderTraversed) = run {
                    val (root, nodes) = BinaryTreeNode.buildBinaryTree(rawTree)
                    val valuesInOrderTraversed = nodeIndicesOfExpectedPath.map {
                        nodes[it].value
                    }
                    Pair(root, valuesInOrderTraversed)
                }
                this.root = root
                this.expectedValuesInOrderTraversed = expectedValuesInOrderTraversed
            }
        }
        val testCases = listOf(
                TestCase(),
                TestCase(listOf(RawBinaryTreeNode(value = 0)), listOf(0)),
                TestCase(listOf(
                        RawBinaryTreeNode(value = 0, leftChildIndex = 1),
                        RawBinaryTreeNode(value = 1, parentIndex = 0)),
                        listOf(1,0)),
                TestCase(listOf(
                        RawBinaryTreeNode(1, 2, value = 0),
                        RawBinaryTreeNode(value = 1, parentIndex = 0),
                        RawBinaryTreeNode(value = 2, parentIndex = 0)),
                        listOf(1,0,2)),
                TestCase(listOf(
                        RawBinaryTreeNode(rightChildIndex = 1, value = 0),
                        RawBinaryTreeNode(2, value = 1, parentIndex = 0),
                        RawBinaryTreeNode(3, 4,
                                value = 2, parentIndex = 1),
                        RawBinaryTreeNode(value = 3, parentIndex = 2),
                        RawBinaryTreeNode(value = 4, parentIndex = 2)),
                        listOf(0,3,2,4,1)),
                TestCase(listOf(
                        RawBinaryTreeNode(1, 4, value = 0),
                        RawBinaryTreeNode(2, 3, value = 1,
                                parentIndex = 0),
                        RawBinaryTreeNode(value = 2, parentIndex = 1),
                        RawBinaryTreeNode(value = 3, parentIndex = 1),
                        RawBinaryTreeNode(5, 6, value = 4,
                                parentIndex = 0),
                        RawBinaryTreeNode(value = 5, parentIndex = 4),
                        RawBinaryTreeNode(value = 6, parentIndex = 4)),
                        listOf(2,1,3,0,5,4,6)),
                null
        ).filterNotNull()
    }
}
