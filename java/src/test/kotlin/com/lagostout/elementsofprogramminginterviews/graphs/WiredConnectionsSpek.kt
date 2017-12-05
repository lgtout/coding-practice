package com.lagostout.elementsofprogramminginterviews.graphs

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object WiredConnectionsSpek : Spek({
    describe("computeIfBipartiteGraphIsPossible()") {
        fun node(value: Char, adjacentNodes: List<Int> = emptyList()) =
                RawGraphNode(value, adjacentNodes)
        val data = listOfNotNull(
//                Pair(listOf(node('A')), true),
//                Pair(listOf(node('A'), node('B')), true),
                Pair(listOf(node('A', listOf(1)), node('B')), true),
//                Pair(listOf(node('A', listOf(1)), node('B'), node('C')), true),
//                Pair(listOf(node('A', listOf(1)), node('B', listOf(2)), node('C')), true),
//                Pair(listOf(node('A', listOf(1)), node('B', listOf(2)), node('C', listOf(0))), false),
                null
        ).map {
            data(toGraph(it.first), it.second)
        }.toTypedArray()
        on("graph: %s", with = *data) { graph, expected ->
            it("returns $expected") {
                assertThat(computeIfBipartiteGraphIsPossible(graph))
                        .isEqualTo(expected)
            }
        }
    }
})