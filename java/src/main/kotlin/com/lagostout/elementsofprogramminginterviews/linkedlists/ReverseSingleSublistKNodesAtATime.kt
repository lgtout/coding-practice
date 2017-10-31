package com.lagostout.elementsofprogramminginterviews.linkedlists

/**
 * Problem 8.2.3 page 117
 */
object ReverseSingleSublistKNodesAtATime {

    @Suppress("UnnecessaryVariable")
    fun <T> reverseSingleSublistKNodesAtATime(
            list: ListNode<T>, k: Int): ListNode<T> {
        var firstInList = list
        val lastNodeInPreviousGroup = firstInList.copy()

        fun seekLastNodeOfGroup(firstNodeInGroup: ListNode<T>): ListNode<T>? {
            var currentNode: ListNode<T> = firstNodeInGroup
            var groupCount = 1
            while (groupCount < k) {
                currentNode = currentNode.next ?: break
                ++groupCount
            }
            return if (groupCount < k) null else currentNode
        }

        // Find last node of last group
        var lastNodeOfLastGroup: ListNode<T> = firstInList
        var firstNodeOfGroup: ListNode<T> = firstInList
        while (true) {
            println("firstNodeOfGroup $firstNodeOfGroup")
            seekLastNodeOfGroup(firstNodeOfGroup)?.let {
                lastNodeOfLastGroup = it
                it.next?.let {
                    firstNodeOfGroup = it
                }
            } ?: break
        }

        // Reverse list
        var firstNodeInGroup = firstInList
        while (true) {
            // We're done if we've reversed the last group
            if (firstInList == lastNodeOfLastGroup) break
            var lastNodeInGroup = seekLastNodeOfGroup(firstNodeInGroup)!!

            // TODO
            // This isn't right yet.
            // Where is lastNodeInPreviousGroup updated?

            // Reverse the nodes in the group
            val firstNodeInReversedGroup = lastNodeInGroup
            val lastNodeInReversedGroup = firstNodeInGroup
            while (firstNodeInReversedGroup != lastNodeInPreviousGroup.next) {
                val node = lastNodeInPreviousGroup.next!!
                lastNodeInPreviousGroup.next = node.next
                node.next = firstNodeInReversedGroup.next
                firstNodeInReversedGroup.next = node
            }
            firstNodeInGroup = firstNodeInReversedGroup
            lastNodeInGroup = lastNodeInReversedGroup

            // Reverse the group in the list: insert it after
            // the last node of the rightmost group that will
            // be reversed.
            firstInList = lastNodeInGroup.next!!
            lastNodeInGroup.next = lastNodeOfLastGroup.next
            lastNodeOfLastGroup.next = firstNodeInGroup
        }

        return firstInList
    }

}
