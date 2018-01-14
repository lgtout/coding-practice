package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode.Companion.buildBinaryTreeRoot
import com.lagostout.datastructures.RawBinaryTreeNode.Companion.rbt
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ReconstructBinaryTreeFromPreorderWithMarkers : Spek({
    describe("reconstructBinaryTreeFromPreorderWithMarkers()") {
        val data = listOfNotNull(
                data(listOf('A', null, null), expected = buildBinaryTreeRoot(
                        listOf(rbt(null, null, null, 'A')))),
                data(listOf('A', 'B' , null, null, null), expected = buildBinaryTreeRoot(
                        listOf(rbt(1, null, null, 'A'), rbt(null, null, 0, 'B')))),
                data(listOf('A', 'B', null, null, 'C', null, null), buildBinaryTreeRoot(
                        listOf(rbt(1, 2, null, 'A'), rbt(null, value = 'B'),
                                rbt(null, value = 'C')))),
                data(listOf('A', 'B', 'D', null, 'E', null, null, 'C', null, null), buildBinaryTreeRoot(
                        listOf(rbt(1, value = 'A'), rbt(2, 3, value = 'B'),
                                rbt(null, 4, value = 'D'), rbt(null, value = 'C'),
                                rbt(null, value = 'E')))),
                data(listOf('A', 'B', 'D', null, null, 'E', null, null, 'C', null, null),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(1, 2, null, 'A'), rbt(3, 4, 0, 'B'),
                                rbt(null, null, 0, 'C'), rbt(null, null, 1, 'D'),
                                rbt(null, null, 1, 'E')
                        ))),
                data(listOf('A', 'B', 'D', null, 'E', null, null, null, 'C', null, null),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(1, 2, null, 'A'), rbt(3, null, 0, 'B'),
                                rbt(null, null, 0, 'C'), rbt(null, 4, 1, 'D'),
                                rbt(null, null, 3, 'E')
                        ))),
                data(listOf('A','B','D', null, 'E', null, 'C', null, null, null, null),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(1, null, null, 'A'), rbt(2, null, 0, 'B'),
                                rbt(null, 3, 1, 'D'), rbt(null, 4, 2, 'E'),
                                rbt(null, null, 3, 'C')
                        ))),
                null
        ).toTypedArray()
        on("preorder: %s", with = *data) { preorder, expected ->
            it("returns $expected") {
                val tree = reconstructBinaryTreeFromPreorderWithMarkers(preorder)
                assertThat(tree).isEqualTo(expected)
            }
        }
    }
})