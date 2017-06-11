package com.lagostout.common

import com.google.common.annotations.VisibleForTesting

class FixedCapacityHeap<T extends Comparable<?>> implements Heapable<T> {

    int capacity
    Heap<T> reverseHeap
    Heap<T> heap

    FixedCapacityHeap(Heap<T> heap, Heap<T> reverseHeap, int capacity) {
        this.heap = heap
        this.reverseHeap = reverseHeap
        this.capacity = capacity
    }

    static <T extends Comparable<?>> FixedCapacityHeap createFixedCapacityMinHeap(int capacity) {
        new FixedCapacityHeap(
                Heap.<T>createMinHeap(),
                Heap.<T>createMaxHeap(),
                capacity)
    }

    static <T extends Comparable<?>> FixedCapacityHeap createFixedCapacityMaxHeap(int capacity) {
        new FixedCapacityHeap(
                Heap.<T>createMaxHeap(),
                Heap.<T>createMinHeap(),
                capacity)
    }

    @Override
    T peek() {
        heap.peek()
    }

    @Override
    T pop() {
        def item = heap.pop()
        reverseHeap.remove(item)
        item
    }

    @Override
    void add(T comparable) {
        heap.add(comparable)
        reverseHeap.add(comparable)
        if (heap.size > capacity) {
            def itemAtOppositeEndOfOrder = reverseHeap.pop()
            heap.remove(itemAtOppositeEndOfOrder)
        }
    }

    @Override
    void addAll(Iterable<T> comparables) {
        comparables.each {
            add(it)
        }
    }

    @Override
    void update(T oldValue, T newValue) {
        heap.update(oldValue, newValue)
        reverseHeap.update(oldValue, newValue)
    }

    @Override
    void remove(T value) {
        heap.remove(value)
        reverseHeap.remove(value)
    }

    @Override
    int getSize() {
        heap.size
    }

    @Override
    List<T> asList() {
        heap.asList()
    }

    @Override
    boolean isEmpty() {
        heap.isEmpty()
    }

    @VisibleForTesting
    List<T> getCopyOfState() {
        new ArrayList<>(heap.copyOfState)
    }

}
