package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

object CloneAGraph {

    data class Vertex(val label: Int, val adjacents: MutableList<Vertex> = mutableListOf())

    fun cloneGraph(node: Vertex): Vertex {
        val stack = LinkedList<Vertex>().apply {
            push(node)
        }
        val vertexToCloneMap = mutableMapOf<Vertex, Vertex>()
                .withDefault { key -> Vertex(key.label) }
        val explored = mutableSetOf<Vertex>()
        while (stack.isNotEmpty()) {
            val vertex = stack.pop()
            if (vertex in explored) continue
            explored.add(vertex)
            vertexToCloneMap.getValue(vertex).let { clone ->
                // We haven't visited this vertex before, so it's
                // not possible to add duplicate adjacent vertices.
                vertex.adjacents.forEach {
                    clone.adjacents.add(
                            vertexToCloneMap.getValue(it))
                }
            }
        }
        return vertexToCloneMap.getValue(node)
    }

}
