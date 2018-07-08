package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

/*  Problem 19.7.1 page 372 */

/* We'll assume that:
  - If there are words in the dictionary, they're
    the same length as [from] and [to].
  - The source and destination strings are in the
    dictionary. */

fun lengthOfShortestProductionSequence(from: String, to: String,
                                       dictionary: List<String>): Int? {
    if (from == to) return 1

    // Build the graph
    val graph = mutableMapOf<String, MutableList<String>>()
    (0..from.lastIndex).forEach { indexOfCharToIgnore ->
        // Words that differ by one character are adjacent
        // in the graph e.g cod cad.
        val adjacentWordGroups = dictionary.groupBy {
            it.removeRange(indexOfCharToIgnore, indexOfCharToIgnore + 1)
        }
//        println(adjacentWordGroups)
        adjacentWordGroups.values.forEach { adjacentWords ->
            for (sourceIndex in 0..adjacentWords.lastIndex) {
                (0..adjacentWords.lastIndex)
                        .filterNot { it == sourceIndex }
                        .forEach { destinationIndex ->
                            graph.getOrPut(adjacentWords[sourceIndex]) {
                                mutableListOf()
                            }.add(adjacentWords[destinationIndex])
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
                graph[word]?.let {
                    if (adjacentWordIndex >= it.size) {
                        explored.remove(word)
                        return@run
                    }
                    stack.push(Frame(word, adjacentWordIndex.inc()))
                    it[adjacentWordIndex].let { adjacentWord ->
                        // Don't add _to_ to explored
                        if (adjacentWord == to) {
                            // Include _to_ in path length.
                            val pathLength = stack.size + 1
                            val d = distance
                            if (d == null || d > pathLength) {
                                distance = pathLength
                            }
                            return@run
                        }
                        // Don't double back on words already in the stack.
                        if (explored.contains(adjacentWord)) return@run
                        stack.push(Frame(adjacentWord, 0))
                        explored.add(adjacentWord)
                    }
                }
            }
        }
    }

    return distance
}