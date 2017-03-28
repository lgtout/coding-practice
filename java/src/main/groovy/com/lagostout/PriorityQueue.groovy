package com.lagostout

import com.google.common.annotations.VisibleForTesting

class PriorityQueue {

    static void insertValue(
            List<Integer> heap, int value) {
        heap.add(-1)
        increaseValue(heap, heap.size() - 1, value)
    }

    static void updateValue(List<Integer> heap, int item, int value) {
        if (item + 1 > heap.size()) {
            throw new IllegalArgumentException()
        }
        if (heap[item] < value) {
            increaseValue(heap, item, value)
        } else if (heap[item] > value) {
            decreaseValue(heap, item, value)
        }
    }

    @VisibleForTesting
    static void increaseValue(
            List<Integer> heap, int item, int value) {
        item += 1
        heap[item] = value
        int parent
        while (item > 1 ) {
            parent = item / 2
            if (heap[parent] > heap[item])
                break
            int temp = heap[item]
            heap[item] = heap[parent]
            heap[parent] = temp
            item = parent
        }
    }

    @VisibleForTesting
    static void decreaseValue(
            List<Integer> heap, int item, int value) {
        item += 1
        heap[item] = value
        Heap.enforceMaxHeapProperty(heap, heap.size() - 1, item)
    }

    static int getMaximum(List<Integer> heap) {
        return heap[0]
    }

    static int extractMaximum(List<Integer> heap) {
        int max = heap[0]
        int lastItemIndex = heap.size() - 1
        heap[0] = heap[lastItemIndex]
        heap[lastItemIndex] = -1
        Heap.enforceMaxHeapProperty(heap, lastItemIndex - 1, 1)
        return max
    }

}
