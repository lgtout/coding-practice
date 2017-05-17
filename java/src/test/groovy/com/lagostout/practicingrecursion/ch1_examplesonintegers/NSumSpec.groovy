package com.lagostout.practicingrecursion.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class NSumSpec extends Specification {

    @Unroll('#n #expected')
    def 'sum of first n'(int n, int expected) {

        expect:
        P10_NSum.nSum(n) == expected

        where:
        [n, expected] << [
                [3, 6],
                [1, 1]
        ]
    }
}
