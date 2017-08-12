package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import com.lagostout.datastructures.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

class IterativePreAndPostOrderTraversalWithLinearSpaceSpek : Spek({
    describe("preOrderTraversal") {
        testCases.forEach {
            given("") {
                it("") {

                }
            }
        }
    }
}) {
    companion object {
        // TODO - Recursive pre- and post-order traversal for solution comparison
        data class TestCase<T : Comparable<T>>(
                val rawTree: List<RawBinaryTreeNode<T>> = emptyList(),
                val preOrderTraversalExpectedIndices: List<Int> = emptyList(),
                val postOrderTraversalExpectedIndices: List<Int> = emptyList())
        val testCases = listOf(
                TestCase(),
                TestCase(listOf(RawBinaryTreeNode(value = 0)), listOf(0)),
                TestCase(listOf(
                        RawBinaryTreeNode(value = 0, leftChildIndex = 1),
                        RawBinaryTreeNode(value = 1)),
                        listOf(0,1),
                        listOf(1,0)),
                TestCase(listOf(
                        RawBinaryTreeNode(1, 2, value = 0),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 2)),
                        listOf(0,1,2),
                        listOf(1,2,0)),
                //    0
                //     \
                //      1
                //     /
                //    2
                //   /  \
                //  3    4
                TestCase(listOf(
                        RawBinaryTreeNode(rightChildIndex = 1, value = 0),
                        RawBinaryTreeNode(2, value = 1),
                        RawBinaryTreeNode(3, 4, value = 2),
                        RawBinaryTreeNode(value = 3),
                        RawBinaryTreeNode(value = 4)),
                        listOf(0,1,2,3,4),
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
