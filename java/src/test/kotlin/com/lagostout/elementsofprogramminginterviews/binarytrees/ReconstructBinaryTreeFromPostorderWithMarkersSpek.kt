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
//                data(listOf(null, null, 'A'), expected = buildBinaryTree(
//                        listOf(RawBTNode(null, null, null, 'A')))),
//                data(listOf(null, null, 'B', null, 'A'), expected = buildBinaryTree(
//                        listOf(RawBTNode(1, null, null, 'A'), RawBTNode(null, null, 0, 'B')))),
//                data(listOf(null, null, 'B', null, null, 'C', 'A'), buildBinaryTree(
//                        listOf(RawBTNode(1, 2, null, 'A'), RawBTNode(parentIndex = 0, value = 'B'),
//                                RawBTNode(parentIndex = 0, value = 'C')))),
//                data(listOf(null, null, 'B', null, 'A'),
//                        expected = buildBinaryTree(listOf(
//                                RawBTNode(1, null, null, 'A'),
//                                RawBTNode(null, null, 0, 'B')))),
//                data(listOf(null, null, 'C', null, 'B', null, 'A'),
//                        expected = buildBinaryTree(listOf(
//                                RawBTNode(1, null, null, 'A'),
//                                RawBTNode(2, null, 0, 'B'),
//                                RawBTNode(null, null, 1, 'C')))),
                data(listOf(null, null, null, null, 'D', null, null, 'C', 'B', null, 'A'),
                        buildBinaryTree(listOf(RawBTNode(1, value = 'A'),
                                RawBTNode(2, 3, 0, value = 'B'), RawBTNode(null, null, 1, value = 'D'),
                                RawBTNode(parentIndex = 1, value = 'C')))),
//                data(listOf(null, null, null, 'E', 'D', null, null, 'C', 'B', null, 'A'),
//                        buildBinaryTree(listOf(RawBTNode(1, value = 'A'),
//                                RawBTNode(2, 3, 0, value = 'B'), RawBTNode(null, 4, 1, value = 'D'),
//                                RawBTNode(parentIndex = 1, value = 'C'),
//                                RawBTNode(parentIndex = 2, value = 'E')))),
//                data(listOf(null, null, 'D', null, null, 'E', 'B', null, null, 'C', 'A'),
//                        expected = buildBinaryTree(listOf(
//                                RawBTNode(1, 2, null, 'A'), RawBTNode(3, 4, 0, 'B'),
//                                RawBTNode(null, null, 0, 'C'), RawBTNode(null, null, 1, 'D'),
//                                RawBTNode(null, null, 1, 'E')))),
//                data(listOf(null, null, null, 'E', 'D', null, 'B', null, null, 'C', 'A'),
//                        expected = buildBinaryTree(listOf(
//                                RawBTNode(1, 2, null, 'A'), RawBTNode(3, null, 0, 'B'),
//                                RawBTNode(null, null, 0, 'C'), RawBTNode(null, 4, 1, 'D'),
//                                RawBTNode(null, null, 3, 'E')))),
//                data(listOf(null, null, 'C', null, 'E', null, 'D', null, 'B', null, 'A'),
//                        expected = buildBinaryTree(listOf(
//                                RawBTNode(1, null, null, 'A'), RawBTNode(2, null, 0, 'B'),
//                                RawBTNode(null, 3, 1, 'D'), RawBTNode(null, 4, 2, 'E'),
//                                RawBTNode(null, null, 3, 'C')))),
//                data(listOf(null, null, null, 'D', 'B', null, null, 'C', 'A'),
//                        expected = buildBinaryTree(listOf(
//                                RawBTNode(1, 2, null, 'A'), RawBTNode(null, 3, null, 'B'),
//                                RawBTNode(null, null, null, 'C'),
//                                RawBTNode(null, null, null, 'D')))),
//                data(listOf(null, null, null, null, 'C', 'B', 'A'),
//                        expected = buildBinaryTree(listOf(
//                                RawBTNode(null, 1, null, 'A'),
//                                RawBTNode(null, 2, null, 'B'),
//                                RawBTNode(null, null, null, 'C')))),
//                data(listOf(null, null, null, null, 'C', 'B', 'A'),
//                        expected = buildBinaryTree(listOf(
//                                RawBTNode(null, 1, null, 'A'),
//                                RawBTNode(null, 2, null, 'B'),
//                                RawBTNode(null, null, null, 'C')))),
//                data(listOf(null, null, null, null, 'E', null, 'D', null, 'C', 'B', 'A'),
//                        expected = buildBinaryTree(listOf(
//                                RawBTNode(null, 1, null, 'A'),
//                                RawBTNode(null, 2, null, 'B'),
//                                RawBTNode(null, 3, null, 'C'),
//                                RawBTNode(null, 4, null, 'D'),
//                                RawBTNode(null, null, null, 'E')))),
//                data(listOf(null, null, null, null, 'C', 'E', 'D', null, 'B', null, 'A'),
//                        expected = buildBinaryTree(listOf(
//                                RawBTNode(1, null, null, 'A'),
//                                RawBTNode(2, null, null, 'B'),
//                                RawBTNode(null, 3, null, 'D'),
//                                RawBTNode(null, 4, null, 'E'),
//                                RawBTNode(null, null, null, 'C')))),
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
