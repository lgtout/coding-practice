package com.lagostout.common

import spock.lang.Specification
import spock.lang.Unroll

import static com.lagostout.common.Heaps.heapStateIsComplete

class HeapsSpec extends Specification {

    // TODO
    // Test satisfiesHeapProperty(...).  Not a high priority
    // since I'm already using it, and it appears to work
    // correctly.

    @Unroll
    'min heap maintains a complete tree'(
            List<Integer> heap, int heapSize, boolean expected) {

        expect:
        heapStateIsComplete(heap, heapSize) == expected

        where:
        [heap, heapSize, expected] << [
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
}
