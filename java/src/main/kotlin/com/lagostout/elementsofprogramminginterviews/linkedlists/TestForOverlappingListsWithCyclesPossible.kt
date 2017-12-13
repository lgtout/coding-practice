package com.lagostout.elementsofprogramminginterviews.linkedlists

/**
 * Problem 8.5 page 121
 */
// Assumptions
// - Lists are never empty.  However, when there's overlapping,
// they may not contain any nodes that aren't part of the
// overlapping segment.
fun <T> listsOverlapWithCyclesPossible(
        list1: LinkedListNode<T>, list2: LinkedListNode<T>):
        LinkedListNode<T>? {
    fun findCycleNode(node1: LinkedListNode<T>, node2: LinkedListNode<T>):
            LinkedListNode<T>? {
        var pointer1 = node1
        var pointer2 = node2
        var cycleNode: LinkedListNode<T>? = null
        while (true) {
            pointer1.next?.let {
                pointer1 = it
            }?: break
            pointer2.next?.let {
                it.next?.let {
                    pointer2 = it
                }
            }?: break
            if (pointer1 == pointer2) {
                cycleNode = pointer1
                break
            }
        }
        return cycleNode
    }
    fun findCycleNode(node: LinkedListNode<T>): LinkedListNode<T>? {
        return findCycleNode(node, node)
    }
    fun pathLength(start: LinkedListNode<T>,
                   end: (LinkedListNode<T>) -> Boolean =
                   { it.next == null }): Int {
        var pointer = start
        var length = 0
        while (true) {
            if (end(pointer)) break
            pointer.next?.let {
                pointer = it
                ++length
            } ?: break
        }
        return length
    }
    // Find cycles.
    return listOf(list1, list2).map {
        findCycleNode(it)
    }.let {
        when {
            // Both lists have cycles.
            it.all {it != null} ->
                findCycleNode(it[0]!!, it[1]!!)?.let { cycleNode ->
                    // Lists share the same cycle.
                    // They definitely overlap.
                    listOf(list1, list2).map {
                        // Measure the distance from the first node
                        // to the node in the cycle.
                        Pair(it, pathLength(it, { cycleNode == it }))
                    }.sortedByDescending { it.second }.let {
                        (longerPath, shorterPath) ->
                        (longerPath.second - shorterPath.second).let {
                            longerPath.first.advance(it)
                        }.let {
                            var node1 = it
                            var node2 = shorterPath.first
                            while (node1 != node2) {
                                node1.next?.let {
                                    node1 = it
                                }
                                node2.next?.let {
                                    node2 = it
                                }
                            }
                            node1
                        }
                    }
                }
            // Lists don't have cycles.
            // They may or may not overlap.
            it.all {it == null} -> {
                listOf(list1, list2).map {
                    Pair(it, pathLength(it)) }
                        .sortedByDescending { it.second }
                        .let { (longerList, shorterList) ->
                            (longerList.second - shorterList.second).let {
                                // Let's be at the same distance from
                                //the end of each list.
                                var pointer1 = longerList.first.advance(it)
                                var pointer2 = shorterList.first
                                var intersection: LinkedListNode<T>? = null
                                while (true) {
                                    if (pointer1 == pointer2) {
                                        intersection = pointer1
                                        break
                                    }
                                    pointer1.next?.let {
                                        pointer1 = it
                                    } ?: break
                                    pointer2.next?.let {
                                        pointer2 = it
                                    }
                                }
                                intersection
                            }
                        }
            }
            // One list has a cycle, the other doesn't.
            // It's not possible for the lists to intersect.
            else -> null
        }
    }
}