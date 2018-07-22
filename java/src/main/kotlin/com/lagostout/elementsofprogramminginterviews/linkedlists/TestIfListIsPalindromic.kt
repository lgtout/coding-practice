package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.common.isOdd

/* Problem 8.11.1 page 128 */

fun <T> listIsPalindromic(list: LinkedListNode<T>): Boolean {
    val count = list.count()
    if (count == 1) return true
    val countOfSublistToReverse = count / 2
    val nodeAfterSublistToReverse = list.advance(countOfSublistToReverse)
    var head = list
    val reversedSublistEndNode = head
    while (reversedSublistEndNode.next != nodeAfterSublistToReverse) {
        val node = reversedSublistEndNode.next
        reversedSublistEndNode.next = node!!.next
        node.next = head
        head = node
    }
    var rightHalfNode = nodeAfterSublistToReverse.let {
        if (count.isOdd) it.next else it
    }
    var leftHalfNode = head
    var isPalindromic = true
    while (leftHalfNode != nodeAfterSublistToReverse) {
        if (leftHalfNode.data != rightHalfNode!!.data) {
            isPalindromic = false
            break
        }
        leftHalfNode = leftHalfNode.next!!
        rightHalfNode = rightHalfNode.next
    }
    return isPalindromic
}