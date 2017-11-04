package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.datastructures.BinaryTreeNode.Companion.buildBinaryTree
import com.lagostout.datastructures.RawBinaryTreeNode
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

typealias RBT = RawBinaryTreeNode<Int>

object ComputeBinaryTreeNodesInOrderOfIncreasingDepthSpek : Spek({
    describe("computeBinaryTreeNodesInOrderOfIncreasingDepth()") {
        val data = listOf(
                Pair(listOf(RBT(value = 1)), listOf(listOf(0))),
                null
        ).filterNotNull().map { (rawNodes, expected) ->
            buildBinaryTree(rawNodes).let { (root, nodes) ->
                data(root!!, expected.map { it.map { nodes[it] } })
            }
        }.toTypedArray()
        on("root %s", with = *data) { root, expected ->
            it("returns $expected") {
                assertThat(computeBinaryTreeNodesInOrderOfIncreasingDepth(root))
                        .isEqualTo(expected)
            }
        }
    }
})