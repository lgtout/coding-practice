package com.lagostout.common

import com.google.common.annotations.VisibleForTesting
import com.lagostout.common.Heaps.HeapPropertyTester

class Heap<T extends Comparable<?>> {

    HeapPropertyTester<T> heapPropertyTester

    int size

    @VisibleForTesting
    List<T> getState() {
        new ArrayList<T>(state)
    }

    private List<T> state

    /**
     * For in-place sorting of items.
     * @param heapPropertyTester
     * @param items Sorted in place.
     */
    Heap(HeapPropertyTester<T> heapPropertyTester, List<T> items) {
        this(heapPropertyTester)
        state = items
        size = state.size()
    }

    Heap(HeapPropertyTester<T> heapPropertyTester) {
        this.heapPropertyTester = heapPropertyTester
        state = []
        size = 0
    }

    void add(T comparable) {
        state.add(comparable)
        size += 1
        def currentNodeIndex = size - 1
        while (true) {
            def parent = parentNodeIndex(currentNodeIndex)
            if (parent != null && !heapPropertyTester.satisfiesHeapProperty(
                    state[parent], state[currentNodeIndex])) {
                swapValue(parent, currentNodeIndex)
                currentNodeIndex = parent
            } else {
                break
            }
        }
    }

    T remove() {
        if (size == 0) return null
        def lastNodeIndex = size - 1
        def firstNodeIndex = 0
        swapValue(firstNodeIndex, lastNodeIndex)
        --size
        pushDown(firstNodeIndex)
        state[lastNodeIndex]
    }

    private void pushDown(int nodeIndex) {
        def currentNodeIndex = nodeIndex
        while (true) {
            def nextNodeIndex = currentNodeIndex
            def leftNodeIndex = leftNodeIndex(currentNodeIndex)
            if (leftNodeIndex != null &&
                    !heapPropertyTester.satisfiesHeapProperty(
                            state[nextNodeIndex], state[leftNodeIndex])) {
                nextNodeIndex = leftNodeIndex
            }
            def rightNodeIndex = rightNodeIndex(currentNodeIndex)
            if (rightNodeIndex != null &&
                    !heapPropertyTester.satisfiesHeapProperty(
                            state[nextNodeIndex], state[rightNodeIndex])) {
                nextNodeIndex = rightNodeIndex
            }
            if (nextNodeIndex == currentNodeIndex) break
            swapValue(nextNodeIndex, currentNodeIndex)
            currentNodeIndex = nextNodeIndex
        }
    }

    void addAll(List<T> comparables) {
        comparables.each { this.add(it) }
    }

    void sort() {
        if (size == 0) return
        def lastParentNodeIndex = parentIndex(size-1)
        (lastParentNodeIndex..0).each { int nodeIndex ->
            pushDown(nodeIndex)
        }
        size.times {
            remove()
        }
    }

    private void swapValue(int nodeIndex, int otherNodeIndex) {
        T otherValue = state[otherNodeIndex]
        state[otherNodeIndex] = state[nodeIndex]
        state[nodeIndex] = otherValue
    }

    private Integer leftNodeIndex(int parentIndex) {
        def index = leftIndex(parentIndex)
        index >= size ? null : index
    }

    private Integer rightNodeIndex(int parentIndex) {
        def index = rightIndex(parentIndex)
        index >= size ? null : index
    }

    private Integer parentNodeIndex(int childIndex) {
        def index = parentIndex(childIndex)
        index < 0 ? null : index
    }

    static <T extends Comparable<?>> void sortAscending(List<T> items) {
        def heap = createMaxHeapInPlace(items)
        heap.sort()
    }

    static <T extends Comparable<?>> void sortDescending(List<T> items) {
        def heap = createMinHeapInPlace(items)
        heap.sort()
    }

    static <T extends Comparable<?>> Heap createMinHeapInPlace() {
        new Heap(new Heaps.MinHeapPropertyComparator<T>())
    }

    static <T extends Comparable<?>> Heap createMinHeapInPlace(List<T> items) {
        new Heap(new Heaps.MinHeapPropertyComparator<T>(), items)
    }

    static <T extends Comparable<?>> Heap createMaxHeapInPlace() {
        new Heap(new Heaps.MaxHeapPropertyTester<T>())
    }

    static <T extends Comparable<?>> Heap createMaxHeapInPlace(List<T> items) {
        new Heap(new Heaps.MaxHeapPropertyTester<T>(), items)
    }

    static int leftIndex(int parentNodeIndex) {
        (parentNodeIndex * 2) + 1
    }

    static int rightIndex(int parentNodeIndex) {
        leftIndex(parentNodeIndex) + 1
    }

    static int parentIndex(int nodeIndex) {
        nodeIndex / 2 - 1
    }

}
