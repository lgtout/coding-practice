package com.lagostout.elementsofprogramminginterviews.linkedlists

/**
 * Problem 8.2.3 page 117
 */
object ReverseSingleSublistKNodesAtATime {

    data class ListNode<T>(var data: T? = null, var next: ListNode<T>? = null)

    fun <T> reverseSingleSublistKNodesAtATime(
            list: ListNode<T>, k: Int): ListNode<T>? {
        val head = ListNode(next = list)
        var groupHead = head
        var groupTail: ListNode<T> = groupHead
        var currentNode = list
        var previousGroupHeadAndTail: Pair<ListNode<T>, ListNode<T>>? = null
        var headGroupHeadAndTail: Pair<ListNode<T>, ListNode<T>>? = null
        while (true) {
            // Find the group's tail node
            var groupNodeCount = 0
            while (groupNodeCount <= k ) {
                groupTail = groupTail.next?.let {
                    it
                } ?: break
                ++groupNodeCount
            }
            // Not enough nodes to make a group
            if (groupNodeCount < k) break


        }
        return null
    }

}
