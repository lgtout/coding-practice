package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.datastructures.RawBinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

typealias RawNode = RawBinaryTreeNode<Char>

object ReconstructBinaryTreeFromTraversalSpek : Spek({
    describe("reconstructBinaryTreeFromTraversal") {
        fun buildBinaryTree(rawTree: List<RawNode>): BinaryTreeNode<Char> {
            val pair = BinaryTreeNode.buildBinaryTree(rawTree)
//            println(pair)
            return pair.first!!
//                    .second.first()
        }
        val data = listOf(
//                data(listOf('A'), listOf('A'), expected = buildBinaryTree(
//                        listOf(RawNode(null, null, null, 'A')))),
//                data(listOf('B','A'), listOf('A','B'), expected = buildBinaryTree(
//                        listOf(RawNode(1, null, null, 'A'), RawNode(null, null, 0, 'B')))),
//                data(listOf('D','B','E','A','C'), listOf('A','B','D','E','C'),
//                        expected = buildBinaryTree(listOf(
//                                RawNode(1, 2, null, 'A'), RawNode(3, 4, 0, 'B'),
//                                RawNode(null, null, 0, 'C'), RawNode(null, null, 1, 'D'),
//                                RawNode(null, null, 1, 'E')
//                        ))),
//                data(listOf('D','E','B','A','C'), listOf('A','B','D','E','C'),
//                        expected = buildBinaryTree(listOf(
//                                RawNode(1, 2, null, 'A'), RawNode(3, null, 0, 'B'),
//                                RawNode(null, null, 0, 'C'), RawNode(null, 4, 1, 'D'),
//                                RawNode(null, null, 3, 'E')
//                        ))),
                // TODO May need more cases - I wasn't expecting this one to fail.
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
                val tree = reconstructBinaryTreeFromTraversal(inorder, preorder)
                // TODO 'E' is getting dropped by the algorithm.
                println(tree?.treeToString())
                assertEquals(expected, tree)
            }
        }
    }
})