package com.lagostout.bytebybyte.recursion;

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object LowestCommonAncestorSpek : Spek({

    data class RawNode(val value: Int, val leftChildIndex: Int? = null,
                       val rightChildIndex: Int? = null)

    describe("lowestCommonAncestor") {

        val data = listOfNotNull(
            Triple(listOf(RawNode(0)), Pair(0,0), 0),
            Triple(listOf(RawNode(0,1), RawNode(1)), Pair(0,0), 0),
            Triple(listOf(RawNode(0,1), RawNode(1)), Pair(0,1), 0),
            Triple(listOf(RawNode(0,1), RawNode(1)), Pair(1,0), 0),
            Triple(listOf(RawNode(0,1), RawNode(1)), Pair(1,1), 1),
            Triple(listOf(RawNode(0,1,2), RawNode(1), RawNode(2)), Pair(1,2), 0),
            Triple(listOf(RawNode(0,1,2), RawNode(1,3,4), RawNode(2), RawNode(3), RawNode(4)), Pair(1,2), 0),
            Triple(listOf(RawNode(0,1,2), RawNode(1,3,4), RawNode(2), RawNode(3), RawNode(4)), Pair(3,4), 1),
            Triple(listOf(RawNode(0,1,2), RawNode(1,3,4), RawNode(2,5,6), RawNode(3), RawNode(4),
                RawNode(5), RawNode(6)), Pair(4,5), 0),
            null
        ).map { (rawNodes, indicesOfNodesToSearchFor, expectedAncestorIndex) ->
            val nodes = rawNodes.map { Node(it.value) }
            nodes.forEachIndexed { index, node ->
                val rawNode= rawNodes[index]
                rawNode.leftChildIndex?.let {
                    node.left = nodes[it]
                }
                rawNode.rightChildIndex?.let {
                    node.right = nodes[it]
                }
            }
            val root = nodes.first()
            val firstDescendant = nodes[indicesOfNodesToSearchFor.first]
            val secondDescendant = nodes[indicesOfNodesToSearchFor.second]
            val expectedAncestor = nodes[expectedAncestorIndex]
            data(root, firstDescendant, secondDescendant, expectedAncestor)
        }.toTypedArray()

        on("root: %s, firstDescendant: %s, secondDescendant: %s", with = *data) {
            root, firstDescendant, secondDescendant, expected ->
            it("should return $expected") {
                assertThat(lowestCommonAncestor(root, firstDescendant, secondDescendant))
                        .isEqualTo(expected)
            }
        }
    }

})
