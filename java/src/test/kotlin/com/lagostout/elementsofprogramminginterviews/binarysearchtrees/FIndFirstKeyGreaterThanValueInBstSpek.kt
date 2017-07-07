package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.BinaryTreeNode
import com.lagostout.common.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

class FindFirstKeyGreaterThanValueInBstSpek : Spek({
    describe("") {
        testCases.forEach {
            given("") {
                it("") {

                }
            }

        }
    }
}){
    private companion object {
        private data class TestCase(
                private val rawNodes: List<RawBinaryTreeNode<Int>>,
                val value: Int, val expected: Int) {
            val root = BinaryTreeNode.buildBinaryTree(rawNodes).left
        }
        private val testCases = listOf(
                TestCase(listOf(), 0, 0),
                null
        ).filterNotNull()
    }
}
