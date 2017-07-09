package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.BinaryTreeNode
import com.lagostout.common.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class FindFirstKeyGreaterThanValueInBstSpek : Spek({
    describe("findFirstKeyGreaterThanValueInBst") {
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
                    BinaryTreeNode.buildBinaryTree(rawNodes).left
            operator fun component4() = root
        }
        private val testCases = listOf(
                TestCase(listOf(RawBinaryTreeNode(value = 10)), 0, 10),
                TestCase(listOf(RawBinaryTreeNode(value = 10)), 10, null),
                TestCase(listOf(RawBinaryTreeNode(value = 10)), 15, null),
                // TODO More cases
                null
        ).filterNotNull()
    }
}
