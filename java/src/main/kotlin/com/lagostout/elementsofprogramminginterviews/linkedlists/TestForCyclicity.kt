package com.lagostout.elementsofprogramminginterviews.linkedlists

/**
 * Problem 8.3 page 118
 */
fun <T> computeCycleStartNode(list: ListNode<T>): ListNode<T>? {
    var cycleStart: ListNode<T>? = null
    var pointer1: ListNode<T>? = list
    var pointer2: ListNode<T>? = pointer1
    var pointer1Distance = 0
    // Find cycle
    while (true) {
        pointer1 = pointer1?.next ?: break
        ++pointer1Distance
        pointer2 = pointer2?.next
        if (pointer2 == pointer1 || pointer2?.next == null) break
        pointer2 = pointer2.next
        if (pointer2 == pointer1 || pointer2?.next == null) break
    }
    // Find cycle start
    if (pointer1 == pointer2) {
        // Count nodes in cycle
        var cycleDistance = 0
        pointer2 = pointer1
        do {
            pointer2 = pointer2?.next
            ++cycleDistance
        } while (pointer2 != pointer1)
        var pointer3: ListNode<T>? = list
        (0..pointer1Distance - cycleDistance).forEach {
            pointer3 = pointer3?.next
        }
        // Now pointer3 and pointer2 are
        // the same distance from pointer1.
        while (pointer2 != pointer3) {
            pointer2 = pointer2?.next
            pointer3 = pointer3?.next
        }
        cycleStart = pointer2
    }
    return cycleStart
}