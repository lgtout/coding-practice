package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.elementsofprogramminginterviews.linkedlists.ReverseSingleSublist.reverseSingleSublist

fun <T> reverseList(list: LinkedListNode<T>): LinkedListNode<T> {
    var nodeCount = 1
    list.run {
        var node = this
        while (node.next != null) {
            node = node.next?.let {
                nodeCount++
                it
            } ?: break
        }
    }
    return reverseSingleSublist(list, 1, nodeCount)
}
