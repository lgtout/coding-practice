package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode.Companion.buildBinaryTreeRoot
import com.lagostout.datastructures.RawBinaryTreeNode.Companion.rbt
import com.lagostout.elementsofprogramminginterviews.binarytrees.ComputeExteriorOfBinaryTree.computeExteriorOfBinaryTree
import com.lagostout.kotlin.common.Alphabet.*
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ComputeExteriorOfBinaryTreeSpek : Spek({
    describe("computeExteriorOfBinaryTree") {
        val data = listOfNotNull(
                Pair(listOf(rbt(A)), listOf(A)),
                Pair(listOf(rbt(A, 1), rbt(B)), listOf(A, B)),
                Pair(listOf(rbt(A, right = 1), rbt(B)), listOf(A, B)),
                Pair(listOf(rbt(A, left = 1, right = 2), rbt(B), rbt(C)), listOf(A, B, C)),
                Pair(listOf(rbt(A, left = 1), rbt(B, left = 2), rbt(C)), listOf(A, B, C)),
                Pair(listOf(rbt(A, right = 1), rbt(B, right = 2), rbt(C)), listOf(A, C, B)),
                Pair(listOf(rbt(A, left = 1), rbt(B, right = 2), rbt(C)), listOf(A, B, C)),
                Pair(listOf(rbt(A, right = 1), rbt(B, left = 2), rbt(C)), listOf(A, C, B)),
                Pair(listOf(rbt(A, right = 1), rbt(B, left = 2, right = 3), rbt(C), rbt(D)), listOf(A, C, D, B)),
                null
        ).map {
            data(buildBinaryTreeRoot(it.first), it.second)
        }.toTypedArray()
        on("tree: %s", with = *data) { tree, expected ->
            it("returns $expected") {
                assertThat(computeExteriorOfBinaryTree(tree))
                        .isEqualTo(expected)
            }
        }
    }
})