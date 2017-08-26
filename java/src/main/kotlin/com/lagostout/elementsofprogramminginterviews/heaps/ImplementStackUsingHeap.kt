package com.lagostout.elementsofprogramminginterviews.heaps

import java.util.*

class StackUsingHeap<T> {
    data class PriorityQueueItem<out T>(val value: T, val priority: Int)

    val heap = PriorityQueue<PriorityQueueItem<T>>(
            Comparator<PriorityQueueItem<T>> { item1, item2 ->
                item2.priority.compareTo(item1.priority)
    })

    fun push(value: T) {
        heap.add(PriorityQueueItem(value, heap.size))
    }

    fun pop(): T {
        return heap.remove().value
    }

    override fun toString(): String {
        return "StackUsingHeap(heap=$heap)"
    }
}

