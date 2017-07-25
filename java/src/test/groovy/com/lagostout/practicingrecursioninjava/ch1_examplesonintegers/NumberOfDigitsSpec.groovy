package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class NumberOfDigitsSpec extends Specification {

    @Unroll('#n #expected')
    def 'number of digits'(int n, int expected) {
        expect:
        P7_NumberOfDigits.numberOfDigits(n) == expected

        where:
        [n, expected] << [
                [0, 1],
                [11, 2],
                [9999, 4]
        ]
    }

}
