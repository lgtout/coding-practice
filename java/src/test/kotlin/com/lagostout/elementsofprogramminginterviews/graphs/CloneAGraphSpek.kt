package com.lagostout.elementsofprogramminginterviews.graphs

import com.lagostout.elementsofprogramminginterviews.graphs.CloneAGraph.cloneGraph
import com.lagostout.elementsofprogramminginterviews.graphs.CloneAGraph.Vertex
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

object CloneAGraphSpek : Spek({
    describe("cloneGraph()") {
        val data = listOf(
                listOf(Pair(0, emptyList())),
                listOf(Pair(0, listOf(1)), Pair(1, emptyList())),
                listOf(Pair(0, listOf(1)), Pair(1, emptyList()),
                        Pair(2, listOf())),
                listOf(Pair(0, listOf(1)), Pair(1, emptyList()),
                        Pair(2, listOf(3)), Pair(3, emptyList())),
                listOf(Pair(0, listOf(1)), Pair(1, emptyList()),
                        Pair(2, listOf(1)), Pair(3, emptyList())),
                listOf(Pair(0, listOf(1)), Pair(1, emptyList()),
                        Pair(2, listOf(1,3)), Pair(3, emptyList())),
                listOf(Pair(0, listOf(1,3,2)), Pair(1, emptyList()),
                        Pair(2, listOf(1,3)), Pair(3, emptyList())),
                listOf(Pair(0, listOf(1,2,3)), Pair(1, emptyList()),
                        Pair(2, listOf(1,3)), Pair(3, listOf(1))),
                listOf(Pair(0, listOf(1,2,3)), Pair(1, emptyList()),
                        Pair(2, listOf(1,3)), Pair(3, listOf(2))),
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
                    val expectedClonedVertices = mutableListOf<Vertex>()
                    val clonedVertices = cloneGraph(vertices, { label -> Vertex(label).apply {
                        expectedClonedVertices.add(this)
                    } })
                    assertThat(clonedVertices).usingElementComparator({
                        o1, o2 -> if (o1 === o2) 0 else 1
                    }).containsExactlyElementsOf(expectedClonedVertices)
                }
            }
        }
    }
})
