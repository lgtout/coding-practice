package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

object CloneAGraph {

    data class Vertex(val label: Int, val adjacents: MutableList<Vertex> = mutableListOf())

    @Suppress("NAME_SHADOWING")
    fun cloneGraph(vertices: List<Vertex>): List<Vertex> {
        val vertices = vertices.toMutableSet()
        val stack = LinkedList<Vertex>()
        val vertexToCloneMap = mutableMapOf<Vertex, Vertex>()
                .withDefault { key -> Vertex(key.label) }
        while (vertices.isNotEmpty()) {
            val explored = mutableSetOf<Vertex>()
            stack.push(vertices.first())
            while (stack.isNotEmpty()) {
                val vertex = stack.pop()
                if (vertex in explored) continue
                explored.add(vertex)
                vertices.remove(vertex)
                vertexToCloneMap.getValue(vertex).let { clone ->
                    // We haven't visited this vertex before, so it's
                    // not possible to add duplicate adjacent vertices.
                    println(clone)
                    vertex.adjacents.forEach {
                        clone.adjacents.add(
                                vertexToCloneMap.getValue(it))
                    }
                }
            }
        }
        println(vertexToCloneMap)
        return vertexToCloneMap.values.toList()
    }

}
