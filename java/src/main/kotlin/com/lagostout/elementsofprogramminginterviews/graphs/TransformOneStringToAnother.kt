package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

/*  Problem 19.7.1 page 372 */
/*
    We'll assume that
    -- If there are words in the dictionary, they're
    the same length as [from] and [to].
    -- The source and destination strings are in the
    dictionary.
    -- The length of the production sequence is the
    length of the path in the graph from the source
    string to the destination string.  This is one
    less than the number of words on that path.
*/
fun lengthOfShortestProductionSequence(from: String, to: String,
                                       dictionary: List<String>): Int? {
    if (from == to) return 0

    // Build the graph
    val graph = mutableMapOf<String, MutableList<String>>()
    (0..from.lastIndex).forEach { indexOfCharToIgnore ->
        // Words that differ by one character are adjacent
        // in the graph e.g cod cad.
        val adjacentWordGroups = dictionary.groupBy {
            it.removeRange(indexOfCharToIgnore,
                    indexOfCharToIgnore + 1)
        }
        println(adjacentWordGroups)
        adjacentWordGroups.values.forEach { adjacentWords ->
            for (source in 0..adjacentWords.lastIndex) {
                (0..adjacentWords.lastIndex).filterNot { it == source }
                        .forEach { destination ->
                            graph.getOrPut(adjacentWords[source],
                                    { mutableListOf() })
                                    .add(adjacentWords[destination])
                        }
            }
        }
    }

    println(graph)

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
                            val pathLength = stack.size
                            distance = listOfNotNull(
                                    distance, pathLength).min()
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