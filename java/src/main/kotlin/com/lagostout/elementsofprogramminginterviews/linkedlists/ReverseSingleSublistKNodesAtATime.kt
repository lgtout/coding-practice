package com.lagostout.elementsofprogramminginterviews.linkedlists

/**
 * Problem 8.2.3 page 117
 */
object ReverseSingleSublistKNodesAtATime {

    data class ListNode<T>(var data: T? = null, var next: ListNode<T>? = null) {
        val hasNext: Boolean
            get() = next != null
    }

    @Suppress("UnnecessaryVariable")
    fun <T> reverseSingleSublistKNodesAtATime(
            list: ListNode<T>, k: Int): ListNode<T>? {
        var firstInList = list
        val pointerToLastNodeInPreviousGroup = firstInList.copy()

        fun seekLastNodeOfGroup(firstNodeInGroup: ListNode<T>): ListNode<T>? {
            var currentNode: ListNode<T> = firstNodeInGroup
            var groupCount = 0
            while (groupCount < k) {
                currentNode = currentNode.next?: break
                ++groupCount
            }
            return if (groupCount < k) null else currentNode
        }

        // Find last node of last group
        var lastNodeOfLastGroup: ListNode<T> = firstInList
        var firstNodeOfGroup = firstInList
        while (true) {
            lastNodeOfLastGroup = seekLastNodeOfGroup(firstNodeOfGroup)?.also {
                firstNodeOfGroup = it
            }?: break
        }

        // Reverse list
        var firstNodeInGroup = firstInList
        while (true) {
            // We're done if we've reversed the last group
            if (firstInList == lastNodeOfLastGroup) break
            var lastNodeInGroup = seekLastNodeOfGroup(firstNodeInGroup)!!

            // Reverse the nodes in the group
            val firstNodeInReversedGroup = lastNodeInGroup
            val lastNodeInReversedGroup = firstNodeInGroup
            while (firstNodeInReversedGroup != pointerToLastNodeInPreviousGroup.next) {
                val node = pointerToLastNodeInPreviousGroup.next!!
                pointerToLastNodeInPreviousGroup.next = node.next
                node.next = firstNodeInReversedGroup.next
                firstNodeInReversedGroup.next = node
            }
            firstNodeInGroup = firstNodeInReversedGroup
            lastNodeInGroup = lastNodeInReversedGroup

            // Reverse the group in the list
            firstInList = lastNodeInGroup.next!!
            lastNodeInGroup.next = lastNodeOfLastGroup.next
            lastNodeOfLastGroup.next = firstNodeInGroup
        }

        return firstInList
    }

}
