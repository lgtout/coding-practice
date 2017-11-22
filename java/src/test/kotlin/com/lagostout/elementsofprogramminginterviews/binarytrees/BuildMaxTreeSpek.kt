package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.datastructures.BinaryTreeNode.Companion.buildBinaryTree
import com.lagostout.elementsofprogramminginterviews.stacksandqueues.RBT
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object BuildMaxTreeSpek : Spek({
    describe("buildMaxTree()") {
        val data = listOf(
                Pair(listOf(1), listOf(
                        RBT(value=1))),
                Pair(listOf(1,2), listOf(
                        RBT(1, value=2), RBT(value=1))),
                Pair(listOf(1,2,3), listOf(
                        RBT(1, value=3), RBT(2, value=2),
                        RBT(value=1))),
                Pair(listOf(2,1), listOf(
                        RBT(rightChildIndex = 1, value=2), RBT(value=1))),
                Pair(listOf(3,2,1), listOf(
                        RBT(rightChildIndex = 1, value=3),
                        RBT(rightChildIndex = 2, value=2), RBT(value=1))),
                // 1
                Pair(listOf(1,2,5,3,2,1), listOf(
                        RBT(1, 2, value=5), RBT(3, value=2),
                        RBT(rightChildIndex = 4, value=3), RBT(value=1),
                        RBT(rightChildIndex = 5, value=2),
                        RBT(value=1))),
                // 2
                Pair(listOf(5,2,0,5,2,6,0), listOf(
                        RBT(1, 2, value=6),
                        RBT(3, 4, value=5), RBT(value=0),
                        RBT(rightChildIndex = 5, value=5), RBT(value=2),
                        RBT(rightChildIndex = 6, value=2), RBT(value=0))),
                // 3
                Pair(listOf(3,1,0,5,7,4,5,6), listOf(
                        RBT(1, 2, value=7),
                        RBT(3, value=5), RBT(4, value=6),
                        RBT(rightChildIndex = 5, value=3), RBT(6, value=5),
                        RBT(rightChildIndex = 7, value=1), RBT(value=4),
                        RBT(value=0))),
                // 4
                Pair(listOf(3,1,0,2,7,4,5,6), listOf(
                        RBT(1, 2, value=7),
                        RBT(rightChildIndex = 3, value=3), RBT(4, value=6),
                        RBT(5, value=2), RBT(6, value=5),
                        RBT(rightChildIndex = 7, value=1), RBT(value=4),
                        RBT(value=0))),
                // 5
                Pair(listOf(1,0,2,5,2,3,4), listOf(
                        RBT(1, 2, value=5),
                        RBT(3, value=2), RBT(4, value=4),
                        RBT(rightChildIndex = 5, value=1), RBT(6, value=3),
                        RBT(value=0), RBT(value=2))),
                null
        ).filterNotNull().map {
            data(it.first, buildBinaryTree(it.second).first!!)
        }.toTypedArray()
        on("integers: %s", with = *data) {
            integers: List<Int>, expected: BinaryTreeNode<Int> ->
            it("returns $expected") {
                assertThat(buildMaxTree(integers))
                        .isEqualTo(expected)
            }
        }
    }
})