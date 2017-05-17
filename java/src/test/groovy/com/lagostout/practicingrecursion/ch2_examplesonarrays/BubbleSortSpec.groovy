package com.lagostout.practicingrecursion.ch2_examplesonarrays

import spock.lang.Specification
import spock.lang.Unroll

class BubbleSortSpec extends Specification {
    @Unroll('#a #expected')
    def 'bubble sort'(int[] a, int[] expected) {
        expect:
        P20_BubbleSort.bubbleSort(a, a.length)
        a == expected
        where:
        [a, expected] << [
                [[4,3,2,1], [1,2,3,4]],
                [[1],[1]]
        ]
    }
}
