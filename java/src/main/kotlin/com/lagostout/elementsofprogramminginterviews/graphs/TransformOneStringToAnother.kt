package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

/**
 *  Problem 19.7.1 page 372
 */
fun lengthOfShortestProductionSequence(from: String, to: String,
                                       dictionary: List<String>): Int? {
    // Build the graph
    val graph = mutableMapOf<String, MutableList<String>>()
    (0..from.lastIndex).forEach { indexOfCharToIgnore ->
        val adjacentWords = dictionary.groupBy {
            it.removeRange(indexOfCharToIgnore,
                    indexOfCharToIgnore + 1)
        }
        adjacentWords.values.forEach {
            for (source in 0..(it.lastIndex - 1)) {
                for (destination in (source + 1)..it.lastIndex) {
                    graph.getOrPut(dictionary[source], { mutableListOf() })
                            .add(dictionary[destination])
                }
            }
        }
    }
    // Search the graph
    data class Frame(val word: String, val adjacentWordIndex: Int = 0)
    val explored = mutableSetOf<String>()
    val stack = LinkedList<Frame>().apply {
        add(Frame(from))
    }
    explored.add(from)
    var distance: Int? = null
    while (stack.isNotEmpty()) {
        run {
            stack.pop().let { (word, adjacentWordIndex) ->
                if (adjacentWordIndex >= graph[word]?.size ?: 0) {
                    return@run
                }
                stack.push(Frame(word, adjacentWordIndex.inc()))
                graph[word]?.let {
                    it[adjacentWordIndex].let {
                        // Don't add _to_ to explored
                        if (it == to) {
                            val pathLength = stack.size - 1
                            distance = distance?.let {
                                minOf(pathLength, it)
                            } ?: pathLength
                            return@run
                        }
                        if (it in explored) return@run
                        stack.push(Frame(it, 0))
                        explored.add(it)
                    }
                }
            }
        }
    }
    return distance
}