package com.lagostout.practicingrecursioninjava.ch2_examplesonarrays

import spock.lang.Specification
import spock.lang.Unroll

class InsertionSortSpec extends Specification {
    @Unroll('#a #expected')
    def 'insertion sort'(int[] a, int[] expected) {
        expect:
        P19_InsertionSort.insertionSort(a, a.length)
        a == expected
        where:
        [a, expected] << [
                [[4,3,2,1], [1,2,3,4]],
                [[2,0,1,0], [0,0,1,2]],
        ]
    }
}
