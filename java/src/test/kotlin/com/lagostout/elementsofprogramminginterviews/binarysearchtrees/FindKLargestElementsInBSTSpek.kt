package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.BinaryTreeNode
import com.lagostout.common.BinaryTreeNode.buildBinaryTree
import com.lagostout.datastructures.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class FindKLargestElementsInBSTSpek : Spek({
    describe("findKLargestElementsInBST") {
        testCases.forEach {
            (_, k, expected, tree) ->
            given("tree $tree k $k") {
                it("finds k largest elements as $expected") {
                    assertEquals(expected, findKLargestElementsInBST(tree, k))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val rawNodes: List<RawBinaryTreeNode<Int>>,
                            val k: Int, val expected: List<Int>) {
            val root: BinaryTreeNode<Int>? = buildBinaryTree(rawNodes).left
            operator fun component4() = root
        }
        val testCases: List<TestCase> = listOf(
                // empty
                TestCase(listOf(), 0, emptyList()),
                // n
                TestCase(listOf(RawBinaryTreeNode(value = 1)), 0, emptyList()),
                TestCase(listOf(RawBinaryTreeNode(value = 1)), 1, listOf(1)),
                TestCase(listOf(RawBinaryTreeNode(value = 1)), 2, listOf(1)),
                // n
                //  \
                //   n
                TestCase(listOf(
                        RawBinaryTreeNode(value = 1, rightChildIndex = 1),
                        RawBinaryTreeNode(value = 2)),
                        1, listOf(2)),
                TestCase(listOf(
                        RawBinaryTreeNode(value = 1, rightChildIndex = 1),
                        RawBinaryTreeNode(value = 2)),
                        2, listOf(2,1)),
                TestCase(listOf(
                        RawBinaryTreeNode(value = 1, rightChildIndex = 1),
                        RawBinaryTreeNode(value = 2)),
                        3, listOf(2,1)),
                //   n
                //  /
                // n
                TestCase(listOf(
                        RawBinaryTreeNode(value = 2, leftChildIndex = 1),
                        RawBinaryTreeNode(value = 1)),
                        1, listOf(2)),
                TestCase(listOf(
                        RawBinaryTreeNode(value = 2, leftChildIndex = 1),
                        RawBinaryTreeNode(value = 1)),
                        2, listOf(2,1)),
                TestCase(listOf(
                        RawBinaryTreeNode(value = 2, leftChildIndex = 1),
                        RawBinaryTreeNode(value = 1)),
                        3, listOf(2, 1)),
                // n
                //  \
                //   n
                //  /
                // n
                TestCase(listOf(
                        RawBinaryTreeNode(value = 2, rightChildIndex = 1),
                        RawBinaryTreeNode(value = 4, leftChildIndex = 2),
                        RawBinaryTreeNode(value = 3)),
                        1, listOf(4)),
                TestCase(listOf(
                        RawBinaryTreeNode(value = 2, rightChildIndex = 1),
                        RawBinaryTreeNode(value = 4, leftChildIndex = 2),
                        RawBinaryTreeNode(value = 3)),
                        2, listOf(4,3)),
                TestCase(listOf(
                        RawBinaryTreeNode(value = 2, rightChildIndex = 1),
                        RawBinaryTreeNode(value = 4, leftChildIndex = 2),
                        RawBinaryTreeNode(value = 3)),
                        3, listOf(4,3,2)),
                TestCase(listOf(
                        RawBinaryTreeNode(value = 2, rightChildIndex = 1),
                        RawBinaryTreeNode(value = 4, leftChildIndex = 2),
                        RawBinaryTreeNode(value = 3)),
                        4, listOf(4,3,2)),
                //   n
                //  /
                // n
                //  \
                //   n
                TestCase(listOf(
                        RawBinaryTreeNode(value = 4, leftChildIndex = 1),
                        RawBinaryTreeNode(value = 2, rightChildIndex = 2),
                        RawBinaryTreeNode(value = 3)),
                        1, listOf(4)),
                TestCase(listOf(
                        RawBinaryTreeNode(value = 4, leftChildIndex = 1),
                        RawBinaryTreeNode(value = 2, rightChildIndex = 2),
                        RawBinaryTreeNode(value = 3)),
                        2, listOf(4,3)),
                TestCase(listOf(
                        RawBinaryTreeNode(value = 4, leftChildIndex = 1),
                        RawBinaryTreeNode(value = 2, rightChildIndex = 2),
                        RawBinaryTreeNode(value = 3)),
                        3, listOf(4,3,2)),
                TestCase(listOf(
                        RawBinaryTreeNode(value = 4, leftChildIndex = 1),
                        RawBinaryTreeNode(value = 2, rightChildIndex = 2),
                        RawBinaryTreeNode(value = 3)),
                        4, listOf(4,3,2)),
                null
        ).filterNotNull()
    }
}