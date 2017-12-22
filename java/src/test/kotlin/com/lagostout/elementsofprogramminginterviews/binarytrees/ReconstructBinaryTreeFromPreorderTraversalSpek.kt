package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on


object ReconstructBinaryTreeFromPreorderTraversalSpek : Spek({
    describe("reconstructBinaryTreeFromPreorderTraversal") {
        fun buildBinaryTree(rawTree: List<RBTNode>): BinaryTreeNode<Char> {
            return BinaryTreeNode.buildBinaryTree(rawTree).first!!
        }
        val data = listOf(
                data(listOf('A'), listOf('A'), expected = buildBinaryTree(
                        listOf(RBTNode(null, null, null, 'A')))),
                data(listOf('B','A'), listOf('A','B'), expected = buildBinaryTree(
                        listOf(RBTNode(1, null, null, 'A'), RBTNode(null, null, 0, 'B')))),
                data(listOf('B','A','C'), listOf('A','B','C'), buildBinaryTree(
                        listOf(RBTNode(1, 2, null, 'A'), RBTNode(value = 'B'), RBTNode(value = 'C')))),
                data(listOf('D','E','B','C','A'), listOf('A','B','D','E','C'), buildBinaryTree(
                        listOf(RBTNode(1, value = 'A'), RBTNode(2, 3, value = 'B'),
                                RBTNode(null, 4, value = 'D'), RBTNode(value = 'C'),
                                RBTNode(value = 'E')))),
                data(listOf('D','B','E','A','C'), listOf('A','B','D','E','C'),
                        expected = buildBinaryTree(listOf(
                                RBTNode(1, 2, null, 'A'), RBTNode(3, 4, 0, 'B'),
                                RBTNode(null, null, 0, 'C'), RBTNode(null, null, 1, 'D'),
                                RBTNode(null, null, 1, 'E')
                        ))),
                data(listOf('D','E','B','A','C'), listOf('A','B','D','E','C'),
                        expected = buildBinaryTree(listOf(
                                RBTNode(1, 2, null, 'A'), RBTNode(3, null, 0, 'B'),
                                RBTNode(null, null, 0, 'C'), RBTNode(null, 4, 1, 'D'),
                                RBTNode(null, null, 3, 'E')
                        ))),
                data(listOf('D','E','C','B','A'), listOf('A','B','D','E','C'),
                        expected = buildBinaryTree(listOf(
                                RBTNode(1, null, null, 'A'), RBTNode(2, null, 0, 'B'),
                                RBTNode(null, 3, 1, 'D'), RBTNode(null, 4, 2, 'E'),
                                RBTNode(null, null, 3, 'C')
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