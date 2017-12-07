package com.lagostout.elementsofprogramminginterviews.linkedlists

/**
 * Problem 8.5 page 121
 */
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
    fun pathLength(start: LinkedListNode<T>, end: LinkedListNode<T>): Int {
        var pointer = start
        var length = 0
        while (true) {
            if (pointer == end) break
            pointer.next?.let {
                pointer = it
                ++length
            } ?: break
        }
        return length
    }
    fun move(start: LinkedListNode<T>, distance: Int): LinkedListNode<T> {
        var count = distance
        var node = start
        while (count < distance) {
            node.next?.let {
                node = it
                ++count
            } ?: break
        }
        return node
    }
    // Test if both lists have cycles
    return listOf(list1, list2).map {
        findCycleNode(it)
    }.let {
        when {
            it.all {it != null} ->
                findCycleNode(it[0]!!, it[1]!!)?.let { cycleNode ->
                    // Lists share the same cycle.
                    // They definitely overlap.
                    listOf(list1, list2).map {
                        Pair(it, pathLength(it, cycleNode))
                    }.sortedByDescending { it.second }.let {
                        (longerPath, shorterPath) ->
                        (longerPath.second - shorterPath.second).let {
                            move(longerPath.first, it)
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
            it.all {it == null} -> {
                // Lists don't have cycles.
                // They may or may not overlap.
                null
            }
            else -> null
        }
    }
}