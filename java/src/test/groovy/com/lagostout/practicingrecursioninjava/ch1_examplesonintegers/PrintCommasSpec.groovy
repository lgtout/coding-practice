package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class PrintCommasSpec extends Specification {
    @Unroll('#n #expected')
    def 'print commas'(long n, String expected) {
        expect:
        P28_PrintCommas.printCommas(n) == expected
        where:
        [n, expected] << [
                [12345, '12,345'],
                [123, '123'],
                [1000, '1,000'],
                [1000000, '1,000,000'],
                [10, '10']
        ]
    }
}
