package com.lagostout.common

class Heaps {

    static <T> boolean heapStateIsComplete(List<T> state) {
        heapStateIsComplete(state, state.size())
    }

    /**
     * A heap's state is complete when any null entries, if they're
     * present, occupy a contiguous range that terminates at the
     * last index that's included in the heap's state.  If the heap's
     * size is n, then the last included index is n-1.  This is true
     * regardless of the actual size data structure used to store state.
     *
     * @param state A heap's internal state
     * @param size Size of the heap.  Not the size of the data structure
     * used to store its state.
     * @return Whether the state complies with completeness constraints
     * of a heap of the given size.
     */
    static <T> boolean heapStateIsComplete(List<T> state, int size) {
        def isComplete = true
        if (state.size() < size) isComplete = false
        else {
            def foundFirstEmptyPosition = false
            for (i in (0..(size-1))) {
                def value = state[i]
                if (!foundFirstEmptyPosition) {
                    foundFirstEmptyPosition = foundFirstEmptyPosition ||
                            value == null
                } else {
                    isComplete = value == null
                    if (!isComplete) break
                }
            }
        }
        isComplete
    }

    static <T extends Comparable<?>> boolean satisfiesMinHeapProperty(
            List<T> heap) {
        satisfiesHeapProperty(heap, new MinHeapPropertyTester<T>())
    }

    static <T extends Comparable<?>> boolean satisfiesMaxHeapProperty(
            List<T> heap) {
        satisfiesHeapProperty(heap, new MaxHeapPropertyTester<T>())
    }

    static <T extends Comparable<?>> boolean satisfiesHeapProperty(
            List<T> heap, HeapPropertyTester<T> heapPropertyTester) {
        satisfiesHeapProperty(0, heap, heapPropertyTester)
    }

    static <T extends Comparable<?>> boolean satisfiesHeapProperty(
            int nodeIndex, List<T> heap,
            HeapPropertyTester<T> heapPropertyTester) {
        def leftNodeIndex = Heap.leftIndex(nodeIndex)
        if (leftNodeIndex >= heap.size()) return true
        def rightNodeIndex = Heap.rightIndex(nodeIndex)
        if (rightNodeIndex >= heap.size()) return true
        if (!heapPropertyTester.satisfiesHeapProperty(
                heap[nodeIndex], heap[leftNodeIndex]) ||
                !heapPropertyTester.satisfiesHeapProperty(
                        heap[nodeIndex], heap[rightNodeIndex])) {
            return false
        }
        satisfiesHeapProperty(leftNodeIndex, heap, heapPropertyTester) &&
                satisfiesHeapProperty(rightNodeIndex, heap, heapPropertyTester)
    }

    static interface HeapPropertyTester<T extends Comparable<?>> {
        boolean satisfiesHeapProperty(
                T parentValue, T childValue)
    }

    static class MinHeapPropertyTester<T extends Comparable<?>>
            implements HeapPropertyTester<T> {
        @Override
        boolean satisfiesHeapProperty(
                T parentValue, T childValue) {
            parentValue <= childValue
        }
    }

    static class MaxHeapPropertyTester<T extends Comparable<?>>
            implements HeapPropertyTester<T> {
        @Override
        boolean satisfiesHeapProperty(
                T parentValue, T childValue) {
            parentValue >= childValue
        }
    }
}
