package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.datastructures.BinaryTreeNode.Companion.buildBinaryTree
import com.lagostout.datastructures.RawBinaryTreeNode as RawNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class FindFirstInorderOccurrenceInBSTSpek : Spek({
    describe("findFirstInorderOccurrence") {
        testCases.forEach {
            (root, expectedNode, k) ->
            given("tree: $root, value to find: $k") {
                it("returns $expectedNode") {
                    assertEquals(expectedNode, findFirstInorderOccurrence(root, k))
                }
            }
        }
    }
}) {
    companion object {
        class TestCase<T : Comparable<T>>(rawNodes: List<RawNode<T>> = emptyList(),
                                          expectedNodeIndex: Int? = null,
                                          val k: Int = 0) {
            val root: BinaryTreeNode<T>?
            val expected: BinaryTreeNode<T>?
            init {
                val (root, expected) = buildBinaryTree(rawNodes).let { (root, nodes) ->
                    Pair(root, expectedNodeIndex?.let { nodes[it] })
                }
                this.root = root
                this.expected = expected
            }
            operator fun component1(): BinaryTreeNode<T>? = root
            operator fun component2(): BinaryTreeNode<T>? = expected
            operator fun component3() = k
        }
        val testCases = listOf(
                TestCase(),
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
                null
        ).filterNotNull()
    }
}