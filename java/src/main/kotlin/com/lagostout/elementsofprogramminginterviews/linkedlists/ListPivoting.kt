package com.lagostout.elementsofprogramminginterviews.linkedlists

/* Problem 8.12 page 129 */

fun <T : Comparable<T>> listPivoting(head: LinkedListNode<T>, k: T) {

    val pointerToHead = LinkedListNode(next = head)

    // Find pointers to first and last pivot nodes
    var pointerToFirstPivotNode: LinkedListNode<T>? = null
    var pointerToLastPivotNode: LinkedListNode<T>? = null
    var pointerToCurrentNode: LinkedListNode<T>? = pointerToHead
    while (true) {
        if (pointerToCurrentNode?.next?.data == k) {
            if (pointerToFirstPivotNode == null) {
                pointerToFirstPivotNode = pointerToCurrentNode
                pointerToLastPivotNode = pointerToCurrentNode
            } else pointerToLastPivotNode = pointerToCurrentNode
        } else if (pointerToLastPivotNode != null) break
        pointerToCurrentNode = pointerToCurrentNode?.next ?: break
    }

    // Pivot wasn't found
    if (pointerToFirstPivotNode == null) return

    // Find less-than segments from first pivot node to last node and move to left of first pivot node
    pointerToCurrentNode = pointerToFirstPivotNode
    while (true) {
        pointerToCurrentNode?.next ?: break
        val currentValue = pointerToCurrentNode.next?.data!!
        if (currentValue < k) {
            val temp = pointerToCurrentNode.next!!
            pointerToCurrentNode.next = temp.next
            temp.next = pointerToFirstPivotNode!!.next
            pointerToFirstPivotNode.next = temp
            pointerToFirstPivotNode = temp
        }
        pointerToCurrentNode = pointerToCurrentNode.next
    }

    // Find greater-than segments from first node to last pivot node and move to right of last pivot node
    pointerToCurrentNode = pointerToHead
    while (true) {
        val currentNode = pointerToCurrentNode?.next
        if (currentNode == null || currentNode == pointerToLastPivotNode?.next) break
        val currentValue = currentNode.data!!
        if (currentValue > k) {
            pointerToCurrentNode?.next = currentNode.next
            currentNode.next = pointerToFirstPivotNode!!.next
            pointerToFirstPivotNode.next = currentNode
            pointerToFirstPivotNode = currentNode
        }
        pointerToCurrentNode = pointerToCurrentNode?.next
    }

}