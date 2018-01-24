package com.lagostout.elementsofprogramminginterviews.linkedlists

/* Problem 8.8.1 page 124 */

fun <T: Comparable<T>> removeDuplicatesFromSortedList(list: LinkedListNode<T>) {
    var distinctEntriesTail = list
    var currentNode = list
    while (true) {
        if (currentNode.data != distinctEntriesTail.data) {
            distinctEntriesTail.next = currentNode
            distinctEntriesTail = currentNode
        }
        currentNode.next?.let {
            currentNode = it
        } ?: break
    }
}
