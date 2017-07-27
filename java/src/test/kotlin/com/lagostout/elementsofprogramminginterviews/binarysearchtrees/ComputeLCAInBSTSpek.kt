package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.BinaryTreeNode
import com.lagostout.datastructures.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given

class ComputeLCAInBSTSpek : Spek({
    describe("") {
        testCases.forEach {
            given("") {

            }
        }
    }
}) {
    companion object {

        class TestCase(rawNodes: List<RawBinaryTreeNode<Int>> = emptyList(),
                       val firstDescendant: Int = -1,
                       val secondDescendant: Int = -1,
                       val expectedLCA: Int? = null) {
            val root: BinaryTreeNode<Int>? = BinaryTreeNode.buildBinaryTree(rawNodes).left
        }

        val testCases: List<TestCase> = run {
            listOf(
                    TestCase(),
                    /**
                     *  1
                     */
                    TestCase(listOf(RawBinaryTreeNode(value = 1))),
                    TestCase(listOf(RawBinaryTreeNode(value = 1)), firstDescendant = 1),
                    TestCase(listOf(RawBinaryTreeNode(value = 1)), secondDescendant = 1),
                    TestCase(listOf(RawBinaryTreeNode(value = 1)),
                            firstDescendant = 1, secondDescendant = 1, expectedLCA = 1),
                    /**
                     *    1
                     *   /
                     *  0
                     */
                    TestCase(listOf(RawBinaryTreeNode(leftChildIndex = 1, value = 1),
                            RawBinaryTreeNode(value = 0)), firstDescendant = 0,
                            secondDescendant = 1, expectedLCA = 0),
                    // First/last descendants reversed
                    TestCase(listOf(RawBinaryTreeNode(leftChildIndex = 1, value = 1),
                            RawBinaryTreeNode(value = 0)), firstDescendant = 1,
                            secondDescendant = 0, expectedLCA = 0),
                    // Descendants missing
                    TestCase(listOf(RawBinaryTreeNode(leftChildIndex = 1, value = 1),
                            RawBinaryTreeNode(value = 0)), firstDescendant = 2,
                            secondDescendant = 0),
                    TestCase(listOf(RawBinaryTreeNode(leftChildIndex = 1, value = 1),
                            RawBinaryTreeNode(value = 0)), firstDescendant = 1,
                            secondDescendant = -1),
                    /**
                     *  1
                     *   \
                     *    2
                     */
                    TestCase(listOf(RawBinaryTreeNode(rightChildIndex = 1, value = 1),
                            RawBinaryTreeNode(value = 2)), firstDescendant = 0,
                            secondDescendant = 1, expectedLCA = 1),
                    // First/last descendants reversed
                    TestCase(listOf(RawBinaryTreeNode(rightChildIndex = 1, value = 1),
                            RawBinaryTreeNode(value = 2)), firstDescendant = 1,
                            secondDescendant = 0),
                    // Descendants missing
                    TestCase(listOf(RawBinaryTreeNode(rightChildIndex = 1, value = 1),
                            RawBinaryTreeNode(value = 2)), firstDescendant = 0,
                            secondDescendant = 0),
                    TestCase(listOf(RawBinaryTreeNode(rightChildIndex = 1, value = 1),
                            RawBinaryTreeNode(value = 2)), firstDescendant = 1,
                            secondDescendant = 3),
                    // TODO More cases
                    null).filterNotNull()
        }
    }
}