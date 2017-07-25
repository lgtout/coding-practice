package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class PrintALogNTimesSpec extends Specification {

    @Unroll('#n #expected')
    def 'print A log N times'(int n, String expected) {
        expect:
        P27_PrintALogNTimes.printALogNTimes(n) == expected
        where:
        [n, expected] << [
                [4, 'AA'],
                [0, ''],
                [1, '']
        ]
    }
}
