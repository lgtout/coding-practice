package com.lagostout.practicingrecursion.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class PrintAExpTimesSpec extends Specification {
    @Unroll('#n #expected')
    def 'print A exponent times'(int n, String expected) {
        expect:
        P29_PrintAExpTimes.printAExpTimes(n) == expected
        where:
        [n, expected] << [
                [2, 'AAAA'],
                [3, 'AAAAAAAA'],
                [0, 'A'],
                [1, 'AA']
        ]
    }
}
