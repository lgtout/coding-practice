package com.lagostout.practicingrecursioninjava.ch2_examplesonarrays

import spock.lang.Specification
import spock.lang.Unroll

class SelectionSortSpec extends Specification {
    @Unroll('#a #expected')
    def 'selection sort'(int[] a, int[] expected) {
        expect:
        P18_SelectionSort.selectionSort(a, a.length)
        a == expected
        where:
        [a, expected] << [
                [[4,3,2,1], [1,2,3,4]],
                [[2,0,1,0], [0,0,1,2]],
        ]
    }
}
