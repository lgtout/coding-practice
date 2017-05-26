package com.lagostout.common

import spock.lang.Specification
import spock.lang.Unroll

import static com.lagostout.common.Heap.*
import static Heaps.heapStateIsComplete
import static com.lagostout.common.Heaps.satisfiesMaxHeapProperty
import static com.lagostout.common.Heaps.satisfiesMinHeapProperty

class HeapSpec extends Specification {

    @Unroll
    'min heap maintains a complete tree'(List items) {

        given:
        def heap = createMinHeap()

        when:
        heap.addAll(items)

        then:
        heapStateIsComplete(heap.getState())

        where:
        items << [
                [1],
                [1,2],
                [1,1],
                [2,1],
                [1,2,3],
                [3,2,1],
                [2,3,1],
        ]

    }

    @Unroll
    'max heap maintains a complete tree'(List items) {

        given:
        def heap = createMaxHeap()

        when:
        heap.addAll(items)

        then:
        heapStateIsComplete(heap.getState())

        where:
        items << [
                [1],
                [1,2],
                [1,1],
                [2,1],
                [1,2,3],
                [3,2,1],
                [2,3,1],
        ]

    }

    @Unroll
    """min heap maintains a tree that satisfies
     the min heap property"""(List items) {

        when:
        def heap = createMinHeap()
        heap.addAll(items)

        then:
        def tree = heap.getState()
        satisfiesMinHeapProperty(tree)

        where:
        items << [
                [1],
                [1,2],
                [1,1],
                [2,1],
                [1,2,3],
                [3,2,1],
                [2,3,1],
        ]

    }

    @Unroll
    """max heap maintains a tree that satisfies the
    max heap property"""(List items) {

        given:
        def heap = createMaxHeap()

        when:
        heap.addAll(items)

        then:
        def tree = heap.getState()
        satisfiesMaxHeapProperty(tree)

        where:
        items << [
                [1],
                [1,2],
                [1,1],
                [2,1],
                [1,2,3],
                [3,2,1],
                [2,3,1],
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
        heap.size == items.size() - 1
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
        heap.size == items.size() - 1
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
        heap.size == 4

        when:
        heap.remove()

        then:
        heap.size == 3

        when:
        heap.addAll([5,6,7])

        then:
        heap.size == 6

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
