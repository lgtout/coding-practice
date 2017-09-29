package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.datastructures.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

typealias RawNode = RawBinaryTreeNode<Char>

object ReconstructBinaryTreeFromTraversalSpek : Spek({
    describe("reconstructBinaryTreeFromTraversal") {
        fun buildBinaryTree(rawTree: List<RawNode>): List<BinaryTreeNode<Char>> {
            return com.lagostout.datastructures.BinaryTreeNode.
                    buildBinaryTree(rawTree).second
        }
        val data = arrayOf(
                data(listOf('A'), listOf('A'), expected = buildBinaryTree(
                        listOf(RawNode(null, null, null, 'A')))),
                data(listOf('B','A'), listOf('A','B'), expected = buildBinaryTree(
                        listOf(RawNode(1, null, null, 'A'), RawNode(null, null, 0, 'B'))))
        )
        on("inorder: %s, preorder: %s", with = *data) {
            inorder, preorder, expected ->

        }
    }
})