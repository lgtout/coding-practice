package com.lagostout.elementsofprogramminginterviews.binarytrees

import assertk.Assert
import assertk.assert
import assertk.assertions.support.expected
import assertk.assertions.support.show
import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.datastructures.BinaryTreeNode.Companion.bbtr
import com.lagostout.datastructures.RawBinaryTreeNode.Companion.rbt
import com.lagostout.kotlin.common.Alphabet
import com.lagostout.kotlin.common.Alphabet.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ComputeRightSiblingTreeUsingRightChildFieldSpek : Spek({

    val data = listOfNotNull(
        data(bbtr(listOf(rbt(A))), bbtr(listOf(rbt(A)))),
        data(bbtr(listOf(rbt(A, left = 1, right = 2), rbt(B), rbt(C))),
            bbtr(listOf(rbt(A, left = 1), rbt(B, right = 2), rbt(C)))),
        data(bbtr(listOf(rbt(A, left = 1, right = 2), rbt(B, left = 3, right = 4),
            rbt(C, left = 5, right = 6), rbt(D), rbt(E), rbt(F), rbt(G))),
            bbtr(listOf(rbt(A, left = 1), rbt(B, left = 3, right = 2),
                rbt(C, left = 5), rbt(D, right = 4), rbt(E, right = 5),
                rbt(F, right = 6), rbt(G)))),
        null
    ).toTypedArray()

    fun Assert<BinaryTreeNode<Alphabet>>.isEqualTo(
            expectedValue: BinaryTreeNode<Alphabet>?) {

        fun treesAreEqual(
                firstRoot: BinaryTreeNode<Alphabet>?,
                secondRoot: BinaryTreeNode<Alphabet>?):
                Pair<Boolean, Pair<Alphabet?, Alphabet?>?>  {
            val roots = listOf(firstRoot, secondRoot)
            when (roots.count { it == null }) {
                1 -> return Pair(false, Pair(firstRoot?.value, secondRoot?.value))
                2 -> return Pair(true, null)
            }
            if (firstRoot!!.value != secondRoot!!.value)
                return Pair(false, Pair(firstRoot.value, secondRoot.value))
            return listOf(treesAreEqual(firstRoot.left, secondRoot.left),
                    treesAreEqual(firstRoot.right, secondRoot.right))
                    .filter { !it.first }.let {
                        if (it.isEmpty()) Pair(true, null)
                        else it.first()
                    }
        }

        val result = treesAreEqual(actual, expectedValue)
        if (result.first) return
        expected(":${show(result.second?.second)} but was:${show(result.second?.first)}")
    }


    describe("computeRightSiblingTreeUsingRightChildField") {
        on("root %s", with = *data) { root, expected ->
            it("should set right children to level nexts $expected") {
                computeRightSiblingTreeUsingRightChildField(root!!)
                assert(root).isEqualTo(expected)
            }
        }
    }

})