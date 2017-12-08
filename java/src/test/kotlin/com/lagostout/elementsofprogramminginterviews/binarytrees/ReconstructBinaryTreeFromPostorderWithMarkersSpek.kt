package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ReconstructBinaryTreeFromPostorderWithMarkersSpek : Spek({
    describe("reconstructBinaryTreeFromPostorderWithMarkers()") {
        fun buildBinaryTree(rawTree: List<RawBTNode>): BinaryTreeNode<Char> {
            return BinaryTreeNode.buildBinaryTree(rawTree).first!!
        }
        val data = listOfNotNull(
                data(listOf(null, null, 'A'), expected = buildBinaryTree(
                        listOf(RawBTNode(null, null, null, 'A')))),
                data(listOf(null, null, 'B', null, 'A'), expected = buildBinaryTree(
                        listOf(RawBTNode(1, null, null, 'A'), RawBTNode(null, null, 0, 'B')))),
                data(listOf(null, null, 'B', null, null, 'C', 'A'), buildBinaryTree(
                        listOf(RawBTNode(1, 2, null, 'A'), RawBTNode(value = 'B'),
                                RawBTNode(value = 'C')))),
                data(listOf(null, null, null, 'E', 'D', null, null, 'C', 'B', null, 'A'),
                        buildBinaryTree(listOf(RawBTNode(1, value = 'A'),
                                RawBTNode(2, 3, value = 'B'), RawBTNode(null, 4, value = 'D'),
                                RawBTNode(value = 'C'), RawBTNode(value = 'E')))),
                data(listOf(null, null, 'D', null, null, 'E', 'B', null, null, 'C', 'A'),
                        expected = buildBinaryTree(listOf(
                                RawBTNode(1, 2, null, 'A'), RawBTNode(3, 4, 0, 'B'),
                                RawBTNode(null, null, 0, 'C'), RawBTNode(null, null, 1, 'D'),
                                RawBTNode(null, null, 1, 'E')))),
                data(listOf(null, null, null, 'E', 'D', null, 'B', null, null, 'C', 'A'),
                        expected = buildBinaryTree(listOf(
                                RawBTNode(1, 2, null, 'A'), RawBTNode(3, null, 0, 'B'),
                                RawBTNode(null, null, 0, 'C'), RawBTNode(null, 4, 1, 'D'),
                                RawBTNode(null, null, 3, 'E')))),
                data(listOf(null, null, 'C', null, 'E', null, 'D', null, 'B', null, 'A'),
                        expected = buildBinaryTree(listOf(
                                RawBTNode(1, null, null, 'A'), RawBTNode(2, null, 0, 'B'),
                                RawBTNode(null, 3, 1, 'D'), RawBTNode(null, 4, 2, 'E'),
                                RawBTNode(null, null, 3, 'C')))),
                // TODO Add more tests - ones without checkmark in notes.
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
