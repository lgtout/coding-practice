package com.lagostout.elementsofprogramminginterviews.linkedlists

/* Problem 8.8.2 page 125 */

fun <T : Comparable<T>> removeEntriesFromListWhenThereAreMoreThanMDuplicates(
        list: LinkedListNode<T>, m: Int): LinkedListNode<T>? {
    val sentinelNode = LinkedListNode(next = list)
    var filteredEntriesTail: LinkedListNode<T> = sentinelNode
    var currentNode = sentinelNode
    var duplicateCount = 0
    while (true) {
        currentNode.next?.let {
            currentNode = it
            while (true) {
                currentNode.next?.let {
                    if (it.data == currentNode.data) {
                        ++duplicateCount
                        currentNode = it
                    } else null
                } ?: break
            }
            if (duplicateCount == m) {
                filteredEntriesTail.next = currentNode.next
            }
            filteredEntriesTail = currentNode
        } ?: break
    }
    return sentinelNode.next
}