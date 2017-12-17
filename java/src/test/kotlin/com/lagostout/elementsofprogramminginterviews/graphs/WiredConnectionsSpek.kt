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
                Pair(listOf(node('A')), true),
                Pair(listOf(node('A'), node('B')), true),
                Pair(listOf(node('A', listOf(1)), node('B')), true),
                Pair(listOf(node('A', listOf(1)), node('B'), node('C')), true),
                Pair(listOf(node('A', listOf(1)), node('B', listOf(2)), node('C')), true),
                Pair(listOf(node('A', listOf(1)), node('B', listOf(2)), node('C', listOf(0))), false),
                Pair(listOf(node('A', listOf(1)), node('B', listOf(2)), node('C', listOf(1))), true),
                Pair(listOf(node('A', listOf(1)), node('B', listOf(2,3)),
                        node('C', listOf(1)), node('D', listOf(1))), true),
                Pair(listOf(node('A', listOf(1)), node('B', listOf(2,3)),
                        node('C', listOf(0)), node('D', listOf(0))), false),
                Pair(listOf(node('A', listOf(1)), node('B', listOf(2,3)),
                        node('C', listOf(0)), node('D')), false),
                Pair(listOf(node('A', listOf(1)), node('B', listOf(2,3)),
                        node('C', listOf(1)), node('D', listOf(0))), false),
                Pair(listOf(node('A', listOf(1)), node('B', listOf(2,3)),
                        node('C', listOf(4)), node('D'), node('1', listOf(1))), false),
                Pair(listOf(node('A', listOf(1)), node('B', listOf(2,3)),
                        node('C'), node('D', listOf(2))), false),
                Pair(listOf(node('A', listOf(1)), node('B', listOf(2,3)),
                        node('C', listOf(4)), node('D', listOf(4)), node('D')), true),
                Pair(listOf(node('A', listOf(1)), node('B', listOf(2,3)),
                        node('C', listOf(4)), node('D', listOf(4)), node('D'),
                        node('E', listOf(6)), node('F', listOf(7)), node('G')), true),
                Pair(listOf(node('A', listOf(1)), node('B', listOf(2,3)),
                        node('C', listOf(4)), node('D', listOf(4)), node('D'),
                        node('E', listOf(6)), node('F', listOf(7)), node('G', listOf(5))), false),
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