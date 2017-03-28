package com.lagostout

import spock.lang.Specification
import spock.lang.Unroll

class PriorityQueueSpec extends Specification {

    @Unroll
    'inserts value'(List<Integer> heap, int value,
                    List<Integer> expected) {

        when:
        PriorityQueue.insertValue(heap, value)

        then:
        heap == expected

        where:
        [heap, value, expected] << [
                [[], 1, [1]],
                [[1], 1, [1,1]],
                [[1], 2, [1,2]],
                [[1,2], 3, [1,2,3]],
                [[2,3,4], 1, [1,2,4,3]]
        ]

    }

    @Unroll
    'increases value'(List<Integer> heap, int item,
                      int value, List<Integer> expected) {

        when:
        PriorityQueue.increaseValue(heap, item, value)

        then:
        heap == expected

        where:
        [heap, item, value, expected] << [
                [[1], 0, 2, [2]],
                [[2,1], 0, 3, [3,1]],
                [[2,1], 1, 3, [3,2]],
                [[4,2,3], 1, 5, [5,4,3]],
                [[8,7,6,5,4,3,2,1], 3, 9, [9,8,6,7,4,3,2,1]]
        ].collect(this.&convertHeapsTo1BasedLists)

    }

    @Unroll
    """throws exception when updating value and
    item index is greater than heap size"""(
            List<Integer> heap, int item, int value) {

        when:
        PriorityQueue.increaseValue(heap, item, value)

        then:
        thrown IllegalArgumentException

        where:
        [heap, item, value] << [
                [[1], 1, 2],
                [[1,2], 3, 2]
        ]

    }

    @Unroll
    'decreases value'(List<Integer> heap, int item,
                      int value, List<Integer> expected) {

        when:
        PriorityQueue.decreaseValue(heap, item, value)

        then:
        heap == expected

        where:
        [heap, item, value, expected] << [
                [[2], 0, 1, [1]],
                [[3,1], 0, 2, [2,1]],
                [[3,2], 0, 1, [2,1]],
                [[4,2,3], 1, 1, [4,1,3]],
                [[4,2,3], 0, 1, [3,2,1]],
                [[4,2,3], 0, 3, [3,2,3]],
                [[9,8,7,6,5,4,3,2,1], 1, 1, [9,6,7,2,5,4,3,1,1]]
        ].collect(this.&convertHeapsTo1BasedLists)

    }


    // TODO

    @Unroll
    'updates value'(List<Integer> heap, int item,
                    int value, List<Integer> expected) {

    }

    @Unroll
    'gets maximum'() {

    }

    @Unroll
    'extracts maximum'() {

    }

    def convertHeapsTo1BasedLists(List aCase) {
        [0,3].each { index ->
            (aCase[index] as List).add(0, null)
        }
        aCase
    }

}
