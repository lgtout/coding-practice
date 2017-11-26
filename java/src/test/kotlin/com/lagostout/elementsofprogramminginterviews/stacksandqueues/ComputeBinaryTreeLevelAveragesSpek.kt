package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import com.lagostout.datastructures.BinaryTreeNode.Companion.buildBinaryTree
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ComputeBinaryTreeLevelAveragesSpek : Spek({
    describe("computeBinaryTreeLevelAverages()") {
        val data = listOfNotNull(
                Pair(listOf(RBT(value = 1)), listOf(1.0)),
                Pair(listOf(RBT(null, 1, null, 1), RBT(value = 2)),
                        listOf(1.0, 2.0)),
                Pair(listOf(RBT(1, 2, null, 1), RBT(value = 2),
                        RBT(value = 3)), listOf(1.0, 2.5)),
                Pair(listOf(RBT(1, 2, null, 1),
                        RBT(leftChildIndex = 3, value = 2),
                        RBT(rightChildIndex = 4, value = 3),
                        RBT(value = 4), RBT(value = 5)),
                        listOf(1.0, 2.5, 4.5)),
                Pair(listOf(RBT(1, 2, null, 1),
                        RBT(leftChildIndex = 3, value = 2),
                        RBT(rightChildIndex = 4, value = 3),
                        RBT(leftChildIndex = 5, rightChildIndex = 6, value = 4),
                        RBT(leftChildIndex = 7, rightChildIndex = 8, value = 5),
                        RBT(value = 6), RBT(value = 7), RBT(value = 8), RBT(value = 9)),
                        listOf(1.0, 2.5, 4.5, 7.5)),
                Pair(listOf(RBT(1, 2, null, 1),
                        RBT(leftChildIndex = 3, value = 2),
                        RBT(rightChildIndex = 4, value = 3),
                        RBT(leftChildIndex = 5, rightChildIndex = 6, value = 4),
                        RBT(leftChildIndex = 7, rightChildIndex = 8, value = 5),
                        RBT(value = 6), RBT(rightChildIndex = 9, value = 7),
                        RBT(value = 8), RBT(value = 9), RBT(value = 10)),
                        listOf(1.0, 2.5, 4.5, 7.5, 10.0)),
                Pair(listOf(RBT(1, 2, null, 1),
                        RBT(leftChildIndex = 3, value = 2),
                        RBT(rightChildIndex = 4, value = 3),
                        RBT(leftChildIndex = 5, rightChildIndex = 6, value = 4),
                        RBT(leftChildIndex = 7, rightChildIndex = 8, value = 5),
                        RBT(value = 6), RBT(rightChildIndex = 9, value = 7),
                        RBT(value = 8), RBT(value = 9),
                        RBT(leftChildIndex = 10, rightChildIndex = 11, value = 10),
                        RBT(value = 11), RBT(value = 12)),
                        listOf(1.0, 2.5, 4.5, 7.5, 10.0, 11.5)),
                null
        ).map { (rawNodes, expected) ->
            buildBinaryTree(rawNodes).let { (root, nodes) ->
                data(root!!, expected)
            }
        }.toTypedArray()
        on("root %s", with = *data) { root, expected ->
            it("returns $expected") {
                computeBinaryTreeLevelAverages(root).let {
                    assertThat(it).isEqualTo(expected)
                }
            }
        }
    }
})
