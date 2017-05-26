package com.lagostout.common

import com.google.common.annotations.VisibleForTesting
import com.lagostout.common.Heaps.HeapPropertyTester

class Heap<T extends Comparable<?>> {

    HeapPropertyTester<T> heapPropertyTester

    int size

    @VisibleForTesting
    List<Comparable<T>> getState() {
        return new ArrayList<Comparable<T>>(state)
    }

    private List<Comparable<T>> state

    Heap(HeapPropertyTester<T> heapPropertyTester) {
        this.heapPropertyTester = heapPropertyTester
        state = []
        size = 0
    }

    void add(Comparable<T> comparable) {
        state.add(comparable)
        size += 1
        def currentNode = [size - 1, state] as HeapNode
        while (currentNode != null) {
            def parent = currentNode.parent()
            if (!heapPropertyTester.satisfiesHeapProperty(
                    parent, currentNode)) {
                parent.swapValue(currentNode)
                currentNode = parent
            } else {
                break
            }
        }
    }

    Comparable<T> remove() {
        null
    }

    void addAll(List<Comparable<T>> comparables) {
        comparables.each { this.add(it) }
    }

    static void sortAscending(List<Comparable<T>> items) {
        null
    }

    static void sortDescending(List<Comparable<T>> items) {
        null
    }

    static <T> Heap createMinHeap() {
        new Heap(new Heaps.MinHeapPropertyTester<T>())
    }

    static <T> Heap createMaxHeap() {
        new Heap(new Heaps.MaxHeapPropertyTester<T>())
    }

}
