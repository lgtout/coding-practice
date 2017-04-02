package com.lagostout

import spock.lang.Specification
import spock.lang.Unroll

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
        ].collect(collector)

    }

    /**
     * If there is a list in a test's data, prepend it with
     * an empty element.  This makes heap indexing 1-based,
     * instead of 0-based.
     */
    static private Closure collector = { List<Object> it ->
        it.collect {
            if (it instanceof List) {
                ((List)it).add(0, null)
            }
            it
        }
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
        ].collect(collector)

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
        ].collect(collector)
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
        ].collect(collector)
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
        ].collect(collector)
    }

}
