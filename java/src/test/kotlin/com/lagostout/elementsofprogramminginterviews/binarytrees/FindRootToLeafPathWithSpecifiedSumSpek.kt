package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import com.lagostout.common.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals


class FindRootToLeafPathWithSpecifiedSum : Spek({

    data class TestCase(val sum: Int,
                        val rawNodes: List<RawBinaryTreeNode<Int>>,
                        val expected: Boolean) {
        val tree = BinaryTreeNode.buildBinaryTree<Int>(rawNodes)[0]
    }

    // TODO
    // Random test case generation.
    // Approach:
    // - Build random tree.
    // - Compute sums of all paths.  Maybe store sums as map of leaf to sum.
    // - Randomly decide whether tree should contain a path with the sum sought.
    //  - If it should, pick a random sum from the map of path sums built.
    //  - If not, randomly choose a sum that's not in the map.

    describe("treeContainsRootToLeafPath") {
        listOf(
                TestCase(0, listOf(RawBinaryTreeNode(value = 0)), true),
                TestCase(1, listOf(RawBinaryTreeNode(value = 0)),false),
                TestCase(1, listOf(
                        RawBinaryTreeNode(rightChildIndex = 1, value = 0),
                        RawBinaryTreeNode(value = 1)), true),
                TestCase(2, listOf(
                        RawBinaryTreeNode(rightChildIndex = 1, value = 0),
                        RawBinaryTreeNode(value = 1)),false),
                TestCase(2, listOf(
                        RawBinaryTreeNode(rightChildIndex = 1, value = 0),
                        RawBinaryTreeNode(leftChildIndex = 2, value = 0),
                        RawBinaryTreeNode(value = 2)), true),
                TestCase(2, listOf(
                        RawBinaryTreeNode(rightChildIndex = 1, leftChildIndex = 2, value = 1),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 1)), true),
                TestCase(4, listOf(
                        RawBinaryTreeNode(leftChildIndex = 1, rightChildIndex = 2, value = 1),
                        RawBinaryTreeNode(rightChildIndex = 3, value = 2),
                        RawBinaryTreeNode(rightChildIndex = 4, value = 1),
                        RawBinaryTreeNode(value = 0),
                        RawBinaryTreeNode(value = 2)
                ), true),
                TestCase(4, listOf( // Two paths have sum sought
                        RawBinaryTreeNode(leftChildIndex = 1, rightChildIndex = 2, value = 1),
                        RawBinaryTreeNode(rightChildIndex = 3, value = 2),
                        RawBinaryTreeNode(rightChildIndex = 4, value = 1),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 2)
                ), true)
        ).forEach {
            given("a binary tree ${it.tree}") {
                it("returns ${it.expected}") {
                    assertEquals(it.expected, treeContainsRootToLeafPath(it.tree, it.sum))
                }
            }
        }
    }

})
