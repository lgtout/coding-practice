package com.lagostout.common

import spock.lang.Specification
import spock.lang.Unroll

import static com.lagostout.common.FixedCapacityHeap.createFixedCapacityMaxHeap
import static com.lagostout.common.FixedCapacityHeap.createFixedCapacityMinHeap
import static org.apache.commons.collections4.CollectionUtils.isEqualCollection

class FixedCapacityHeapSpec extends Specification {

    // TODO
    // Test Heapable API - This very low priority since
    // FixedCapacityHeap primarily delegates to the Heaps.

    @Unroll
    'min heap discards largest item when heap at capacity and item added'() {

        given:
        def heap = createFixedCapacityMinHeap(2)
        heap.addAll([2,4])

        when:
        heap.add(5)

        then:
        isEqualCollection(heap.copyOfState, [2, 4])

        when:
        heap.add(1)

        then:
        isEqualCollection(heap.copyOfState, [1, 2])

        when:
        heap.add(1)

        then:
        isEqualCollection(heap.copyOfState, [1, 1])

    }

    @Unroll
    'max heap discards smallest item when heap at capacity and item added'() {

        given:
        def heap = createFixedCapacityMaxHeap(2)
        heap.addAll([2,4])

        when:
        heap.add(5)

        then:
        isEqualCollection(heap.copyOfState, [5, 4])

        when:
        heap.add(1)

        then:
        isEqualCollection(heap.copyOfState, [5, 4])

        when:
        heap.add(5)

        then:
        isEqualCollection(heap.copyOfState, [5, 5])

    }

    def 'min heap does not discard items when items added to heap without exceeding capacity'() {

        given:
        def heap = createFixedCapacityMinHeap(3)

        when:
        heap.add(2)

        then:
        isEqualCollection(heap.copyOfState, [2])
        heap.size == 1

        when:
        heap.add(3)

        then:
        isEqualCollection(heap.copyOfState, [2,3])
        heap.size == 2

    }

    def 'max heap does not discard items when items added to heap without exceeding capacity'() {

        given:
        def heap = createFixedCapacityMaxHeap(3)

        when:
        heap.add(2)

        then:
        isEqualCollection(heap.copyOfState, [2])
        heap.size == 1

        when:
        heap.add(3)

        then:
        isEqualCollection(heap.copyOfState, [2,3])
        heap.size == 2

    }

}
