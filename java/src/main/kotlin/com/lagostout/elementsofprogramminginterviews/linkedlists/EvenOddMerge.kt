package com.lagostout.elementsofprogramminginterviews.linkedlists

/* Problem 8.10 page 127 */

fun <T> evenOddMerge(list: LinkedListNode<T>) {
    var previousEvenNode = list
    var oddNode = list.next
    while (true) {
        val nextEvenNode = oddNode?.next ?: break
        oddNode.next = nextEvenNode.next
        nextEvenNode.next = previousEvenNode.next
        previousEvenNode.next = nextEvenNode
        previousEvenNode = nextEvenNode
        oddNode = oddNode.next
    }
}