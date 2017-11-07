package com.lagostout.elementsofprogramminginterviews.graphs

import com.lagostout.elementsofprogramminginterviews.graphs.CloneAGraph.cloneGraph
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

object CloneAGraphSpek : Spek({
    describe("cloneGraph()") {
        val data = listOf(
                listOf(Pair(0, emptyList<Int>())),
//                listOf(Pair(0, listOf(1)), Pair(1, emptyList())),
                null
        ).filterNotNull().map {
            it.map { Pair(CloneAGraph.Vertex(it.first), it.second) }.run {
                map { (vertex, indicesOfAdjacents) ->
                    indicesOfAdjacents.forEach {
                        vertex.adjacents.add(get(it).first)
                    }
                    vertex
                }
            }
        }
        data.forEachIndexed { index, vertices ->
            given("vertices $vertices") {
                it("#$index returns a clone of the graph") {
                    val clone = cloneGraph(vertices)
//                    assertThat(clone).containsExactlyInAnyOrder(
//                            *vertices.toTypedArray())
//                    vertices.filter { vertex ->
//                        clone.any { it === vertex }
//                    }.let {
//                        assertThat(it).containsExactlyInAnyOrder(
//                                *clone.toTypedArray())
//                    }
                    assertThat(clone).containsExactlyInAnyOrder(
                            *vertices.toTypedArray())
                    vertices.filter { vertex ->
                        clone.any { it === vertex }
                    }.let {
                        assertThat(it).containsExactlyInAnyOrder(
                                *clone.toTypedArray())
                    }
                }
            }
        }
    }
})
