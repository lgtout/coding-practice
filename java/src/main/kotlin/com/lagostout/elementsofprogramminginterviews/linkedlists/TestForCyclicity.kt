package com.lagostout.elementsofprogramminginterviews.linkedlists

/**
 * Problem 8.3 page 118
 */
fun <T> computeCycleStartNode(list: ListNode<T>): ListNode<T>? {
    var pointer1: ListNode<T>? = list
    var pointer2: ListNode<T>? = pointer1
    var index = 0
    // Find cycle
    while (true) {
        ++index
        pointer1 = pointer1?.next
        pointer2 = pointer2?.next
        if (pointer2 == pointer1 || pointer2?.next == null) break
        pointer2 = pointer2.next
        if (pointer2 == pointer1 || pointer2?.next == null) break
    }
    // Find cycle start
    if (pointer1 == pointer2) {
        // Count nodes in cycle
        var cycleNodeCount = 1
        while (pointer2?.next != pointer1) {
            pointer2 = pointer2?.next
            ++cycleNodeCount
        }
        var visitCount = 0
        pointer2 = list
        while (visitCount < 2) {
            pointer2 = pointer2?.next
//            if (pointer2 == pointer)
        }
    }
    return null
}