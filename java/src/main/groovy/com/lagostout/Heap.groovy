package com.lagostout

import java.util.function.BiFunction

class Heap {

    static void buildMaxHeap(List<Integer> items, int heapSize) {
        // We only enforce the heap property on the first half
        // of items in the heap i.e. all rows but the bottom-most.
        for (int vertex = heapSize/2; vertex >= 1; vertex--) {
            enforceMaxHeapProperty(items, heapSize, vertex)
        }
    }

    static void buildMinHeap(List<Integer> items, int heapSize) {
        // We only enforce the heap property on the first half
        // of items in the heap i.e. all rows but the bottom-most.
        for (int vertex = heapSize/2; vertex >= 1; vertex--) {
            enforceMinHeapProperty(items, heapSize, vertex)
        }
    }

    /**
     * Sorts a collection in place, using heap sort.
     * @param items
     */
    static void sort(List<Integer> items) {
        // Heap size is the number of items stored,
        // _not_ the size of the array.
        def heapSize = items.size() - 1
        buildMaxHeap(items, heapSize)
        def lastIndex = heapSize

        // Swap the first (largest) item with the last item in the
        // heap.  Reduce the heap size by 1.  And re-enforce the
        // heap property on the first item.
        while (lastIndex > 1) {
            def temp = items[lastIndex]
            items.set(lastIndex, items[1])
            items.set(1, temp)
            enforceMaxHeapProperty(items, --lastIndex, 1)
        }
    }

    /**
     * Given a vertex, enforces the max heap property on
     * it and on any vertices it modifies.
     */
    static void enforceMaxHeapProperty(
            List<Integer> items, int heapSize, int vertex) {
        enforceHeapProperty(items, heapSize, vertex,
                { item, maxOrMinItem -> item > maxOrMinItem })
    }

    /**
     * Given a vertex, enforces the min heap property on
     * it and on any vertices it modifies.
     */
    static void enforceMinHeapProperty(
            List<Integer> items, int heapSize, int vertex) {
        enforceHeapProperty(items, heapSize, vertex,
                { item, maxOrMinItem -> item < maxOrMinItem })
    }

    /**
     * Given a vertexIndex, enforces the heap property on
     * it and on any vertices it modifies.
     */
    static void enforceHeapProperty(
            List<Integer> items, int heapSize, int vertexIndex,
            BiFunction<Integer, Integer, Boolean> comparator) {

        if (heapSize <= 0) return
        int lastItemIndex = items.size() - 1
        if (heapSize > lastItemIndex ||
                vertexIndex > heapSize ||
                vertexIndex < 1)
            throw new IllegalArgumentException()
        if (vertexIndex < 0) return

        while (true) {
            int indexOfLargestOrSmallestItem = vertexIndex
            int largestOrSmallestItem = items[vertexIndex]

            // Compare value at vertexIndex with left child.
            // Update largest/smallest item index if heap
            // property violated.
            int leftIndex = getLeftChildIndex(vertexIndex)
            if (leftIndex <= heapSize) {
                int leftItem = items[leftIndex]
                if (comparator.apply(leftItem, largestOrSmallestItem)) {
                    indexOfLargestOrSmallestItem = leftIndex
                    largestOrSmallestItem = leftItem
                }
            }

            // Compare value at vertexIndex with right child.
            // Update largest/smallest item index if heap
            // property violated.
            int rightIndex = getRightChildIndex(vertexIndex)
            if (rightIndex <= heapSize) {
                int rightItem = items[rightIndex]
                if (comparator.apply(rightItem, largestOrSmallestItem)) {
                    indexOfLargestOrSmallestItem = rightIndex
                    largestOrSmallestItem = rightItem
                }
            }

            // If vertexIndex isn't the largest item, swap,
            // so that the max/min heap property will be
            // enforced on the updated vertexIndex on the
            // next iteration of the while-loop.
            // If we haven't updated the vertexIndex in the
            // current iteration, then the value at that
            // position is either in its final position,
            // or we've reached the bottom row, where no
            // vertices have children.  In either case,
            // we're done, and we should exit the while-loop.
            if (indexOfLargestOrSmallestItem != vertexIndex) {
                int temp = items[vertexIndex]
                items[vertexIndex] = largestOrSmallestItem
                items[indexOfLargestOrSmallestItem] = temp
                vertexIndex = indexOfLargestOrSmallestItem
            } else {
                break
            }
        }
    }

    static int getLeftChildIndex(int i) {
        i << 1
    }

    static int getRightChildIndex(int i) {
        (i << 1) | 1
    }

    static int getParentIndex(int i) {
        i >> 1
    }

}
