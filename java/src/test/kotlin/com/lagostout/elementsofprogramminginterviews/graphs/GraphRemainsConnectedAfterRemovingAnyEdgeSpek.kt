package com.lagostout.elementsofprogramminginterviews.graphs

import com.lagostout.elementsofprogramminginterviews.graphs.GraphRemainsConnectedAfterRemovingAnyEdge.Node
import com.lagostout.elementsofprogramminginterviews.graphs.GraphRemainsConnectedAfterRemovingAnyEdge.graphRemainsConnectedAfterRemovingAnyEdge
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object GraphRemainsConnectedAfterRemovingAnyEdgeSpek : Spek({
    fun toGraph(rawMap: Map<Node<Char>, List<Int>>):
            Map<Node<Char>, List<Node<Char>>> {
        val keys = rawMap.keys.toList()
        return rawMap.mapValues { (_, value) ->
            value.map {
                keys[it]
            }
        }
    }
    describe("graphRemainsConnectedAfterRemovingAnyEdge()") {
        val data = listOf(
                Pair(mapOf(Node('A') to emptyList()), false),
                Pair(mapOf(Node('A') to listOf(1), Node('B') to emptyList()), true),
                Pair(mapOf(Node('A') to listOf(1), Node('B') to listOf(0)), false),
                Pair(mapOf(Node('A') to listOf(2), Node('B') to listOf(2),
                        Node('C') to listOf(3), Node('D') to emptyList()), true),
                null
        ).filterNotNull().map { data(toGraph(it.first), it.second) }.toTypedArray()
        on("graph: %s", with = *data) {
            graph: Map<Node<Char>, List<Node<Char>>>, expected: Boolean ->
            it("returns $expected") {
                assertThat(graphRemainsConnectedAfterRemovingAnyEdge(graph))
                        .isEqualTo(expected)
            }
        }
    }
})