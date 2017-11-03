package com.lagostout.elementsofprogramminginterviews.graphs

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

object CloneAGraphSpek : Spek({
    describe("cloneGraph()") {
        val data = listOf(
                listOf(Pair(0, emptyList())),
                listOf(Pair(0, listOf(1)), Pair(1, emptyList())),
                null
        ).filterNotNull().map {
            it.map { Pair(CloneAGraph.Vertex(it.first), it.second) }.also {
                it.forEach { (vertex, indicesOfAdjacents) ->
                    indicesOfAdjacents.map {}
//                    vertex.adjacents.add(it[index])
                }
            }
        }
        data.forEach {

        }
//        given()
    }
})
