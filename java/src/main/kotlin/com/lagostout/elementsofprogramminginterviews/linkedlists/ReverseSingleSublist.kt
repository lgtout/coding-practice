package com.lagostout.elementsofprogramminginterviews.linkedlists

object ReverseSingleSublist {
    data class ListNode<T>(var data: T? = null, var next: ListNode<T>? = null)

    @Suppress("NAME_SHADOWING")
    fun <T> reverseSingleSublist(list: ListNode<T>, start: Int, end: Int) {
        if (start == end) return

        var start = start
        var end = end

        var lastNode = list
        var listSize = 1
        while (true) {
            ++listSize
            lastNode.next?.let {
                lastNode = it
            } ?: break
        }
        listOf(start, end).find { it < 1 || it > listSize} ?:
            throw IllegalArgumentException("Start and end positions must be >= 1 and <= list size")

        if (listSize == 1) return

        if (start > end) {
            val temp = start
            start = end
            end = temp
        }

        var nodeBeforeSublistStart: ListNode<T>? = null
        var sublistEnd: ListNode<T>? = null
        var currentPosition = 0
        val head = ListNode(next = list)
        var currentNode = head
        while (true) {
            if (currentPosition + 1 == start)
                nodeBeforeSublistStart = currentNode
            else if (currentPosition == end) {
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