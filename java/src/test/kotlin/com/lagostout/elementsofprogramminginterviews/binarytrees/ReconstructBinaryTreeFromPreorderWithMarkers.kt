package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ReconstructBinaryTreeFromPreorderWithMarkers : Spek({
    describe("reconstructBinaryTreeFromPreorderWithMarkers()") {
        fun buildBinaryTree(rawTree: List<RawBTNode>): BinaryTreeNode<Char> {
            return BinaryTreeNode.buildBinaryTree(rawTree).first!!
        }
        val data = listOfNotNull(
                data(listOf('A', null, null), expected = buildBinaryTree(
                        listOf(RawBTNode(null, null, null, 'A')))),
                data(listOf('A', 'B' , null, null, null), expected = buildBinaryTree(
                        listOf(RawBTNode(1, null, null, 'A'), RawBTNode(null, null, 0, 'B')))),
                data(listOf('A', 'B', null, null, 'C', null, null), buildBinaryTree(
                        listOf(RawBTNode(1, 2, null, 'A'), RawBTNode(value = 'B'),
                                RawBTNode(value = 'C')))),
                data(listOf('A', 'B', 'D', null, 'E', null, null, 'C', null, null), buildBinaryTree(
                        listOf(RawBTNode(1, value = 'A'), RawBTNode(2, 3, value = 'B'),
                                RawBTNode(null, 4, value = 'D'), RawBTNode(value = 'C'),
                                RawBTNode(value = 'E')))),
                data(listOf('A', 'B', 'D', null, null, 'E', null, null, 'C', null, null),
                        expected = buildBinaryTree(listOf(
                                RawBTNode(1, 2, null, 'A'), RawBTNode(3, 4, 0, 'B'),
                                RawBTNode(null, null, 0, 'C'), RawBTNode(null, null, 1, 'D'),
                                RawBTNode(null, null, 1, 'E')
                        ))),
                data(listOf('A', 'B', 'D', null, 'E', null, null, null, 'C', null, null),
                        expected = buildBinaryTree(listOf(
                                RawBTNode(1, 2, null, 'A'), RawBTNode(3, null, 0, 'B'),
                                RawBTNode(null, null, 0, 'C'), RawBTNode(null, 4, 1, 'D'),
                                RawBTNode(null, null, 3, 'E')
                        ))),
                data(listOf('A','B','D', null, 'E', null, 'C', null, null, null, null),
                        expected = buildBinaryTree(listOf(
                                RawBTNode(1, null, null, 'A'), RawBTNode(2, null, 0, 'B'),
                                RawBTNode(null, 3, 1, 'D'), RawBTNode(null, 4, 2, 'E'),
                                RawBTNode(null, null, 3, 'C')
                        ))),
                null
        ).toTypedArray()
        on("inorder: %s, preorder: %s", with = *data) {
            preorder, expected ->
            it("returns $expected") {
                val tree = reconstructBinaryTreeFromPreorderWithMarkers(preorder)
                assertThat(tree).isEqualTo(expected)
            }
        }
    }
})