package com.lagostout.elementsofprogramminginterviews.linkedlists

/**
 * Problem 8.3.1 page 118
 */
fun <T> computeCycleStartNode(list: LinkedListNode<T>): Pair<Boolean, LinkedListNode<T>?> {
    var cycleStart: LinkedListNode<T>? = null
    var cycleExists = false
    var pointer1: LinkedListNode<T>? = list
    var pointer2: LinkedListNode<T>? = pointer1
    var pointer1Distance = 0
    // Find cycle
    while (true) {
        pointer2 = pointer2?.next
        if (pointer2 == pointer1 || pointer2?.next == null) break
        pointer2 = pointer2.next
        if (pointer2 == pointer1 || pointer2?.next == null) break
        pointer1 = pointer1?.next ?: break
        ++pointer1Distance
    }
    // Find cycle start
    if (pointer1 == pointer2) {
        // Count nodes in cycle
        var cycleDistance = 0
        do {
            pointer2 = pointer2?.next?.also {
                ++cycleDistance
            }
        } while (pointer2 != null && pointer2 != pointer1)
        if (cycleDistance > 0) {
            cycleExists = true
            var pointer3: LinkedListNode<T>? = list
            while (cycleDistance > pointer1Distance) {
                --cycleDistance
                pointer2 = pointer2?.next
            }
            while (pointer1Distance > cycleDistance) {
                --pointer1Distance
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
    }
    return Pair(cycleExists, cycleStart)
}