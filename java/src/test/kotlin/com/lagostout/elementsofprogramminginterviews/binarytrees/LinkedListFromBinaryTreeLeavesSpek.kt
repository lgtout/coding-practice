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

object LinkedListFromBinaryTreeLeavesSpek : Spek({ describe("createLinkedListFromBinaryTreeLeaves") {
        val data = listOfNotNull(
                Pair(listOf(rbt(A)), listOf(0)),
                Pair(listOf(rbt(A, left = 1), rbt(B)), listOf(1)),
                Pair(listOf(rbt(A, right = 1), rbt(B)), listOf(1)),
                Pair(listOf(rbt(A, left = 1, right = 2), rbt(B), rbt(C)),
                        listOf(1,2)),
                Pair(listOf(rbt(A, left = 1, right = 2),
                        rbt(B, left = 3, right = 4),
                        rbt(C), rbt(D), rbt(E)),
                        listOf(3,4,2)),
                Pair(listOf(rbt(A, left = 1, right = 2),
                        rbt(B, right = 3),
                        rbt(C, right = 4), rbt(D), rbt(E)),
                        listOf(3,4)),
                null
        ).map { (rawNodes, expectedIndices) ->
            buildBinaryTree(rawNodes).run {
                data(first!!, expectedIndices.map { second[it] })
            }
        }.toTypedArray()
        on("tree: %s", with = *data) { tree, expected ->
            it("returns $expected") {
                println(tree)
                assertThat(createLinkedListFromBinaryTreeLeaves(tree).let {
                    var node = it
                    val list = mutableListOf(it)
                    println(it)
                    while (true) {
                        node.right?.let {
                            list.add(it)
                            node = it
                        } ?: break
                    }
                    list
                }).isEqualTo(expected)
            }
        }
    }
})