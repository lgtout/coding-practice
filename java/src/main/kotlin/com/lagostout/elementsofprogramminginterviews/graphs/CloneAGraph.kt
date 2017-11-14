package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

/**
 * Problem 19.5 page 369
 */
object CloneAGraph {

    data class Vertex(val label: Int, val adjacents: MutableList<Vertex> = mutableListOf())

    @Suppress("NAME_SHADOWING")
    fun cloneGraph(vertices: List<Vertex>): List<Vertex> {
        val vertices = vertices.toMutableSet()
        val stack = LinkedList<Vertex>()
        val vertexToCloneMap = mutableMapOf<Vertex, Vertex>()
        fun computeIfAbsent(vertex: Vertex): Vertex {
            return vertexToCloneMap.computeIfAbsent(vertex) {
                key -> Vertex(key.label) }
        }
        while (vertices.isNotEmpty()) {
            val explored = mutableSetOf<Vertex>()
            stack.push(vertices.first())
            while (stack.isNotEmpty()) {
                val vertex = stack.pop()
                if (vertex in explored) continue
                explored.add(vertex)
                vertices.remove(vertex)
                computeIfAbsent(vertex).let { clone ->
                    // We haven't visited this vertex before, so it's
                    // not possible to add duplicate adjacent vertices.
//                    println("vertexToCloneMap $vertexToCloneMap")
//                    println("clone $clone")
                    vertex.adjacents.forEach {
                        clone.adjacents.add(
                                computeIfAbsent(it))
                    }
                }
//                println("vertexToCloneMap $vertexToCloneMap")
            }
        }
//        println(vertexToCloneMap)
        return vertexToCloneMap.values.toList()
    }

}
