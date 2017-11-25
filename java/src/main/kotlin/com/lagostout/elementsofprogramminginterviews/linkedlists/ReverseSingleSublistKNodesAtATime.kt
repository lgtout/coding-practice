package com.lagostout.elementsofprogramminginterviews.linkedlists

/**
 * Problem 8.2.3 page 117
 */
object ReverseSingleSublistKNodesAtATime {

    @Suppress("UnnecessaryVariable")
    fun <T> reverseSingleSublistKNodesAtATime(
            list: LinkedListNode<T>, k: Int): LinkedListNode<T> {

        fun seekLastNodeOfGroup(firstNodeInGroup: LinkedListNode<T>): LinkedListNode<T>? {
            var currentNode: LinkedListNode<T> = firstNodeInGroup
            var groupCount = 1
            while (groupCount < k) {
                currentNode = currentNode.next ?: break
                ++groupCount
            }
            return if (groupCount < k) null else currentNode
        }

        var firstInList = list

        // Find last node of last group
        var lastNodeOfLastGroup: LinkedListNode<T> = firstInList
        var firstNodeOfLastGroup: LinkedListNode<T> = firstInList
        while (true) {
            seekLastNodeOfGroup(firstNodeOfLastGroup)?.let {
                lastNodeOfLastGroup = it
                it.next?.let {
                    firstNodeOfLastGroup = it
                }
            } ?: break
        }

        // Reverse list
        while (true) {

            // Reverse the nodes in the group
            var lastNodeInGroup = seekLastNodeOfGroup(firstInList)?: break
            val lastNodeInGroupAfterReversal = firstInList
            while (lastNodeInGroup != firstInList) {
                val node = firstInList
                firstInList = firstInList.next!!
                node.next = lastNodeInGroup.next
                lastNodeInGroup.next = node
            }

            // We're done if we've reversed the last group
            if (firstInList == lastNodeOfLastGroup) break

            // Reverse the group in the list: insert it after
            // the last node of the rightmost group that has
            // not yet been reversed.
            lastNodeInGroup = lastNodeInGroupAfterReversal
            val node = firstInList
            lastNodeInGroup.next?.let {
                firstInList = it
                lastNodeInGroup.next = lastNodeOfLastGroup.next
                lastNodeOfLastGroup.next = node
            }
        }

        return firstInList
    }

}
