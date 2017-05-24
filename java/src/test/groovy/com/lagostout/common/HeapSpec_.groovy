package com.lagostout.common

import spock.lang.Specification
import spock.lang.Unroll

class HeapSpec_ extends Specification {

    @Unroll
    'sorts collection - static'(List<Integer> items,
                                List<Integer> expected) {

        when:
        Heap_.sort(items)

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
    'builds max heap - static'(
            List<Integer> items, List<Integer> expected) {

        when:
        Heap_.buildMaxHeap(items, items.size())

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
        ]

    }

    @Unroll
    'enforces max heap property - static'(
            List<Integer> items, int vertexIndex,
            List<Integer> expected) {

        expect:
        Heap_.enforceMaxHeapProperty(items, items.size(), vertexIndex)

        where:
        [items, vertexIndex, expected] << [
                [[], 0, []],
                [[1], 0, [1]],
                [[2,1], 0, [2,1]],
                [[1,2], 0, [2,1]],
                [[3,2,1], 0, [3,2,1]],
                [[1,2,3], 0, [3,2,1]],
                [[1,3,2], 0, [3,1,2]],
                [[1,2,3,4,5,6,7], 0, [3,2,7,4,5,6,1]],
        ]
    }

    @Unroll
    'builds min heap'(List<Integer> items,
                      List<Integer> expected) {
        when:
        Heap_.buildMinHeap(items, items.size())

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
        ]
    }

    @Unroll
    'enforces min heap property - static'(
            List<Integer> items, int vertexIndex,
            List<Integer> expected) {

        expect:
        Heap_.enforceMinHeapProperty(items, items.size(), vertexIndex)

        where:
        [items, vertexIndex, expected] << [
                [[], 0, []],
                [[1], 0, [1]],
                [[1,2], 0, [1,2]],
                [[2,1], 0, [1,2]],
                [[1,2,3], 0,[1,2,3]],
                [[3,2,1], 0, [1,2,3]],
                [[3,1,2], 0, [1,3,2]],
                [[7,6,5,4,3,2,1], 0, [1,3,2,4,6,7,5]],
        ]
    }

}
