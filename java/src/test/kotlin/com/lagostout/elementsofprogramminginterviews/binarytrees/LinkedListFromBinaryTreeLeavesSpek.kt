package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode.Companion.buildBinaryTree
import com.lagostout.datastructures.RawBinaryTreeNode.Companion.rbt
import com.lagostout.kotlin.common.Alphabet.*
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object LinkedListFromBinaryTreeLeavesSpek : Spek({
    describe("createLinkedListFromBinaryTreeLeaves") {
        val data = listOfNotNull(
                Pair(listOf(rbt(A)), listOf(rbt(A))),
                Pair(listOf(rbt(A, left = 1), rbt(B)), listOf(rbt(B))),
                Pair(listOf(rbt(A, right = 1), rbt(B)), listOf(rbt(B))),
                Pair(listOf(rbt(A, right = 1, left = 2), rbt(B), rbt(C)),
                        listOf(rbt(B, right = 1), rbt(C))),
                null
        ).map {
            data(buildBinaryTree(it.first).first!!,
                    buildBinaryTree(it.second).first!!)
        }.toTypedArray()
        on("tree: %s", with = *data) { tree, expected ->
            it("returns $expected") {
                // TODO Does this equality check do what's expected?
                assertThat(createLinkedListFromBinaryTreeLeaves(tree))
                        .isEqualTo(expected)
            }
        }
    }
})