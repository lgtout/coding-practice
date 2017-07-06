package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import com.lagostout.common.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

class KthNodeInInOrderTraversalSpek : Spek({
    describe("") {
        testCases.forEach {
            given("") {
                it("") {

                }
            }
        }
    }
}) {
    private companion object {
        private data class TestCase(val rawNodes: List<RawBinaryTreeNode<Int>>,
                                    val expected: BinaryTreeNode<Int>?)
        private val testCases: List<TestCase> = listOf()
    }
}