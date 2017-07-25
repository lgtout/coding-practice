package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class PrintAFactorialTimesSpec extends Specification {
    @Unroll('#n #expected')
    def 'print A factorial times'(int n, String expected) {
        expect:
        P33_PrintAFactorialTimes.printAFactorialTimes(n) == expected
        where:
        [n, expected] << [
                [2, 'AA'],
                [3, 'AAAAAA'],
                [1, 'A']
        ]
    }
}
