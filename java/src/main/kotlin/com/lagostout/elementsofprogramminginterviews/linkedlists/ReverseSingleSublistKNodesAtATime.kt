package com.lagostout.elementsofprogramminginterviews.linkedlists

/**
 * Problem 8.2.3 page 117
 */
object ReverseSingleSublistKNodesAtATime {

    data class ListNode<T>(var data: T? = null, var next: ListNode<T>? = null)

    fun <T> reverseSingleSublistKNodesAtATime(
            list: ListNode<T>, k: Int): ListNode<T>? {
        val nodeBeforeFirstInList = ListNode(next = list)
        var nodeBeforeGroup = nodeBeforeFirstInList
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
            firstNodeInGroup = lastNodeInReversedGroup?.next
            // TODO Figure out if/how to update nodeBeforeGroup
//            nodeBeforeGroup = lastNodeInReversedGroup!!
            val firstNodeInList = nodeBeforeFirstInList.next
            nodeBeforeFirstInList.next = firstNodeInReversedGroup
            lastNodeInReversedGroup?.next = firstNodeInList
            nodeBeforeGroup.next = firstNodeInGroup
        }
        return null
    }

}
