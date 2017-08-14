package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import com.lagostout.elementsofprogramminginterviews.binarytrees.IterativePreAndPostOrderTraversalWithLinearSpace.preOrderTraversalPath
import com.lagostout.elementsofprogramminginterviews.binarytrees.IterativePreAndPostOrderTraversalWithLinearSpace.postOrderTraversalPath
import org.hamcrest.MatcherAssert.*
import org.hamcrest.Matchers

class IterativePreAndPostOrderTraversalWithLinearSpaceSpek : Spek({
    describe("preOrderTraversalPath") {
        testCases.forEach {
            (root, preOrderTraversalExpectedIndices, _) ->
            given("root $root") {
                it("returns $preOrderTraversalExpectedIndices") {
                    assertThat(preOrderTraversalPath(root),
                            Matchers.containsInAnyOrder(
                                    *preOrderTraversalExpectedIndices.toTypedArray()))
                }
            }
        }
    }
    describe("postOrderTraversalPath") {
        testCases.forEach {
            (root, _, postOrderTraversalExpectedIndices) ->
            given("root $root") {
                it("returns $postOrderTraversalExpectedIndices") {
                    assertThat(postOrderTraversalPath(root),
                            Matchers.containsInAnyOrder(
                                    *postOrderTraversalExpectedIndices.toTypedArray()))
                }
            }
        }
    }
}) {
    companion object {
        class TestCase<T : Comparable<T>>(
                rawTree: List<RawBinaryTreeNode<T>> = emptyList(),
                private val preOrderTraversalExpectedIndices: List<Int> = emptyList(),
                private val postOrderTraversalExpectedIndices: List<Int> = emptyList()) {
            val root = com.lagostout.datastructures.BinaryTreeNode.Companion.buildBinaryTree(rawTree).first!!
            operator fun component1() = root
            operator fun component2() = preOrderTraversalExpectedIndices
            operator fun component3() = postOrderTraversalExpectedIndices

        }
        val testCases = listOf(
                // TODO Do we need more cases?
                TestCase(listOf(RawBinaryTreeNode(value = 0)), listOf(0)),
//                TestCase(listOf(
//                        RawBinaryTreeNode(value = 0, leftChildIndex = 1),
//                        RawBinaryTreeNode(value = 1)),
//                        listOf(0,1),
//                        listOf(1,0)),
//                TestCase(listOf(
//                        RawBinaryTreeNode(1, 2, value = 0),
//                        RawBinaryTreeNode(value = 1),
//                        RawBinaryTreeNode(value = 2)),
//                        listOf(0,1,2),
//                        listOf(1,2,0)),
//                //    0
//                //     \
//                //      1
//                //     /
//                //    2
//                //   /  \
//                //  3    4
//                TestCase(listOf(
//                        RawBinaryTreeNode(rightChildIndex = 1, value = 0),
//                        RawBinaryTreeNode(2, value = 1),
//                        RawBinaryTreeNode(3, 4, value = 2),
//                        RawBinaryTreeNode(value = 3),
//                        RawBinaryTreeNode(value = 4)),
//                        listOf(0,1,2,3,4),
//                        listOf(3,4,2,1,0)),
//                TestCase(listOf(
//                        RawBinaryTreeNode(1, 4, value = 0),
//                        RawBinaryTreeNode(2, 3, value = 1),
//                        RawBinaryTreeNode(value = 2),
//                        RawBinaryTreeNode(value = 3),
//                        RawBinaryTreeNode(5, 6, value = 4),
//                        RawBinaryTreeNode(value = 5),
//                        RawBinaryTreeNode(value = 6)),
//                        listOf(0,1,2,3,4,5,6),
//                        listOf(2,3,1,5,6,4,0)),
                null
        ).filterNotNull()
    }
}
