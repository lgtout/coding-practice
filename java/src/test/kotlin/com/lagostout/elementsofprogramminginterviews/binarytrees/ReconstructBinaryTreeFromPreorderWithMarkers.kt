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
        fun buildBinaryTree(rawTree: List<RBTNode>): BinaryTreeNode<Char> {
            return BinaryTreeNode.buildBinaryTree(rawTree).first!!
        }
        val data = listOfNotNull(
                data(listOf('A', null, null), expected = buildBinaryTree(
                        listOf(RBTNode(null, null, null, 'A')))),
                data(listOf('A', 'B' , null, null, null), expected = buildBinaryTree(
                        listOf(RBTNode(1, null, null, 'A'), RBTNode(null, null, 0, 'B')))),
                data(listOf('A', 'B', null, null, 'C', null, null), buildBinaryTree(
                        listOf(RBTNode(1, 2, null, 'A'), RBTNode(value = 'B'),
                                RBTNode(value = 'C')))),
                data(listOf('A', 'B', 'D', null, 'E', null, null, 'C', null, null), buildBinaryTree(
                        listOf(RBTNode(1, value = 'A'), RBTNode(2, 3, value = 'B'),
                                RBTNode(null, 4, value = 'D'), RBTNode(value = 'C'),
                                RBTNode(value = 'E')))),
                data(listOf('A', 'B', 'D', null, null, 'E', null, null, 'C', null, null),
                        expected = buildBinaryTree(listOf(
                                RBTNode(1, 2, null, 'A'), RBTNode(3, 4, 0, 'B'),
                                RBTNode(null, null, 0, 'C'), RBTNode(null, null, 1, 'D'),
                                RBTNode(null, null, 1, 'E')
                        ))),
                data(listOf('A', 'B', 'D', null, 'E', null, null, null, 'C', null, null),
                        expected = buildBinaryTree(listOf(
                                RBTNode(1, 2, null, 'A'), RBTNode(3, null, 0, 'B'),
                                RBTNode(null, null, 0, 'C'), RBTNode(null, 4, 1, 'D'),
                                RBTNode(null, null, 3, 'E')
                        ))),
                data(listOf('A','B','D', null, 'E', null, 'C', null, null, null, null),
                        expected = buildBinaryTree(listOf(
                                RBTNode(1, null, null, 'A'), RBTNode(2, null, 0, 'B'),
                                RBTNode(null, 3, 1, 'D'), RBTNode(null, 4, 2, 'E'),
                                RBTNode(null, null, 3, 'C')
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