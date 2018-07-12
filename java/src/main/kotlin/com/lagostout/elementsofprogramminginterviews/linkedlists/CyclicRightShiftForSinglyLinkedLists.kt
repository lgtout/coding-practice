package com.lagostout.elementsofprogramminginterviews.linkedlists

/* Problem 8.9 page 125 */

fun <T> cyclicRightShiftForSinglyLinkedLists(list: LinkedListNode<T>, k: Int): LinkedListNode<T> {
    if (list.next == null) return list
    val listCount = list.count()
    val shiftCount = k.rem(listCount)
    if (shiftCount == 0) return list
    val endNode = list.advance(listCount)
    val positionOfNewEndNode = listCount - shiftCount - 1
    val newEndNode = list.advance(positionOfNewEndNode)
    val newFirstNode = newEndNode.next
    newEndNode.next = null
    endNode.next = list
    return newFirstNode!!
}