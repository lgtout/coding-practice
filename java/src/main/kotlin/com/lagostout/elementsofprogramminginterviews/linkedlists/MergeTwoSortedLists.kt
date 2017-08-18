package com.lagostout.elementsofprogramminginterviews.linkedlists

data class ListNode<T>(var data: T, var next: ListNode<T>? = null)

fun <T : Comparable<T>> mergeTwoSortedLists(
        firstList: ListNode<T>?, secondList: ListNode<T>?): ListNode<T>? {
    firstList ?: return secondList
    secondList ?: return firstList
    var (mergedList, otherList) = if (firstList.data > secondList.data)
        Pair<ListNode<T>, ListNode<T>?>(secondList, firstList) else
        Pair<ListNode<T>, ListNode<T>?>(firstList, secondList)
//    var currentMergedListNode = mergedList
//    while (true) {
//        if (currentMergedListNode.next == null ||
//                currentMergedListNode.next!!.data > otherList.data) {
//            currentMergedListNode.next = otherList
//            otherList = otherList.next
//
//        }
//    }
    return null
}