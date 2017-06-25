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

    describe("treeContainsRootToLeafPath") {
        listOf(
                TestCase(0, listOf(RawBinaryTreeNode(value = 0)), true),
                TestCase(1, listOf(RawBinaryTreeNode(value = 0)),false),
                TestCase(1, listOf(
                        RawBinaryTreeNode(rightChildIndex = 1, value = 0),
                        RawBinaryTreeNode(value = 1)), true)
        ).forEach {
            given("a binary tree ${it.tree}") {
                it("returns ${it.expected}") {
                    assertEquals(it.expected, treeContainsRootToLeafPath(it.tree, it.sum))
                }
            }
        }
    }

})
