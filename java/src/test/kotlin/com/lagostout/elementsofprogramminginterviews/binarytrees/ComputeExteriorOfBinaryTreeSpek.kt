package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode.Companion.buildBinaryTreeRoot
import com.lagostout.datastructures.RawBinaryTreeNode.Companion.rbt
import com.lagostout.elementsofprogramminginterviews.binarytrees.ComputeExteriorOfBinaryTree.computeExteriorOfBinaryTree
import com.lagostout.kotlin.common.Alphabet.A
import com.lagostout.kotlin.common.Alphabet.B
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