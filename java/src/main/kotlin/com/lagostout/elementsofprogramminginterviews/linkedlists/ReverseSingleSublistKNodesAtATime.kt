package com.lagostout.elementsofprogramminginterviews.linkedlists

/**
 * Problem 8.2.3 page 117
 */
object ReverseSingleSublistKNodesAtATime {

    data class ListNode<T>(var data: T? = null, var next: ListNode<T>? = null)

    fun <T> reverseSingleSublistKNodesAtATime(
            list: ListNode<T>, k: Int): ListNode<T>? {
        val nodeBeforeFirstInList = ListNode(next = list)
        val nodeBeforeGroup = nodeBeforeFirstInList.copy()
        var firstNodeInGroup = nodeBeforeFirstInList.next
        while (true) {
            var lastNodeInGroup = firstNodeInGroup

            // Find the group's tail node
            var groupNodeCount = 1
            while (groupNodeCount < k ) {
                lastNodeInGroup = lastNodeInGroup?.next?.let {
                    it
                } ?: break
                ++groupNodeCount
            }

            // Not enough nodes to make a group
            if (groupNodeCount < k) break

            // Reverse the nodes in the group
            val firstNodeInReversedGroup = lastNodeInGroup
            val lastNodeInReversedGroup = firstNodeInGroup
            while (firstNodeInReversedGroup != nodeBeforeGroup.next) {
                val node = nodeBeforeGroup.next!!
                nodeBeforeGroup.next = node.next
                node.next = firstNodeInReversedGroup?.next
                firstNodeInReversedGroup?.next = node
            }

            // Reverse the group in the list
            firstNodeInGroup = firstNodeInReversedGroup
            lastNodeInGroup = lastNodeInReversedGroup!!
//            nodeBeforeGroup = lastNodeInGroup
            nodeBeforeGroup.next = lastNodeInGroup.next
            lastNodeInGroup.next = nodeBeforeFirstInList.next
            nodeBeforeFirstInList.next = firstNodeInGroup
        }
        return null
    }

}
