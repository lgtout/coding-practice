package com.lagostout.elementsofprogramminginterviews.heaps

import java.util.*

/**
 * Problem 11.7.2 page 188
 */
class ImplementQueueUsingHeap {

    class HeapItem<T : Comparable<T>>(val data: T, val priority: Int)

    class QueueUsingHeap<T : Comparable<T>> {
        var count: Int = 0

        val heap = PriorityQueue<HeapItem<T>>(
               { i, j -> i.priority.compareTo(j.priority) })

        fun add(data: T) {
            heap.add(HeapItem(data, count++))
        }

        fun remove(): T {
            return heap.remove().data
        }

    }

}
