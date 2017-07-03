package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.BinaryTreeNode
import com.lagostout.common.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given

class TestIfBinaryTreeSatisfiesBstPropertySpek : Spek({
    describe("satisfiesBstProperty") {
        testCases.forEach {
            (_, root) ->
            given("binary tree $root") {

            }
        }
    }
}) {
    companion object {
        data class TestCase(val rawNodes: List<RawBinaryTreeNode<Int>> = listOf()) {
            var root: BinaryTreeNode<Int>? = BinaryTreeNode.buildBinaryTree(rawNodes).left
            operator fun component2() = root
        }
        val testCases = listOf(
                TestCase(),
                null
        ).filterNotNull()
    }
}