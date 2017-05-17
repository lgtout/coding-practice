package com.lagostout.practicingrecursion.ch2_examplesonarrays

import spock.lang.Specification
import spock.lang.Unroll

class PrintArraySpec extends Specification {
    @Unroll('#a #n #expected')
    def 'print array'(int[] a, int n, String expected) {
        expect:
        P1_PrintArray.printArray(a, n) == expected
        where:
        [a, n, expected] << [
                [[1,2,3], 3, '1 2 3'],
                [[1], 1, '1']
        ]
    }
}
