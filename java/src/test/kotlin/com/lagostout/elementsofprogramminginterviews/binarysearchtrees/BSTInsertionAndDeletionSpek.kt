package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.datastructures.BinaryTreeNode.Companion.bbtr
import com.lagostout.datastructures.RawBinaryTreeNode.Companion.rbt
import com.lagostout.kotlin.common.Alphabet
import com.lagostout.kotlin.common.Alphabet.*
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object BSTInsertionAndDeletionSpek : Spek({

    describe("insert") {
        val data = listOfNotNull(
            data(bbtr(listOf(rbt(A))), B, bbtr(listOf(rbt(A, right = 1), rbt(B)))),
            data(bbtr(listOf(rbt(B))), A, bbtr(listOf(rbt(B, left = 1), rbt(A)))),
            data(bbtr(listOf(rbt(D, left = 1, right = 2), rbt(B), rbt(F))), A,
                bbtr(listOf(rbt(D, left = 1, right = 2), rbt(B, left = 3), rbt(F), rbt(A)))),
            data(bbtr(listOf(rbt(D, left = 1, right = 2), rbt(B), rbt(F))), C,
                bbtr(listOf(rbt(D, left = 1, right = 2), rbt(B, right = 3), rbt(F), rbt(C)))),
            data(bbtr(listOf(rbt(D, left = 1, right = 2), rbt(B), rbt(F))), E,
                bbtr(listOf(rbt(D, left = 1, right = 2), rbt(B), rbt(F, left = 3), rbt(E)))),
            data(bbtr(listOf(rbt(D, left = 1, right = 2), rbt(B), rbt(F))), G,
                bbtr(listOf(rbt(D, left = 1, right = 2), rbt(B), rbt(F, right = 3), rbt(G)))),
            null
        ).toTypedArray()

        on("tree: %s, valueToInsert: %s", with = *data) { root, valueToInsert, expected ->
            it("should return $expected") {
                BSTInsertionAndDeletion.insert(valueToInsert, root!!)
                assertThat(root).isEqualTo(expected)
            }
        }
    }

    describe("delete") {

        val data = listOfNotNull(

            // Delete non-existent node
            data(bbtr(listOf(rbt(A))), B, bbtr(listOf(rbt(A)))),
            data(bbtr(listOf(rbt(B, left = 1, right = 2), rbt(A), rbt(C))), D,
                bbtr(listOf(rbt(B, left = 1, right = 2), rbt(A), rbt(C)))),
            data(bbtr(listOf(rbt(C, left = 1, right = 2), rbt(B), rbt(D))), A,
                bbtr(listOf(rbt(C, left = 1, right = 2), rbt(B), rbt(D)))),

            // Delete root node

            // Delete root node when there is no replacement node
            data(bbtr(listOf(rbt(A))), A, null as BinaryTreeNode<Alphabet>?),

            // Delete root node when replacement node is child of root node and has no children
            data(bbtr(listOf(rbt(B, left = 1), rbt(A))), B, bbtr(listOf(rbt(A)))),
            data(bbtr(listOf(rbt(A, right = 1), rbt(B))), A, bbtr(listOf(rbt(B)))),
            data(bbtr(listOf(rbt(B, left = 1, right = 2), rbt(A), rbt(C))), B,
                bbtr(listOf(rbt(A, right = 1), rbt(C)))),

            // Delete root node when replacement node is child of root and has a child
            data(bbtr(listOf(rbt(D, left = 1, right = 2), rbt(B, left = 3), rbt(E), rbt(A))), D,
                bbtr(listOf(rbt(B, left = 1, right = 2), rbt(A), rbt(E)))),
            data(bbtr(listOf(rbt(A, right = 1), rbt(B, right = 2), rbt(C))), A,
                bbtr(listOf(rbt(B, right = 1), rbt(C)))),

            // Delete root node when replacement node is not a child of root and has no children
            data(bbtr(listOf(rbt(A, right = 1), rbt(B, right = 2), rbt(C))), A,
                bbtr(listOf(rbt(B, right = 1), rbt(C)))),
            data(bbtr(listOf(rbt(C, left = 1, right = 3), rbt(A, right = 2), rbt(B), rbt(D))), C,
                bbtr(listOf(rbt(B, left = 1, right = 2), rbt(A), rbt(D)))),
            data(bbtr(listOf(rbt(C, left = 1), rbt(B, left = 2), rbt(C))), C,
                bbtr(listOf(rbt(B, left = 1), rbt(C)))),

            // Delete root node when replacement node is not a child of root and has a child
            data(bbtr(listOf(rbt(A, right = 1), rbt(D, left = 2), rbt(B, right = 3), rbt(C))), A,
                bbtr(listOf(rbt(B, right = 1), rbt(D, left = 2), rbt(C)))),
            data(bbtr(listOf(rbt(D, left = 1, right = 2), rbt(A, right = 3), rbt(E), rbt(C, left = 4), rbt(B))), D,
                bbtr(listOf(rbt(C, left = 1, right = 2), rbt(A, right = 3), rbt(E), rbt(B)))),

            // Delete non-root node

            // The following sub-cases are not exhaustive, but I believe I've covered enough to be
            // somewhat confident of the correctness of the solution.

            // Delete non-root node that is a left or right child, when there is no replacement node.
            data(bbtr(listOf(rbt(A, right = 1), rbt(B))), B, bbtr(listOf(rbt(A)))),
            data(bbtr(listOf(rbt(B, left = 1), rbt(A))), A, bbtr(listOf(rbt(B)))),

            // Delete non-root node that is a left or right child, when there is a replacement node,
            // the replacement node is a child of the node to delete, and the replacement node has
            // no children.
            data(bbtr(listOf(rbt(C, left = 1), rbt(B, right = 2), rbt(C))), B,
                bbtr(listOf(rbt(C, left = 1), rbt(C)))),
            data(bbtr(listOf(rbt(A, right = 1), rbt(C, left = 2), rbt(B))), C,
                bbtr(listOf(rbt(A, right = 1), rbt(B)))),

            // Delete non-root node that is a left or right child, when there is a replacement node,
            // the replacement node is a child of the node to delete, and the replacement node has
            // a child. This case is already covered.

            // Delete non-root node that is a left or right child, when there is a replacement node,
            // the replacement node is not a child of the node to delete, and the replacement node has
            // a child.
            data(bbtr(listOf(rbt(A, right = 1), rbt(E, left = 2), rbt(B, right = 3), rbt(D, left = 4), rbt(C))), E,
                bbtr(listOf(rbt(A, right = 1), rbt(D, left = 2), rbt(B, right = 3), rbt(C)))),
            data(bbtr(listOf(rbt(E, left = 1), rbt(A, right = 2), rbt(D, left = 3), rbt(B, right = 4), rbt(C))), A,
                bbtr(listOf(rbt(E, left = 1), rbt(B, right = 2), rbt(D, left = 3), rbt(C)))),

            null
        ).toTypedArray()

        on("tree: %s, valueToDelete: %s", with = *data) { root, valueToDelete, expected ->
            it("should return $expected") {
                val tree = BSTInsertionAndDeletion.delete(valueToDelete, root!!)
                assertThat(tree).isEqualTo(expected)
            }
        }
    }

})
