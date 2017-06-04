package com.lagostout.common

import org.apache.commons.collections4.Bag
import org.apache.commons.collections4.bag.HashBag
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

        expect:
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
    'pops the max item #expectedItem from the heap #items'(
            List items, int expectedSize, Comparable expectedItem) {

        given:
        def heap = createMaxHeap()
        heap.addAll(items)
        def itemCount = items.count(expectedItem)
        def expectedItemCount = itemCount >= 1 ? itemCount - 1 : 0

        when:
        def maxItem = heap.pop()

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
    'pops the min item #expectedItem from the heap #items'(
            List items, int expectedSize, Comparable expectedItem) {

        given:
        def heap = createMinHeap()
        heap.addAll(items)
        def itemCount = items.count(expectedItem)
        def expectedItemCount = itemCount >= 1 ? itemCount - 1 : 0

        when:
        def minItem = heap.pop()

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
        def heap = createMaxHeap()

        when:
        heap.addAll([1,2,3,4])

        then:
        heap.size == 4

        when:
        heap.pop()

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

    @Unroll
    'updates a max-heap item'(Heap<Integer> heap, int oldValue,
                              int newValue, Bag<Integer> expected) {
        when:
        heap.update(oldValue, newValue)

        then:
        expected == new HashBag<Integer>(heap.state)

        where:
        [heap, oldValue, newValue, expected] << [
                [[1,2,3], 2, 3],
                [[3,4,5,9,1,2,4], 1, 9],
        ].collect { List<Integer> items, int _oldValue, int _newValue ->
            def _expected = new HashBag(items)
            _expected.remove(_oldValue, 1)
            _expected.add(_newValue)
            def _heap = createMaxHeap()
            _heap.addAll(items)
            [_heap, _oldValue, _newValue, _expected]
        }

    }

    @Unroll
    'updates a min-heap item'(Heap<Integer> heap, int oldValue,
                              int newValue, Bag<Integer> expected) {
        when:
        heap.update(oldValue, newValue)

        then:
        expected == new HashBag<Integer>(heap.state)

        where:
        [heap, oldValue, newValue, expected] << [
                [[1,2,3], 2, 3],
                [[3,4,5,9,1,2,4], 1, 9],
        ].collect { List<Integer> items, int _oldValue, int _newValue ->
            def _expected = new HashBag(items)
            _expected.remove(_oldValue, 1)
            _expected.add(_newValue)
            def _heap = createMinHeap()
            _heap.addAll(items)
            [_heap, _oldValue, _newValue, _expected]
        }

    }

    @Unroll
    def 'peeks at the top of a min-heap without altering the heap'(
            List<Integer> items, int expected) {

        when:
        def heap = createMinHeap()
        heap.addAll(items)

        then:
        heap.peek() == expected
        heap.size == items.size()

        where:
        [items, expected] << [
                [[1,1,2,3], 1],
                [[2,3,1], 1],
                [[3,4,5,9,1,2,4], 1]]

    }

    def 'peeks at the top of a max-heap without altering the heap'(
        List<Integer> items, int expected) {

            when:
            def heap = createMaxHeap()
            heap.addAll(items)

            then:
            heap.peek() == expected
            heap.size == items.size()

            where:
            [items, expected] << [
                    [[1,1,2,3], 3],
                    [[2,3,1], 3],
                    [[3,4,5,9,1,2,4], 9]]

    }

}
