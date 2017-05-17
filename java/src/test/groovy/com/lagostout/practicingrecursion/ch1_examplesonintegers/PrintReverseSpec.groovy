package com.lagostout.practicingrecursion.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class PrintReverseSpec extends Specification {

    @Unroll
    def 'print reverse'(int n, int expected) {
        expect:
        P20_PrintReverse.printReverse(n) == expected
        where:
        [n, expected] << [
                [1, 1],
                [12, 21],
                [21, 12]
        ]
    }

}
