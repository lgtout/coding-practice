package com.lagostout.common

import spock.lang.Specification
import spock.lang.Unroll

import static com.lagostout.common.Graphs.satisfiesMaxHeapProperty
import static com.lagostout.common.Graphs.satisfiesMinHeapProperty
import static com.lagostout.common.Heap.*

class HeapSpec extends Specification {

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
    static private <T> boolean heapStateIsComplete(List<T> state, int size) {
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

    @Unroll
    'min heap maintains a complete tree'(
            List items, int heapSize, boolean expected) {

        given:
        def heap = createMinHeap()

        when:
        heap.addAll(items)

        then:
        heapStateIsComplete(heap.getState(), heapSize) == expected

        where:
        [items, heapSize, expected] << [
                [[1], 1, true],
                [[1, 2], 1, true],
                [[1, 2], 2, true],
                [[1, 2], 3, false],
                [[1, 2, null], 3, true],
                [[1, null, 2], 3, false],
                [[1, null, 2], 2, true],
                [[1, null, 2], 1, true],
                [[1, 2, null], 1, true],
                [[1, 2, null], 2, true],
                [[1, 2, 3], 3, true],
        ]

    }

    @Unroll
    'max heap maintains a complete tree'(
            List items, int heapSize, boolean expected) {

        given:
        def heap = createMaxHeap()

        when:
        heap.addAll(items)

        then:
        heapStateIsComplete(heap.getState(), heapSize) == expected

        where:
        [items, heapSize, expected] << [
                [[1], 1, true],
                [[1, 2], 1, true],
                [[1, 2], 2, true],
                [[1, 2], 3, false],
                [[1, 2, null], 3, true],
                [[1, null, 2], 3, false],
                [[1, null, 2], 2, true],
                [[1, null, 2], 1, true],
                [[1, 2, null], 1, true],
                [[1, 2, null], 2, true],
                [[1, 2, 3], 3, true],
        ]

    }

    @Unroll
    'min heap maintains a tree that satisfies the min heap property'(
            List items, List expectedState) {

        when:
        def heap = createMinHeap()
        heap.addAll(items)

        then:
        def tree = heap.getState()
        satisfiesMinHeapProperty(tree)

        where:
        [items, expectedState] << [
                [[1], [1]],
                [[1,2], [1,2]],
                [[1,1], [1,1]],
                [[2,1], [1,2]],
                [[1,2,3], [1,2,3]],
                [[3,2,1], [1,2,3]],
                [[3,2,1], [1,2,3]],
        ]

    }

    @Unroll
    'max heap maintains a tree that satisfies the max heap property'(
            List items, List expectedState) {

        when:
        def heap = createMinHeap()
        heap.addAll(items)

        then:
        def tree = heap.getState()
        satisfiesMaxHeapProperty(tree)

        where:
        [items, expectedState] << [
                [[1], [1]],
                [[1,2], [1,2]],
                [[1,1], [1,1]],
                [[2,1], [1,2]],
                [[1,2,3], [1,2,3]],
                [[3,2,1], [1,2,3]],
                [[3,2,1], [1,2,3]],
        ]

    }

    @Unroll
    'removes the max item from the heap'(
            List items, Comparable expected) {

        given:
        def heap = createMaxHeap()

        when:
        heap.addAll(items)

        then:
        heap.remove() == expected
        heap.size() == items.size() - 1
        // A heap can contain duplicate items, so it's insufficient
        // to simply confirm that the removed item is absent.
        heap.getState().count(expected) == items.count(expected) - 1

        where:
        [items, expected] << []

    }

    @Unroll
    'removes the min item from the heap'(
            List items, Comparable expected) {

        given:
        def heap = createMinHeap()
        heap.addAll(items)

        when:
        def removedItem = heap.remove()

        then:
        removedItem == expected
        heap.size() == items.size() - 1
        // A heap can contain duplicate items, so it's insufficient
        // to simply confirm that the removed item is absent.
        heap.getState().count(expected) == items.count(expected) - 1

        where:
        [items, expected] << []

    }

    @Unroll
    'reports the size of the heap'() {

        given:
        def heap = createMaxHeap()

        when:
        heap.addAll([1,2,3,4])

        then:
        heap.size() == 4

        when:
        heap.remove()

        then:
        heap.size() == 3

        when:
        heap.addAll([5,6,7])

        then:
        heap.size() == 6

    }

    @Unroll
    'sorts a collection in ascending order'(
            List<Integer> items, List<Integer> expected) {

        when:
        sortAscending(items)

        then:
        items == expected

        where:
        [items, expected] << [
                [[], []],
                [[1], [1]],
                [[1,2], [1,2]],
                [[2,1], [1,2]],
                [[7,6,5,4,3,2,1], [1,2,3,4,5,6,7]]
        ]

    }

    @Unroll
    'sorts a collection in descending order'(
            List<Integer> items, List<Integer> expected) {

        when:
        sortDescending(items)

        then:
        items == expected

        where:
        [items, expected] << [
                [[], []],
                [[1], [1]],
                [[1,2], [1,2]],
                [[2,1], [1,2]],
                [[7,6,5,4,3,2,1], [1,2,3,4,5,6,7]]
        ]

    }

}
