package com.lagostout.elementsofprogramminginterviews.linkedlists

/**
 * Problem 8.1 page 115
 */
object MergeTwoSortedLists {

    data class ListNode<T>(var data: T, var next: ListNode<T>? = null)

    fun <T : Comparable<T>> mergeTwoSortedLists(
            firstList: ListNode<T>?, secondList: ListNode<T>?): ListNode<T>? {
        firstList ?: return secondList
        secondList ?: return firstList
        val mergedListHead: ListNode<T>
        var otherListCurrentNode: ListNode<T>?
        val lists = listOf(firstList, secondList).sortedWith(
                Comparator { (data), (data2) -> data.compareTo(data2) })
        mergedListHead = lists[0]
        otherListCurrentNode = lists[1]
        var mergedListCurrentNode = mergedListHead
        var finished = false
        while (!finished) {
            finished = otherListCurrentNode?.let { otherNode ->
                mergedListCurrentNode.next?.let { mergedNextNode ->
                    if (otherNode.data < mergedNextNode.data) {
                        otherListCurrentNode = otherNode.next
                        mergedListCurrentNode.next = otherNode
                        mergedListCurrentNode = otherNode
                        mergedListCurrentNode.next = mergedNextNode
                    } else {
                        mergedListCurrentNode = mergedNextNode
                    }
                    false
                } ?: run {
                    mergedListCurrentNode.next = otherNode
                    true
                }
            } ?: true
        }
        return mergedListHead
    }
}
