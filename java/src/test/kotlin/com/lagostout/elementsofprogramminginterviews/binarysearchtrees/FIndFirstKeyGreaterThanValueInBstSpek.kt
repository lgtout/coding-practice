package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.datastructures.RawBinaryTreeNode
import com.lagostout.elementsofprogramminginterviews.binarysearchtrees.FindFirstKeyGreaterThanValueInBst.firstKeyGreaterThanValueInBst
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class FindFirstKeyGreaterThanValueInBstSpek : Spek({
    describe("firstKeyGreaterThanValueInBst") {
        testCases.forEach {
            (_, value, expected, root) ->
            given("a binary search tree $root and value $value") {
                it("finds $expected as the first key greater than value") {
                    assertEquals(expected, firstKeyGreaterThanValueInBst(root, value))
                }
            }

        }
    }
}){
    private companion object {
        private data class TestCase(
                val rawNodes: List<RawBinaryTreeNode<Int>>,
                val value: Int = 0, val expected: Int? = null) {
            val root: BinaryTreeNode<Int> =
                    BinaryTreeNode.buildBinaryTree(rawNodes).first!!
            operator fun component4() = root
        }
        private val testCases = listOf(
                // Single node
                TestCase(listOf(RawBinaryTreeNode(value = 10)), 0, 10),
                TestCase(listOf(RawBinaryTreeNode(value = 10)), 10, null),
                TestCase(listOf(RawBinaryTreeNode(value = 10)), 15, null),
                // Node -> right node
                TestCase(listOf(
                        RawBinaryTreeNode(rightChildIndex = 1, value = 10),
                        RawBinaryTreeNode(value = 20)
                ), 15, 20),
                TestCase(listOf(
                        RawBinaryTreeNode(rightChildIndex = 1, value = 10),
                        RawBinaryTreeNode(value = 20)
                ), 30, null),
                // Node -> left node
                TestCase(listOf(
                        RawBinaryTreeNode(leftChildIndex = 1, value = 20),
                        RawBinaryTreeNode(value = 10)
                ), 5, 10),
                TestCase(listOf(
                        RawBinaryTreeNode(leftChildIndex = 1, value = 20),
                        RawBinaryTreeNode(value = 10)
                ), 15, 20),
                // Node -> right node -> left node
                TestCase(listOf(
                        RawBinaryTreeNode(rightChildIndex = 1, value = 10),
                        RawBinaryTreeNode(leftChildIndex = 2, value = 20),
                        RawBinaryTreeNode(value = 15)
                ), 18, 20),
                TestCase(listOf(
                        RawBinaryTreeNode(rightChildIndex = 1, value = 10),
                        RawBinaryTreeNode(leftChildIndex = 2, value = 20),
                        RawBinaryTreeNode(value = 15)
                ), 10, 15),
                // Node -> left node -> right node
                TestCase(listOf(
                        RawBinaryTreeNode(leftChildIndex = 1, value = 20),
                        RawBinaryTreeNode(rightChildIndex = 2, value = 10),
                        RawBinaryTreeNode(value = 15)
                ), 18, 20),
                TestCase(listOf(
                        RawBinaryTreeNode(leftChildIndex = 1, value = 20),
                        RawBinaryTreeNode(rightChildIndex = 2, value = 10),
                        RawBinaryTreeNode(value = 15)
                ), 10, 15),
                null
        ).filterNotNull()
    }
}
