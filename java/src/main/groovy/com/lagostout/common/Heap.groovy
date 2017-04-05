package com.lagostout.common

import java.util.function.BiFunction

class Heap {

    static void buildMaxHeap(List<Integer> items, int heapSize) {
        // We only enforce the heap property on all non-leaf vertices
        for (int vertex = lastParent(heapSize); vertex >= 0; vertex--) {
            enforceMaxHeapProperty(items, heapSize, vertex)
        }
    }

    static void buildMinHeap(List<Integer> items, int heapSize) {
        // We only enforce the heap property on all non-leaf vertices
        for (int vertex = lastParent(heapSize); vertex >= 0; vertex--) {
            enforceMinHeapProperty(items, heapSize, vertex)
        }
    }

    /**
     * Sorts a collection in place, using heap sort.
     * @param items
     */
    static void sort(List<Integer> items) {
        buildMaxHeap(items, items.size())
        def lastIndex = items.size() - 1

        // Swap the first (largest) item with the last item in the
        // heap.  Reduce the heap size by 1.  And re-enforce the
        // heap property on the first item.
        while (lastIndex > 0) {
            def temp = items[lastIndex]
            items.set(lastIndex, items[0])
            items.set(0, temp)
            enforceMaxHeapProperty(items, --lastIndex, 0)
        }

        items.drop(0)
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

        if (heapSize <= 1) return

        if (vertexIndex >= heapSize || vertexIndex < 0)
            throw new IllegalArgumentException()

        while (true) {
            int indexOfLargestOrSmallestItem = vertexIndex
            int largestOrSmallestItem = items[vertexIndex]

            // Compare value at vertexIndex with left child.
            // Update largest/smallest item index if heap
            // property violated.
            int leftIndex = getLeftChildIndex(vertexIndex)
            if (leftIndex < heapSize) {
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
            if (rightIndex < heapSize) {
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
        (i * 2) + 1
    }

    static int getRightChildIndex(int i) {
        getLeftChildIndex(i) + 1
    }

    static int getParentIndex(int i) {
        (i - 1) / 2
    }

    static int lastParent(int heapSize) {
        getParentIndex(heapSize - 1)
    }

}
