package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.datastructures.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.assertj.core.api.Assertions.*

typealias RawNode = RawBinaryTreeNode<Char>

object ReconstructBinaryTreeFromPreorderTraversalSpek : Spek({
    describe("reconstructBinaryTreeFromPreorderTraversal") {
        fun buildBinaryTree(rawTree: List<RawNode>): BinaryTreeNode<Char> {
            return BinaryTreeNode.buildBinaryTree(rawTree).first!!
        }
        val data = listOf(
                data(listOf('A'), listOf('A'), expected = buildBinaryTree(
                        listOf(RawNode(null, null, null, 'A')))),
                data(listOf('B','A'), listOf('A','B'), expected = buildBinaryTree(
                        listOf(RawNode(1, null, null, 'A'), RawNode(null, null, 0, 'B')))),
                data(listOf('B','A','C'), listOf('A','B','C'), buildBinaryTree(
                        listOf(RawNode(1, 2, null, 'A'), RawNode(value = 'B'), RawNode(value = 'C')))),
                data(listOf('D','E','B','C','A'), listOf('A','B','D','E','C'), buildBinaryTree(
                        listOf(RawNode(1, value = 'A'), RawNode(2, 3, value = 'B'),
                                RawNode(null, 4, value = 'D'), RawNode(value = 'C'),
                                RawNode(value = 'E')))),
                data(listOf('D','B','E','A','C'), listOf('A','B','D','E','C'),
                        expected = buildBinaryTree(listOf(
                                RawNode(1, 2, null, 'A'), RawNode(3, 4, 0, 'B'),
                                RawNode(null, null, 0, 'C'), RawNode(null, null, 1, 'D'),
                                RawNode(null, null, 1, 'E')
                        ))),
                data(listOf('D','E','B','A','C'), listOf('A','B','D','E','C'),
                        expected = buildBinaryTree(listOf(
                                RawNode(1, 2, null, 'A'), RawNode(3, null, 0, 'B'),
                                RawNode(null, null, 0, 'C'), RawNode(null, 4, 1, 'D'),
                                RawNode(null, null, 3, 'E')
                        ))),
                data(listOf('D','E','C','B','A'), listOf('A','B','D','E','C'),
                        expected = buildBinaryTree(listOf(
                                RawNode(1, null, null, 'A'), RawNode(2, null, 0, 'B'),
                                RawNode(null, 3, 1, 'D'), RawNode(null, 4, 2, 'E'),
                                RawNode(null, null, 3, 'C')
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