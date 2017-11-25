package com.lagostout.elementsofprogramminginterviews.linkedlists

/**
 * Problem 8.4 page 119
 */
fun <T> listsAreOverlapping(list1: LinkedListNode<T>,
                            list2: LinkedListNode<T>): Boolean {
    var listsOverlap = false
    var list1Node = list1
    var list1NodeCount = 1
    while (true) {
        list1Node.next?.let {
            list1Node = it
            ++list1NodeCount
        } ?: break
    }
    var list2Node = list2
    var list2NodeCount = 1
    while (true) {
        list2Node.next?.let {
            list2Node = it
            ++list2NodeCount
        } ?: break
    }
    listOf(Pair(list1NodeCount, list1),
            Pair(list2NodeCount, list2)).sortedBy { it.first }.let {
        (shorter, longer) ->
        var longerListNodeCount = 1
        val listLengthDifference = longer.first - shorter.first
        var longerListNode = longer.second
        while (longerListNodeCount < listLengthDifference) {
            longerListNode.next?.let {
                longerListNode = it
            }
            ++longerListNodeCount
        }
        var shorterListNode = shorter.second
        while (true) {
            if (shorterListNode == longerListNode) {
                listsOverlap = true
                break
            }
            shorterListNode.next?.let {
                shorterListNode = it
            }
            longerListNode.next?.let {
                longerListNode = it
            }
        }
    }
    return listsOverlap
}