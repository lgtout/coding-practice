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
            Map<Node<Char>, Set<Node<Char>>> {
        val keys = rawMap.keys.toList()
        return rawMap.mapValues {
            // Convert indices to nodes
            it.value.map {
                keys[it]
            }.toMutableSet()
        }.apply {
            // Add back edges
            forEach { (key, adjacentNodes) ->
                adjacentNodes.forEach {
                    get(it)?.add(key)
                }
            }
        }
    }
    describe("graphRemainsConnectedAfterRemovingAnyEdge()") {
        val data = listOf(
                Pair(mapOf(Node('A') to emptyList()), true),
                Pair(mapOf(Node('A') to listOf(1), Node('B') to emptyList()), false),
                Pair(mapOf(Node('A') to listOf(2), Node('B') to listOf(2),
                        Node('C') to listOf(3), Node('D') to emptyList()), false),
                Pair(mapOf(Node('A') to listOf(1,2), Node('B') to listOf(2),
                        Node('C') to listOf(3), Node('D') to emptyList()), false),
                Pair(mapOf(Node('A') to listOf(1), Node('B') to listOf(2),
                        Node('C') to emptyList()), false),
                Pair(mapOf(Node('A') to listOf(1), Node('B') to listOf(2),
                        Node('C') to listOf(1)), false),
                Pair(mapOf(Node('A') to listOf(1), Node('B') to listOf(2),
                        Node('C') to listOf(0)), true),
                null
        ).filterNotNull().map { data(toGraph(it.first), it.second) }.toTypedArray()
        on("graph: %s", with = *data) {
            graph: Map<Node<Char>, Set<Node<Char>>>, expected: Boolean ->
            it("returns $expected") {
                assertThat(graphRemainsConnectedAfterRemovingAnyEdge(graph))
                        .isEqualTo(expected)
            }
        }
    }
})