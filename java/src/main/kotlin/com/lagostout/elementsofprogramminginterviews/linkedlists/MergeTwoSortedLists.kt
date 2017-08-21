package com.lagostout.elementsofprogramminginterviews.linkedlists

data class ListNode<T>(var data: T, var next: ListNode<T>? = null)

fun <T : Comparable<T>> mergeTwoSortedLists(
        firstList: ListNode<T>?, secondList: ListNode<T>?): ListNode<T>? {
    firstList ?: return secondList
    secondList ?: return firstList
    val mergedListHead: ListNode<T>
    var otherList: ListNode<T>?
    val lists = listOf(firstList, secondList).sortedWith(
            Comparator { (data), (data2) -> data.compareTo(data2) })
    mergedListHead = lists[0]
    otherList = lists[1]
    var mergedListCurrentNode = mergedListHead
    while (true) {
        otherList?.let { otherListNode ->
            mergedListCurrentNode.next?.let { mergedListNextNode ->
                if (otherListNode.data < mergedListNextNode.data) {
                    otherList = otherListNode.next
                    mergedListCurrentNode.next = otherListNode
                    mergedListCurrentNode = otherListNode
                }
                mergedListCurrentNode.next = mergedListNextNode
            }
        } ?: break
    }
    return mergedListHead
}