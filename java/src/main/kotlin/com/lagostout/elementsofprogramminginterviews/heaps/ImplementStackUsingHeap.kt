package com.lagostout.elementsofprogramminginterviews.heaps

import java.util.*

class StackUsingHeap<T> {
    class PriorityQueueItem<T>(val value: T, val order: Int) :
            Comparable<PriorityQueueItem<T>> {
        override fun compareTo(other: PriorityQueueItem<T>): Int {
            return order.compareTo(other.order)
        }
    }
    val heap = PriorityQueue<PriorityQueueItem<T>>(
            Comparator<PriorityQueueItem<T>> { item1, item2 ->
                item2.compareTo(item1)
    })
    fun push(value: T) {
        heap.add(PriorityQueueItem(value, heap.size))
    }
    fun pop(): T {
        return heap.remove().value
    }
}

