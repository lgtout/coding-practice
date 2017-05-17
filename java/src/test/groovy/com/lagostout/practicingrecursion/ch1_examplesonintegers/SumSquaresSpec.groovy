package com.lagostout.practicingrecursion.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class SumSquaresSpec extends Specification {

    @Unroll('#n')
    def 'sums squares'(int n, int expected) {
        expect:
        P11_SumSquares.sumSquares(n) == expected
        where:
        [n, expected] << [
                [2, 5],
                [3, 14]
        ]
    }

}
