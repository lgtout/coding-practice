package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.BinaryTreeNode
import com.lagostout.common.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(JUnitPlatform::class)
class TestIfBinaryTreeSatisfiesBstPropertySpek : Spek({
    describe("satisfiesBstProperty") {
        testCases.forEach {
            (_, satisfiesBstProperty, root) ->
            context("binary tree $root") {
                val label = "${ if (satisfiesBstProperty) "satisfies" else "does not satisfy" } "
                it("computes tree $label the BST property") {
                    assertEquals(satisfiesBstProperty,
                            binaryTreeSatisfiesBstProperty(root))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val rawNodes: List<RawBinaryTreeNode<Int>> = listOf(),
                            val satisfiesBstProperty: Boolean = false) {
            var root: BinaryTreeNode<Int> = BinaryTreeNode.buildBinaryTree(rawNodes).left
            operator fun component3() = root
        }
        val testCases = listOf(
                TestCase(listOf(RawBinaryTreeNode(value = 1)),
                        true),
                TestCase(listOf(
                        RawBinaryTreeNode(rightChildIndex = 1, value = 2),
                        RawBinaryTreeNode(value = 1)),
                        false),
                TestCase(listOf(
                        RawBinaryTreeNode(rightChildIndex = 1, value = 2),
                        RawBinaryTreeNode(value = 3)),
                        true),
                TestCase(listOf(
                        RawBinaryTreeNode(leftChildIndex = 1, value = 2),
                        RawBinaryTreeNode(value = 3)),
                        false),
                TestCase(listOf(
                        RawBinaryTreeNode(leftChildIndex = 1, value = 2),
                        RawBinaryTreeNode(value = 1)),
                        true),
                TestCase(listOf(
                        RawBinaryTreeNode(1, 2, value = 2),
                        RawBinaryTreeNode(value = 1),
                        RawBinaryTreeNode(value = 3)),
                        true),
                TestCase(listOf(
                        RawBinaryTreeNode(1, 2, value = 2),
                        RawBinaryTreeNode(value = 3),
                        RawBinaryTreeNode(value = 1)),
                        false),
                TestCase(listOf(
                        RawBinaryTreeNode(1, 2, value = 2),
                        RawBinaryTreeNode(value = 3),
                        RawBinaryTreeNode(value = 1)),
                        false),
                TestCase(listOf(
                        RawBinaryTreeNode(1, 2, value = 2),
                        RawBinaryTreeNode(value = 3),
                        RawBinaryTreeNode(value = 1)),
                        false),
                TestCase(listOf(
                        RawBinaryTreeNode(rightChildIndex = 1, value = 20),
                        RawBinaryTreeNode(leftChildIndex = 2, value = 30),
                        RawBinaryTreeNode(leftChildIndex = 3, value = 25),
                        RawBinaryTreeNode(value = 15)),
                        false),
                TestCase(listOf(
                        RawBinaryTreeNode(leftChildIndex = 1, value = 25),
                        RawBinaryTreeNode(rightChildIndex = 2, value = 10),
                        RawBinaryTreeNode(rightChildIndex = 3, value = 15),
                        RawBinaryTreeNode(rightChildIndex = 4, value = 20),
                        RawBinaryTreeNode(value = 30)),
                        false),
                TestCase(listOf(
                        RawBinaryTreeNode(leftChildIndex = 1, value = 25),
                        RawBinaryTreeNode(rightChildIndex = 2, value = 10),
                        RawBinaryTreeNode(rightChildIndex = 3, value = 15),
                        RawBinaryTreeNode(rightChildIndex = 4, value = 20),
                        RawBinaryTreeNode(value = 24)),
                        true),
                null
        ).filterNotNull()
    }
}