package com.lagostout.practicingrecursion.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class InvNSumSpec extends Specification {
    @Unroll('#n #expected')
    def 'sum of n inverses'(int n, double expected) {
        expect:
        P12_InvNSum.invNSum(n) == expected
        where:
        [n, expected] << [
                [1, 1],
                [2, 1.5]
        ]
    }
}
