package com.lagostout.practicingrecursioninjava.ch2_examplesonarrays

import spock.lang.Specification
import spock.lang.Unroll

class BinarySearchSpec extends Specification {
    @Unroll('#a #key #expected')
    def 'binary search'(int[] a, int key, int expected) {
        expect:
        P22_BinarySearch.binarySearch(a, key, 0, a.length - 1) == expected
        where:
        [a, key, expected] << [
                [[1,2,3,4], 2, 1],
//                [[1,2,3,4], 5, -1],
                [[1,2,3,4], 5, 3],
                [[1,2], 1, 0],
                [[2], 2, 0],
                [[2], 1, -1],
                [[2,4], 3, 0],
                [[2,4], 5, 2]
//                [[2], 1, -1],
        ]
    }
}
