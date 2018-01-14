package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.datastructures.BinaryTreeNode.Companion.buildBinaryTreeRoot
import com.lagostout.datastructures.RawBinaryTreeNode.Companion.rbt
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ReconstructBinaryTreeFromPreorderTraversalSpek : Spek({
    describe("reconstructBinaryTreeFromPreorderTraversal") {
        val data = listOfNotNull(
                data(listOf('A'), listOf('A'), expected = buildBinaryTreeRoot(
                        listOf(rbt(null, null, null, 'A')))),
                data(listOf('B','A'), listOf('A','B'), expected = buildBinaryTreeRoot(
                        listOf(rbt(1, null, null, 'A'), rbt(null, null, 0, 'B')))),
                data(listOf('B','A','C'), listOf('A','B','C'), buildBinaryTreeRoot(
                        listOf(rbt(1, 2, null, 'A'), rbt(null, value = 'B'), rbt(null, value = 'C')))),
                data(listOf('D','E','B','C','A'), listOf('A','B','D','E','C'), buildBinaryTreeRoot(
                        listOf(rbt(1, value = 'A'), rbt(2, 3, value = 'B'),
                                rbt(null, 4, value = 'D'), rbt(null, value = 'C'),
                                rbt(null, value = 'E')))),
                data(listOf('D','B','E','A','C'), listOf('A','B','D','E','C'),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(1, 2, null, 'A'), rbt(3, 4, 0, 'B'),
                                rbt(null, null, 0, 'C'), rbt(null, null, 1, 'D'),
                                rbt(null, null, 1, 'E')
                        ))),
                data(listOf('D','E','B','A','C'), listOf('A','B','D','E','C'),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(1, 2, null, 'A'), rbt(3, null, 0, 'B'),
                                rbt(null, null, 0, 'C'), rbt(null, 4, 1, 'D'),
                                rbt(null, null, 3, 'E')
                        ))),
                data(listOf('D','E','C','B','A'), listOf('A','B','D','E','C'),
                        expected = buildBinaryTreeRoot(listOf(
                                rbt(1, null, null, 'A'), rbt(2, null, 0, 'B'),
                                rbt(null, 3, 1, 'D'), rbt(null, 4, 2, 'E'),
                                rbt(null, null, 3, 'C')
                        ))),
                null
        ).toTypedArray()
        on("inorder: %s, preorder: %s", with = *data) {
            inorder, preorder, expected ->
            it("returns $expected") {
                val tree = reconstructBinaryTreeFromPreorderTraversal(inorder, preorder)
                assertThat(tree).isEqualTo(expected)
            }
        }
    }
})