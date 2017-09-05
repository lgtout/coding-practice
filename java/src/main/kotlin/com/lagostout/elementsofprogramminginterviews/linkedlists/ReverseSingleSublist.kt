package com.lagostout.elementsofprogramminginterviews.linkedlists

class ReverseSingleSublist {
    data class ListNode<T>(var data: T? = null, var next: ListNode<T>? = null)

    fun <T> reverseSingleSublist(list: ListNode<T>, start: Int, end: Int) {
        var lastNode = list
        var listSize = 1
        while (true) {
            ++listSize
            lastNode.next?.let {
                lastNode = it
            } ?: break
        }
        if (listOf(start, end).find { it < 1 || it > listSize} != null) {
            throw IllegalArgumentException("Start and end positions must be >= 1 and <= list size")
        }
        // Nodes point in one direction only - left to right.
        // So let's simplify solution by constraining start and end.
        if (start > end) {
            throw IllegalArgumentException("End position must be >= start position")
        }
        var nodeBeforeSublistStart: ListNode<T>? = null
        var sublistEnd: ListNode<T>? = null
        var currentPosition = 0
        val head = ListNode(next = list)
        var currentNode = head
        while (true) {
            if (currentPosition + 1 == start)
                nodeBeforeSublistStart = currentNode
            if (currentPosition == end) {
                sublistEnd = currentNode
                break
            }
            currentNode.next?.apply {
                currentNode = this
                ++currentPosition
            } ?: break
        }
        while (nodeBeforeSublistStart?.next !== sublistEnd) {
            nodeBeforeSublistStart?.next?.let {
                nodeBeforeSublistStart?.next = it.next
                sublistEnd?.apply {
                    it.next = next
                    next = it
                }
            }
        }
    }
}