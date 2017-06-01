package com.lagostout.common

import spock.lang.Specification
import spock.lang.Unroll

import static Heaps.heapStateIsComplete
import static com.lagostout.common.Heap.*
import static com.lagostout.common.Heaps.satisfiesMaxHeapProperty
import static com.lagostout.common.Heaps.satisfiesMinHeapProperty

class HeapSpec extends Specification {

    @Unroll
    'min heap maintains a complete tree'(List items) {

        given:
        def heap = createMinHeapInPlace()

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
        def heap = createMaxHeapInPlace()

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
        def heap = createMinHeapInPlace()
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
        def heap = createMaxHeapInPlace()

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
    'removes the max item #expectedItem from the heap #items'(
            List items, int expectedSize, Comparable expectedItem) {

        given:
        def heap = createMaxHeapInPlace()
        heap.addAll(items)
        def itemCount = items.count(expectedItem)
        def expectedItemCount = itemCount >= 1 ? itemCount - 1 : 0

        when:
        def maxItem = heap.remove()

        then:
        maxItem == expectedItem
        heap.size == expectedSize
        // A heap can contain duplicate items, so it's insufficient
        // to simply confirm that the removed item is absent.
        heap.getState().take(heap.size)
                .count(expectedItem) == expectedItemCount

        where:
        [items, expectedSize, expectedItem] << [
                [[], 0, null],
                [[4], 0, 4],
                [[4,2,5,5,1], 4, 5],
                [[1,1,1,4,1,1], 5, 4],
                [[-1, -4, -8, -2], 3, -1],
        ]

    }

    @Unroll
    'removes the min item #expectedItem from the heap #items'(
            List items, int expectedSize, Comparable expectedItem) {

        given:
        def heap = createMinHeapInPlace()
        heap.addAll(items)
        def itemCount = items.count(expectedItem)
        def expectedItemCount = itemCount >= 1 ? itemCount - 1 : 0

        when:
        def minItem = heap.remove()

        then:
        minItem == expectedItem
        heap.size == expectedSize
        // A heap can contain duplicate items, so it's insufficient
        // to simply confirm that the removed item is absent.
        heap.getState().take(heap.size)
                .count(expectedItem) == expectedItemCount

        where:
        [items, expectedSize, expectedItem] << [
                [[], 0, null],
                [[4], 0, 4],
                [[4,4], 1, 4],
                [[4,2,5,5,1], 4, 1],
                [[1,1,1,4,1,1], 5, 1],
                [[-1, -4, -8, -2], 3, -8],
        ]

    }

    @Unroll
    'reports the size of the heap'() {

        given:
        def heap = createMaxHeapInPlace()

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
                [[1,3,7,4,2], [1,2,3,4,7]],
                [[1,2,3,7,4,7,2], [1,2,2,3,4,7,7]],
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
                [[1,3,7,4,2], [1,2,3,4,7]],
                [[1,2,3,7,4,7,2], [1,2,2,3,4,7,7]],
                [[2,1], [1,2]],
                [[7,6,5,4,3,2,1], [1,2,3,4,5,6,7]]
        ].collect {
            [it[0], it[1].reverse()]
        }

    }

}
