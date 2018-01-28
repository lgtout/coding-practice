package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.datastructures.BinaryTreeNode.Companion.bbtr
import com.lagostout.datastructures.RawBinaryTreeNode.Companion.rbt
import com.lagostout.elementsofprogramminginterviews.binarysearchtrees.BuildMinimumHeightBST.buildMinimumHeightBST
import com.lagostout.kotlin.common.Alphabet
import com.lagostout.kotlin.common.Alphabet.*
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object BuildMinimumHeightBSTSpek : Spek({
    describe("buildMinimumHeightBST") {
        val data = listOfNotNull(
                data(emptyList(), null as BinaryTreeNode<Alphabet>?),
                data(listOf(A), bbtr(listOf(rbt(A)))),
                data(listOf(A,B), bbtr(listOf(rbt(B, left = 1), rbt(A)))),
                data(listOf(A,B,C), bbtr(listOf(rbt(B, left = 1, right = 2),
                        rbt(A), rbt(C)))),
                data(listOf(A,B,C,D), bbtr(listOf(rbt(C, left = 1, right = 3),
                        rbt(B, left = 2), rbt(A), rbt(D)))),
                data(listOf(A,B,C,D,E), bbtr(listOf(rbt(C, left = 1, right = 3),
                        rbt(B, left = 2), rbt(A), rbt(E, left = 4), rbt(D)))),
                data(listOf(A,B,C,D,E,F), bbtr(listOf(rbt(D, left = 1, right = 4),
                        rbt(B, left = 2, right = 3), rbt(A), rbt(C),
                        rbt(F, left = 5), rbt(E)))),
                data(listOf(A,B,C,D,E,F,G), bbtr(listOf(rbt(D, left = 1, right = 4),
                        rbt(B, left = 2, right = 3), rbt(A), rbt(C),
                        rbt(F, left = 5, right = 6), rbt(E), rbt(G)))),
                null
        ).toTypedArray()
        on("list: %s", with = *data) { list, expected ->
            it("returns $expected") {
                assertThat(buildMinimumHeightBST(list))
                        .isEqualTo(expected)
            }
        }
    }
})
