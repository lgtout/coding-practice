package com.lagostout.elementsofprogramminginterviews.linkedlists

class ReverseSingleSublist {
    data class ListNode<T>(var data: T? = null, var next: ListNode<T>? = null)

    fun <T> reverseSingleSublist(list: ListNode<T>, start: Int, end: Int) {
        if (start < 1 || end < 1) {
            throw IllegalArgumentException("Start and end positions must be >= 1")
        }
        // Simplifying solution to only handle start <= end
        if (start > end) {
            throw IllegalArgumentException("End position must be >= start position")
        }
        if (list.next == null || start == end)
            return
        // TODO Handle empty list
        // TODO No fast way to check if end exceeds list length, so we'll check as we move in the list.
        var nodeBeforeSublistStart: ListNode<T>? = null
        var sublistEndNode: ListNode<T>? = null
        var currentPosition = 0
        var currentNode = ListNode<T>()
        currentNode.next = list
        while (true) {
            if (currentPosition + 1 == start)
                nodeBeforeSublistStart = currentNode
            if (currentPosition == end) {
                sublistEndNode = currentNode
                break
            }
            currentNode.next?.apply {
                currentNode = this
                ++currentPosition
            } ?: break
        }
        if (nodeBeforeSublistStart == null || sublistEndNode == null) {
            throw IllegalArgumentException(
                    "Start and end position values must be in the range " +
                            "1 to the length of the list (${currentPosition + 1})")
        }
        // TODO Verify for null cases (end of list)
        while (nodeBeforeSublistStart.next !== sublistEndNode) {
            nodeBeforeSublistStart.next?.let {
                nodeBeforeSublistStart?.next = it.next
                val nodeAfterSublistEndNode = sublistEndNode?.next
                sublistEndNode?.next = it
                it.next = nodeAfterSublistEndNode
                sublistEndNode = it
            }
        }
    }
}