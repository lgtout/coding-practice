package com.lagostout.elementsofprogramminginterviews.linkedlists

fun <T> reverseSingleSublist(list: ListNode<T>, start: Int, end: Int) {
    if (start > end) {
        // TODO Does it have to be?  Why not allow reversal in the backward direction?
        throw IllegalArgumentException("End position must be >= start position")
    }
    val start = if (start < 0) 1 else start
    // TODO No fast way to check if end exceeds list length, so we'll check as we move in the list.
    var endNode: ListNode<T>? = null
    var nodeBeforeStart: ListNode<T>? = null
    var nextPosition = 1
    var currentNode = list
    while (true) {
        if (nextPosition == start) {
            nodeBeforeStart = currentNode
        } else if (nextPosition - 1 == end) {
            endNode = currentNode
            break
        }
        currentNode.next?.apply {
            currentNode = this
            ++nextPosition
        } ?: break
    }
    if (nodeBeforeStart == null || endNode == null) {
        throw IllegalArgumentException(
                "Start and end positions must be from 1 to the length of the list ($nextPosition)")
    }
    while (nodeBeforeStart !== endNode) {
        val nodeToMove = nodeBeforeStart?.next
        nodeBeforeStart = nodeBeforeStart?.next
        val nodeAfterEndNode = endNode.next
        endNode.next = nodeToMove
        nodeToMove?.next = nodeAfterEndNode
    }
}