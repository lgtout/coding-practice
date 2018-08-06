package com.lagostout.elementsofprogramminginterviews.linkedlists

/* Problem 8.11.2 page 128 */

fun <T> doublyLinkedListIsPalindrome(
        list: LinkedListNode<T>, tail: LinkedListNode<T>): Boolean {
    var leftNode = list
    var rightNode = tail
    var isPalindrome = true
    while (true) {
        if (leftNode.data != rightNode.data) {
            isPalindrome = false
            break
        }
        if (leftNode.next == rightNode ||
                leftNode == rightNode) break
        leftNode = leftNode.next!!
        rightNode = rightNode.previous!!
    }
    return isPalindrome
}