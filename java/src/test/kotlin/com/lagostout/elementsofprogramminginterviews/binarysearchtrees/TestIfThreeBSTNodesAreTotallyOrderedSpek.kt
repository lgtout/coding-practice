package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.common.second
import com.lagostout.common.third
import com.lagostout.datastructures.BinaryTreeNode.Companion.bbtr
import com.lagostout.datastructures.RawBinaryTreeNode.Companion.rbt
import com.lagostout.datastructures.bstFind
import com.lagostout.kotlin.common.Alphabet.*
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object TestIfThreeBSTNodesAreTotallyOrdered : Spek({

    val data = listOfNotNull(
        Triple(bbtr(listOf(rbt(A))), Triple(A,A,A), false),
        Triple(bbtr(listOf(rbt(A, right = 1), rbt(B))), Triple(A,B,A), false),
        Triple(bbtr(listOf(rbt(A, right = 1), rbt(B))), Triple(A,B,B), false),
        Triple(bbtr(listOf(rbt(B, left = 1), rbt(A))), Triple(A,B,A), false),
        Triple(bbtr(listOf(rbt(B, left = 1), rbt(A))), Triple(A,B,B), false),
        Triple(bbtr(listOf(rbt(A, right = 1), rbt(B, right = 2), rbt(C))), Triple(A,C,B), true),
        Triple(bbtr(listOf(rbt(A, right = 1), rbt(B, right = 2), rbt(C))), Triple(A,C,B), true),
        Triple(bbtr(listOf(rbt(A, right = 1), rbt(B, right = 2), rbt(C))), Triple(C,A,B), true),
        Triple(bbtr(listOf(rbt(D, left = 1, right = 4), rbt(B, left = 2, right = 3), rbt(A), rbt(C),
            rbt(I, left = 5, right = 9), rbt(F, left = 6, right = 7), rbt(E), rbt(H, left = 8), rbt(G),
            rbt(J))), Triple(G,D,F), true),
        Triple(bbtr(listOf(rbt(D, left = 1, right = 4), rbt(B, left = 2, right = 3), rbt(A), rbt(C),
            rbt(I, left = 5, right = 9), rbt(F, left = 6, right = 7), rbt(E), rbt(H, left = 8), rbt(G),
            rbt(J))), Triple(F,G,E), false),
        null
    ).map { (root, arguments, expected) ->
        val nodes = arguments.toList().map {
            root!!.bstFind(it)!!
        }
        data(nodes.first(), nodes.second(), nodes.third(), expected)
    }.toTypedArray()

    describe("bstNodesAreTotallyOrdered") {
        on("firstAncestorOrDescendant %s, secondAncestorOrDescendant %s, " +
                "middle %s", with = *data) {
                firstAncestorOrDescendant, secondAncestorOrDescendant, middle, expected ->
            it("should return $expected") {
                assertThat(bstNodesAreTotallyOrdered(
                    firstAncestorOrDescendant, secondAncestorOrDescendant, middle))
            }
        }
    }


})