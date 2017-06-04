package com.lagostout.common

import com.google.common.annotations.VisibleForTesting
import com.lagostout.common.Heaps.HeapPropertyTester

// https://www.hackerearth.com/practice/data-structures/trees/heapspriority-queues/tutorial/

class PriorityQueue<T extends Comparable<?>> {

    Heap<T> heap

    PriorityQueue(List<T> items,
                  HeapPropertyTester heapPropertyTester) {
        heap = new Heap<>(heapPropertyTester, items)
    }

    @VisibleForTesting
    PriorityQueue(Heap<T> heap) {
        this.heap = heap
    }

    void insert(T value) {
        heap.add(value)
    }

    void update(T oldValue, T newValue) {
        heap.update(oldValue, newValue)
    }

    T peek() {
        heap.peek()
    }

    T pop() {
        heap.pop()
    }

}
