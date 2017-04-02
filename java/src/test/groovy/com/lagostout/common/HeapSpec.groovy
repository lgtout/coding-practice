package com.lagostout.common

import spock.lang.Specification
import spock.lang.Unroll
import static com.lagostout.util.HeapUtil.toOneBasedHeap

class HeapSpec extends Specification {

    @Unroll
    'sorts collection - static'(List<Integer> items,
                                List<Integer> expected) {

        when:
        Heap.sort(items)

        then:
        items == expected

        where:
        [items, expected] << [
                [[], []],
                [[1], [1]],
                [[1,2], [1,2]],
                [[2,1], [1,2]],
                [[7,6,5,4,3,2,1], [1,2,3,4,5,6,7]]
        ].collect(toOneBasedHeap)

    }

    @Unroll
    'builds max heap - static'(
            List<Integer> items, List<Integer> expected) {

        when:
        Heap.buildMaxHeap(items, items.size() - 1)

        then:
        items == expected

        where:
        [items, expected] << [
                [[], []],
                [[1], [1]],
                [[2,1], [2,1]],
                [[1,2], [2,1]],
                [[3,2,1], [3,2,1]],
                [[1,2,3], [3,2,1]],
                [[1,2,3,6,5,4], [6,5,4,2,1,3]]
        ].collect(toOneBasedHeap)

    }

    @Unroll
    'enforces max heap property - static'(
            List<Integer> items, int vertexIndex,
            List<Integer> expected) {

        expect:
        Heap.enforceMaxHeapProperty(items, items.size() - 1, vertexIndex)

        where:
        [items, vertexIndex, expected] << [
                [[], 0, []],
                [[1], 1, [1]],
                [[2,1], 1, [2,1]],
                [[1,2], 1, [2,1]],
                [[3,2,1], 1, [3,2,1]],
                [[1,2,3], 1, [3,2,1]],
                [[1,3,2], 1, [3,1,2]],
                [[1,2,3,4,5,6,7], 1, [3,2,7,4,5,6,1]],
        ].collect(toOneBasedHeap)
    }

    @Unroll
    'builds min heap'(List<Integer> items,
                      List<Integer> expected) {
        when:
        Heap.buildMinHeap(items, items.size() - 1)

        then:
        items == expected

        where:
        [items, expected] << [
                [[], []],
                [[1], [1]],
                [[2,1], [1,2]],
                [[1,2], [1,2]],
                [[3,2,1], [1,2,3]],
                [[1,2,3], [1,2,3]],
                [[7,6,5,4,3,2,1], [1,3,2,4,6,7,5]]
        ].collect(toOneBasedHeap)
    }

    @Unroll
    'enforces min heap property - static'(
            List<Integer> items, int vertexIndex,
            List<Integer> expected) {

        expect:
        Heap.enforceMinHeapProperty(items, items.size() - 1, vertexIndex)

        where:
        [items, vertexIndex, expected] << [
                [[], 0, []],
                [[1], 1, [1]],
                [[1,2], 1, [1,2]],
                [[2,1], 1, [1,2]],
                [[1,2,3], 1,[1,2,3]],
                [[3,2,1], 1, [1,2,3]],
                [[3,1,2], 1, [1,3,2]],
                [[7,6,5,4,3,2,1], 1, [1,3,2,4,6,7,5]],
        ].collect(toOneBasedHeap)
    }

}
