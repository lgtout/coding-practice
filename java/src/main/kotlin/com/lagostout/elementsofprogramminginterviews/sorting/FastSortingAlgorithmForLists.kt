package com.lagostout.elementsofprogramminginterviews.sorting

import com.lagostout.elementsofprogramminginterviews.linkedlists.LinkedListNode
import com.lagostout.elementsofprogramminginterviews.linkedlists.advance
import com.lagostout.elementsofprogramminginterviews.linkedlists.count

/* Problem 14.9 page 255 */

fun <T : Comparable<T>> fastSort(head: LinkedListNode<T>): LinkedListNode<T> {

    fun sort(head: LinkedListNode<T>, size: Int): LinkedListNode<T> {

        if (size <= 1) return head

        // Split

        val leftHalfSize  = size / 2
        val lastNodeOfLeftHalf = head.advance(
            leftHalfSize - 1)
        var rightHalfHead = lastNodeOfLeftHalf.next
        lastNodeOfLeftHalf.next = null
        val rightHalfSize = size - leftHalfSize
        var leftHalfHead: LinkedListNode<T>? =
                sort(head, leftHalfSize)
        rightHalfHead = sort(rightHalfHead!!, rightHalfSize)

        // Merge

        val sentinel: LinkedListNode<T> = LinkedListNode()
        var tail = sentinel
        while (leftHalfHead != null && rightHalfHead != null) {
            if (leftHalfHead.data!! <= rightHalfHead.data!!) {
                tail.next = leftHalfHead
                leftHalfHead = leftHalfHead.next
            } else if (leftHalfHead.data!! > rightHalfHead.data!!) {
                tail.next = rightHalfHead
                rightHalfHead = rightHalfHead.next
            }
            tail = tail.next!!
            tail.next = null
        }

        tail.next = leftHalfHead ?: rightHalfHead
        return sentinel.next!!
    }

    return sort(head, head.count())
}