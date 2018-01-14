package com.lagostout.elementsofprogramminginterviews.linkedlists

/* Problem 8.7 page 123 */

fun <T> removeKthLastElement(list: LinkedListNode<T>, k: Int): LinkedListNode<T>? {
    var count = 1
    var pointer1 = list
    var pointer2 = list
    while (count < k + 1) {
        pointer1 = pointer1.next ?: break
        ++count
    }
    return when {
        count < k -> null
        count == k -> pointer2
        else -> {
            while (true) {
                pointer1.next?.let {
                    pointer1 = it
                } ?: break
                pointer2.next?.let {
                    pointer2 = it
                }
            }
            val kthNode = pointer2.next
            pointer2.next = kthNode?.next
            kthNode?.apply {
                next = null
            }
        }
    }
}
