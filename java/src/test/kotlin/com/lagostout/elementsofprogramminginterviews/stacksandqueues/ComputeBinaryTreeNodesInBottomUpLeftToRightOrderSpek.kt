package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import com.lagostout.datastructures.BinaryTreeNode.Companion.buildBinaryTree
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ComputeBinaryTreeNodesInBottomUpLeftToRightOrderSpek : Spek({
    describe("computeBinaryTreeNodesInBottomUpLeftToRightOrder()") {
        val data = listOf(
                Pair(listOf(RBT(value = 1)), listOf(listOf(0))),
                Pair(listOf(RBT(null, 1, null, 1), RBT(value = 2)),
                        listOf(listOf(1), listOf(0))),
                Pair(listOf(RBT(1, 2, null, 1), RBT(value = 2), RBT(value = 3)),
                        listOf(listOf(1,2), listOf(0))),
                Pair(listOf(RBT(1, 2, null, 1),
                        RBT(leftChildIndex = 3, value = 2),
                        RBT(rightChildIndex = 4, value = 3),
                        RBT(value = 4), RBT(value = 5)),
                        listOf(listOf(3,4), listOf(1,2), listOf(0))),
                Pair(listOf(RBT(1, 2, null, 1),
                        RBT(leftChildIndex = 3, value = 2),
                        RBT(rightChildIndex = 4, value = 3),
                        RBT(leftChildIndex = 5, rightChildIndex = 6, value = 4),
                        RBT(leftChildIndex = 7, rightChildIndex = 8, value = 5),
                        RBT(value = 6), RBT(value = 7), RBT(value = 8), RBT(value = 9)),
                        listOf(listOf(5,6,7,8), listOf(3,4), listOf(1,2), listOf(0))),
                Pair(listOf(RBT(1, 2, null, 1),
                        RBT(leftChildIndex = 3, value = 2),
                        RBT(rightChildIndex = 4, value = 3),
                        RBT(leftChildIndex = 5, rightChildIndex = 6, value = 4),
                        RBT(leftChildIndex = 7, rightChildIndex = 8, value = 5),
                        RBT(value = 6), RBT(rightChildIndex = 9, value = 7),
                        RBT(value = 8), RBT(value = 9), RBT(value = 10)),
                        listOf(listOf(9), listOf(5,6,7,8), listOf(3,4), listOf(1,2), listOf(0))),
                Pair(listOf(RBT(1, 2, null, 1),
                        RBT(leftChildIndex = 3, value = 2),
                        RBT(rightChildIndex = 4, value = 3),
                        RBT(leftChildIndex = 5, rightChildIndex = 6, value = 4),
                        RBT(leftChildIndex = 7, rightChildIndex = 8, value = 5),
                        RBT(value = 6), RBT(rightChildIndex = 9, value = 7),
                        RBT(value = 8), RBT(value = 9),
                        RBT(leftChildIndex = 10, rightChildIndex = 11, value = 10),
                        RBT(value = 11), RBT(value = 12)),
                        listOf(listOf(10, 11), listOf(9), listOf(5,6,7,8), listOf(3,4),
                                listOf(1,2), listOf(0))),
                null
        ).filterNotNull().map { (rawNodes, expected) ->
            buildBinaryTree(rawNodes).let { (root, nodes) ->
                data(root!!, expected.map { it.map { nodes[it] } })
            }
        }.toTypedArray()
        on("root %s", with = *data) { root, expected ->
            it("returns $expected") {
                computeBinaryTreeNodesInBottomUpLeftToRightOrder(root).let {
                    assertThat(it).isEqualTo(expected)
                }
            }
        }
    }
})