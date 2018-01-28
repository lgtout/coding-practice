package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode.Companion.bbtr
import com.lagostout.datastructures.RawBinaryTreeNode.Companion.rbt
import com.lagostout.elementsofprogramminginterviews.binarysearchtrees.BuildMinimumHeightBST.buildMinimumHeightBST
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
//                data(emptyList<Alphabet>(), null as BinaryTreeNode<Alphabet>?),
//                data(listOf(A), bbtr(listOf(rbt(A)))),
//                data(listOf(A,B), bbtr(listOf(rbt(B, left = 1), rbt(A)))),
                // TODO More cases
                data(listOf(A,B,C), bbtr(listOf(rbt(B, left = 1, right = 2), rbt(A), rbt(C)))),
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
