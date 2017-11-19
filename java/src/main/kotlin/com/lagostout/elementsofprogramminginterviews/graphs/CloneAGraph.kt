package com.lagostout.elementsofprogramminginterviews.graphs

import com.google.common.base.MoreObjects
import org.apache.commons.lang3.builder.HashCodeBuilder
import java.util.*

/**
 * Problem 19.5 page 369
 */
object CloneAGraph {

    class Vertex(val label: Int, val adjacents: MutableList<Vertex> = mutableListOf()) {
        override fun toString(): String {
            return MoreObjects.toStringHelper(this).add("label", label).apply {
                add("adjacents", adjacents.map { label })
            }.toString()
        }

        override fun hashCode(): Int {
            return HashCodeBuilder().append(label).apply {
                append(adjacents.map { label })
            }.toHashCode()
        }
    }

    @Suppress("NAME_SHADOWING")
    fun cloneGraph(vertices: List<Vertex>,
                   vertex: (Int) -> Vertex): List<Vertex> {
        val vertices = vertices.toMutableSet()
        val stack = LinkedList<Vertex>()
        val vertexToCloneMap = mutableMapOf<Vertex, Vertex>()
        fun computeIfAbsent(vertex: Vertex): Vertex {
            return vertexToCloneMap.computeIfAbsent(vertex) {
                key -> vertex(key.label) }
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
                    vertex.adjacents.forEach {
                        clone.adjacents.add(
                                computeIfAbsent(it))
                    }
                }
            }
        }
        return vertexToCloneMap.values.toList()
    }

}
