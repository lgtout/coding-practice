package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import java.util.*

/* Problem 9.10 page 148 */

class QueueWithMaxAPI {

    data class MaxCount(val max: Int, val count: Int)

    private val queue = LinkedList<Int>()
    private val deque = LinkedList<MaxCount>()

    fun enqueue(entry: Int) {
        queue.add(entry)
        var count = 0
        while (true) {
            if (deque.isEmpty()) break
            val (previousMax, previousCount) = deque.last()
            if (previousMax > entry) break
            count += previousCount
            deque.removeLast()
        }
        deque.addLast(MaxCount(entry, count + 1))
    }

    fun dequeue(): Int {
        deque.removeFirst().let {
            val count = it.count - 1
            if (count > 0)
                deque.addFirst(it.copy(count = count))
        }
        return queue.remove()
    }

    fun max(): Int {
        return deque.first().max
    }

}