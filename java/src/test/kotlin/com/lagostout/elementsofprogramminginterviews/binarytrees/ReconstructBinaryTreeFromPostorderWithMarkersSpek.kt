package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode.Companion.buildBinaryTreeRoot
import com.lagostout.datastructures.RawBinaryTreeNode.Companion.rbt
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ReconstructBinaryTreeFromPostorderWithMarkersSpek : Spek({
    describe("reconstructBinaryTreeFromPostorderWithMarkers()") {
        val data = listOfNotNull(
                data(listOf(null, null, 'A'), expected = buildBinaryTreeRoot(
                        listOf(rbt(null, null, null, 'A')))),
                data(listOf(null, null, 'B', null, 'A'), expected = buildBinaryTreeRoot(
                        listOf(rbt(1, null, null, 'A'), rbt(null, null, 0, 'B')))),
                data(listOf(null, null, 'B', null, null, 'C', 'A'), buildBinaryTreeRoot(
                        listOf(rbt(1, 2, null, 'A'), rbt(null, parent = 0, value = 'B'),
                                rbt(null, parent = 0, value = 'C')))),
                data(listOf(null, null, 'B', null, 'A'),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(1, null, null, 'A'),
                                rbt(null, null, 0, 'B')))),
                data(listOf(null, null, 'C', null, 'B', null, 'A'),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(1, null, null, 'A'),
                                rbt(2, null, 0, 'B'),
                                rbt(null, null, 1, 'C')))),
                data(listOf(null, null, 'D', null, null, 'C', 'B', null, 'A'),
                        buildBinaryTreeRoot(listOf(rbt(1, value = 'A'),
                                rbt(2, 3, 0, value = 'B'), rbt(null, null, 1, value = 'D'),
                                rbt(null, parent = 1, value = 'C')))),
                data(listOf(null, null, null, 'E', 'D', null, null, 'C', 'B', null, 'A'),
                        buildBinaryTreeRoot(listOf(rbt(1, value = 'A'),
                                rbt(2, 3, 0, value = 'B'), rbt(null, 4, 1, value = 'D'),
                                rbt(null, parent = 1, value = 'C'),
                                rbt(null, parent = 2, value = 'E')))),
                data(listOf(null, null, 'D', null, null, 'E', 'B', null, null, 'C', 'A'),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(1, 2, null, 'A'), rbt(3, 4, 0, 'B'),
                                rbt(null, null, 0, 'C'), rbt(null, null, 1, 'D'),
                                rbt(null, null, 1, 'E')))),
                data(listOf(null, null, null, 'E', 'D', null, 'B', null, null, 'C', 'A'),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(1, 2, null, 'A'), rbt(3, null, 0, 'B'),
                                rbt(null, null, 0, 'C'), rbt(null, 4, 1, 'D'),
                                rbt(null, null, 3, 'E')))),
                data(listOf(null, null, null, null, 'C', 'E', 'D', null, 'B', null, 'A'),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(1, null, null, 'A'), rbt(2, null, 0, 'B'),
                                rbt(null, 3, 1, 'D'), rbt(null, 4, 2, 'E'),
                                rbt(null, null, 3, 'C')))),
                data(listOf(null, null, null, 'D', 'B', null, null, 'C', 'A'),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(1, 2, null, 'A'), rbt(null, 3, null, 'B'),
                                rbt(null, null, null, 'C'),
                                rbt(null, null, null, 'D')))),
                data(listOf(null, null, null, null, 'C', 'B', 'A'),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(null, 1, null, 'A'),
                                rbt(null, 2, null, 'B'),
                                rbt(null, null, null, 'C')))),
                data(listOf(null, null, null, null, 'C', 'B', 'A'),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(null, 1, null, 'A'),
                                rbt(null, 2, null, 'B'),
                                rbt(null, null, null, 'C')))),
                data(listOf(null, null, null, null, null, null, 'E', 'D', 'C', 'B', 'A'),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(null, 1, null, 'A'),
                                rbt(null, 2, null, 'B'),
                                rbt(null, 3, null, 'C'),
                                rbt(null, 4, null, 'D'),
                                rbt(null, null, null, 'E')))),
                data(listOf(null, null, null, null, 'C', 'E', 'D', null, 'B', null, 'A'),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(1, null, null, 'A'),
                                rbt(2, null, null, 'B'),
                                rbt(null, 3, null, 'D'),
                                rbt(null, 4, null, 'E'),
                                rbt(null, null, null, 'C')))),
                null
        ).toTypedArray()
        on("preorder: %s", with = *data) { preorder, expected ->
            it("returns $expected") {
                val tree = reconstructBinaryTreeFromPostorderWithMarkers(preorder)
                assertThat(tree).isEqualTo(expected)
            }
        }
    }
})
