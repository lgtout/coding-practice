package com.lagostout.elementsofprogramminginterviews.graphs

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

object DeadlockDetectionInUndirectedGraphSpek : Spek({
    describe("detectDeadlock()") {

        @Suppress("NAME_SHADOWING")
        fun <T> addBackEdges(graph: MutableMap<T, MutableSet<T>>):
                MutableMap<T, MutableSet<T>> {
            return graph.apply {
                entries.forEach { (node, adjacentNodes) ->
                    adjacentNodes.forEach {
                        (graph[it]?: mutableSetOf<T>().apply {
                            graph[it] = this
                        }).add(node)
                    }
                }
            }
        }

        val data = listOf(
                data(mutableMapOf(), false),
                data(addBackEdges(mutableMapOf(0 to mutableSetOf(1), 0 to mutableSetOf(1))), false),
                data(addBackEdges(mutableMapOf(0 to mutableSetOf(1),
                        1 to mutableSetOf(2), 2 to mutableSetOf(0))), true),
                data(addBackEdges(mutableMapOf(0 to mutableSetOf(1),
                        1 to mutableSetOf(2))), false),
                data(addBackEdges(mutableMapOf(0 to mutableSetOf(1),
                        1 to mutableSetOf(2), 2 to mutableSetOf(0,3,4))), true),
                data(addBackEdges(mutableMapOf(0 to mutableSetOf(1),
                        1 to mutableSetOf(2), 2 to mutableSetOf(3,4))), false),
                null
        ).filterNotNull().toTypedArray()

        on("graph: %s", with = *data) { graph, expected ->
            it("returns $expected") {
                assertEquals(expected, detectDeadlock(graph))
            }
        }
    }
})