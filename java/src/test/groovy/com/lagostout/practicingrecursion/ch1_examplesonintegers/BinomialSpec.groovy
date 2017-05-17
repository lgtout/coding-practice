package com.lagostout.practicingrecursion.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class BinomialSpec extends Specification {

    @Unroll('#n #k #expected')
    def 'binomial'(int n, int k, int expected) {
        expect:
        P22_Binomial.binomial(n,k) == expected
        where:
        [n, k, expected] << [
                [0, 0, 1],
                [1, 0, 1],
                [1, 1, 1],
                [2, 0, 1],
                [2, 1, 2],
                [2, 2, 1],
                [3, 0, 1],
                [3, 1, 3],
                [3, 2, 3],
                [3, 3, 1],
        ]
    }
}
