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
        fun buildBinaryTree(rawTree: List<RBTNode>): BinaryTreeNode<Char> {
            return BinaryTreeNode.buildBinaryTree(rawTree).first!!
        }
        val data = listOfNotNull(
                data(listOf(null, null, 'A'), expected = buildBinaryTree(
                        listOf(RBTNode(null, null, null, 'A')))),
                data(listOf(null, null, 'B', null, 'A'), expected = buildBinaryTree(
                        listOf(RBTNode(1, null, null, 'A'), RBTNode(null, null, 0, 'B')))),
                data(listOf(null, null, 'B', null, null, 'C', 'A'), buildBinaryTree(
                        listOf(RBTNode(1, 2, null, 'A'), RBTNode(parentIndex = 0, value = 'B'),
                                RBTNode(parentIndex = 0, value = 'C')))),
                data(listOf(null, null, 'B', null, 'A'),
                        expected = buildBinaryTree(listOf(
                                RBTNode(1, null, null, 'A'),
                                RBTNode(null, null, 0, 'B')))),
                data(listOf(null, null, 'C', null, 'B', null, 'A'),
                        expected = buildBinaryTree(listOf(
                                RBTNode(1, null, null, 'A'),
                                RBTNode(2, null, 0, 'B'),
                                RBTNode(null, null, 1, 'C')))),
                data(listOf(null, null, 'D', null, null, 'C', 'B', null, 'A'),
                        buildBinaryTree(listOf(RBTNode(1, value = 'A'),
                                RBTNode(2, 3, 0, value = 'B'), RBTNode(null, null, 1, value = 'D'),
                                RBTNode(parentIndex = 1, value = 'C')))),
                data(listOf(null, null, null, 'E', 'D', null, null, 'C', 'B', null, 'A'),
                        buildBinaryTree(listOf(RBTNode(1, value = 'A'),
                                RBTNode(2, 3, 0, value = 'B'), RBTNode(null, 4, 1, value = 'D'),
                                RBTNode(parentIndex = 1, value = 'C'),
                                RBTNode(parentIndex = 2, value = 'E')))),
                data(listOf(null, null, 'D', null, null, 'E', 'B', null, null, 'C', 'A'),
                        expected = buildBinaryTree(listOf(
                                RBTNode(1, 2, null, 'A'), RBTNode(3, 4, 0, 'B'),
                                RBTNode(null, null, 0, 'C'), RBTNode(null, null, 1, 'D'),
                                RBTNode(null, null, 1, 'E')))),
                data(listOf(null, null, null, 'E', 'D', null, 'B', null, null, 'C', 'A'),
                        expected = buildBinaryTree(listOf(
                                RBTNode(1, 2, null, 'A'), RBTNode(3, null, 0, 'B'),
                                RBTNode(null, null, 0, 'C'), RBTNode(null, 4, 1, 'D'),
                                RBTNode(null, null, 3, 'E')))),
                data(listOf(null, null, null, null, 'C', 'E', 'D', null, 'B', null, 'A'),
                        expected = buildBinaryTree(listOf(
                                RBTNode(1, null, null, 'A'), RBTNode(2, null, 0, 'B'),
                                RBTNode(null, 3, 1, 'D'), RBTNode(null, 4, 2, 'E'),
                                RBTNode(null, null, 3, 'C')))),
                data(listOf(null, null, null, 'D', 'B', null, null, 'C', 'A'),
                        expected = buildBinaryTree(listOf(
                                RBTNode(1, 2, null, 'A'), RBTNode(null, 3, null, 'B'),
                                RBTNode(null, null, null, 'C'),
                                RBTNode(null, null, null, 'D')))),
                data(listOf(null, null, null, null, 'C', 'B', 'A'),
                        expected = buildBinaryTree(listOf(
                                RBTNode(null, 1, null, 'A'),
                                RBTNode(null, 2, null, 'B'),
                                RBTNode(null, null, null, 'C')))),
                data(listOf(null, null, null, null, 'C', 'B', 'A'),
                        expected = buildBinaryTree(listOf(
                                RBTNode(null, 1, null, 'A'),
                                RBTNode(null, 2, null, 'B'),
                                RBTNode(null, null, null, 'C')))),
                data(listOf(null, null, null, null, null, null, 'E', 'D', 'C', 'B', 'A'),
                        expected = buildBinaryTree(listOf(
                                RBTNode(null, 1, null, 'A'),
                                RBTNode(null, 2, null, 'B'),
                                RBTNode(null, 3, null, 'C'),
                                RBTNode(null, 4, null, 'D'),
                                RBTNode(null, null, null, 'E')))),
                data(listOf(null, null, null, null, 'C', 'E', 'D', null, 'B', null, 'A'),
                        expected = buildBinaryTree(listOf(
                                RBTNode(1, null, null, 'A'),
                                RBTNode(2, null, null, 'B'),
                                RBTNode(null, 3, null, 'D'),
                                RBTNode(null, 4, null, 'E'),
                                RBTNode(null, null, null, 'C')))),
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
