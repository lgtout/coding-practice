package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.assertj.core.api.Assertions.*


object ReconstructBinaryTreeFromPreorderTraversalSpek : Spek({
    describe("reconstructBinaryTreeFromPreorderTraversal") {
        fun buildBinaryTree(rawTree: List<RawBTNode>): BinaryTreeNode<Char> {
            return BinaryTreeNode.buildBinaryTree(rawTree).first!!
        }
        val data = listOf(
                data(listOf('A'), listOf('A'), expected = buildBinaryTree(
                        listOf(RawBTNode(null, null, null, 'A')))),
                data(listOf('B','A'), listOf('A','B'), expected = buildBinaryTree(
                        listOf(RawBTNode(1, null, null, 'A'), RawBTNode(null, null, 0, 'B')))),
                data(listOf('B','A','C'), listOf('A','B','C'), buildBinaryTree(
                        listOf(RawBTNode(1, 2, null, 'A'), RawBTNode(value = 'B'), RawBTNode(value = 'C')))),
                data(listOf('D','E','B','C','A'), listOf('A','B','D','E','C'), buildBinaryTree(
                        listOf(RawBTNode(1, value = 'A'), RawBTNode(2, 3, value = 'B'),
                                RawBTNode(null, 4, value = 'D'), RawBTNode(value = 'C'),
                                RawBTNode(value = 'E')))),
                data(listOf('D','B','E','A','C'), listOf('A','B','D','E','C'),
                        expected = buildBinaryTree(listOf(
                                RawBTNode(1, 2, null, 'A'), RawBTNode(3, 4, 0, 'B'),
                                RawBTNode(null, null, 0, 'C'), RawBTNode(null, null, 1, 'D'),
                                RawBTNode(null, null, 1, 'E')
                        ))),
                data(listOf('D','E','B','A','C'), listOf('A','B','D','E','C'),
                        expected = buildBinaryTree(listOf(
                                RawBTNode(1, 2, null, 'A'), RawBTNode(3, null, 0, 'B'),
                                RawBTNode(null, null, 0, 'C'), RawBTNode(null, 4, 1, 'D'),
                                RawBTNode(null, null, 3, 'E')
                        ))),
                data(listOf('D','E','C','B','A'), listOf('A','B','D','E','C'),
                        expected = buildBinaryTree(listOf(
                                RawBTNode(1, null, null, 'A'), RawBTNode(2, null, 0, 'B'),
                                RawBTNode(null, 3, 1, 'D'), RawBTNode(null, 4, 2, 'E'),
                                RawBTNode(null, null, 3, 'C')
                        ))),
                null
        ).filterNotNull().toTypedArray()
        on("inorder: %s, preorder: %s", with = *data) {
            inorder, preorder, expected ->
            it("returns $expected") {
                val tree = reconstructBinaryTreeFromPreorderTraversal(inorder, preorder)
                assertThat(tree).isEqualTo(expected)
            }
        }
    }
})