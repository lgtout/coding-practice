package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.datastructures.BinaryTreeNode.Companion.buildBinaryTree
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals
import com.lagostout.datastructures.RawBinaryTreeNode as RawNode

class FindFirstInorderOccurrenceInBSTSpek : Spek({
    describe("findFirstInorderOccurrence") {
        testCases.forEachIndexed {
            index, (root, expectedNode, k) ->
            given("#${index + 1}: tree: $root, value to find: $k") {
                it("returns $expectedNode") {
                    assertEquals(expectedNode,findFirstInorderOccurrence(root, k))
                }
            }
        }
    }
}) {
    companion object {
        class TestCase<T : Comparable<T>>(rawNodes: List<RawNode<T>> = emptyList(),
                                          expectedNodeIndex: Int? = null,
                                          val k: Int = 0) {
            val root: BinaryTreeNode<T>
            val expected: BinaryTreeNode<T>?
            init {
                val (root, expected) = buildBinaryTree(rawNodes).let { (root, nodes) ->
                    Pair(root, expectedNodeIndex?.let { nodes[it] })
                }
                this.root = root!!
                this.expected = expected
            }
            operator fun component1(): BinaryTreeNode<T> = root
            operator fun component2(): BinaryTreeNode<T>? = expected
            operator fun component3() = k
        }
        val testCases = listOf(
                TestCase(listOf(
                        RawNode(1, 2, value = 10),
                        RawNode(value = 10),
                        RawNode(value = 15)), 1, 10),
                TestCase(listOf(
                        RawNode(1, 2, value = 10),
                        RawNode(value = 5),
                        RawNode(value = 15)), 0, 10),
                TestCase(listOf(
                        RawNode(1, 2, value = 10),
                        RawNode(value = 10),
                        RawNode(value = 10)), 1, 10),
                TestCase(listOf(
                        RawNode(1, 2, value = 10),
                        RawNode(value = 5),
                        RawNode(value = 10)), 0, 10),
                TestCase(listOf(
                        RawNode(1, 2, value = 10),
                        RawNode(rightChildIndex = 3, value = 5),
                        RawNode(value = 10),
                        RawNode(value = 10)), 3, 10),
                TestCase(listOf(
                        RawNode(1, 2, value = 10),
                        RawNode(rightChildIndex = 3, value = 5),
                        RawNode(value = 10),
                        RawNode(leftChildIndex = 4, value = 10),
                        RawNode(value = 10)), 4, 10),
                TestCase(listOf(
                        RawNode(1, 2, value = 10),
                        RawNode(rightChildIndex = 3, value = 5),
                        RawNode(value = 10),
                        RawNode(leftChildIndex = 4, value = 10),
                        RawNode(rightChildIndex = 5, value = 10),
                        RawNode(leftChildIndex = 6, value = 20),
                        RawNode(value = 10)), 4, 10),
                TestCase(listOf(
                        RawNode(leftChildIndex = 1, rightChildIndex = 2, value = 10),
                        RawNode(value = 5),
                        RawNode(rightChildIndex = 3, value = 15),
                        RawNode(leftChildIndex = 4, value = 30),
                        RawNode(value = 20)), 4, 20),
                TestCase(listOf(
                        RawNode(leftChildIndex = 1, rightChildIndex = 2, value = 10),
                        RawNode(value = 5),
                        RawNode(rightChildIndex = 3, value = 15),
                        RawNode(leftChildIndex = 4, value = 30),
                        RawNode(value = 18)), null, 20),
                TestCase(listOf(
                        RawNode(leftChildIndex = 1, rightChildIndex = 2, value = 10),
                        RawNode(value = 5),
                        RawNode(rightChildIndex = 3, value = 15),
                        RawNode(leftChildIndex = 4, value = 30),
                        RawNode(value = 18)), null, 12),
                TestCase(listOf(
                        RawNode(leftChildIndex = 1, rightChildIndex = 2, value = 10),
                        RawNode(value = 5),
                        RawNode(value = 15)), null, 2),
                null
        ).filterNotNull()
    }
}