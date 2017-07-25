package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class GCDSpec extends Specification {

    @Unroll('#m #n #expected')
    def 'gcd'(int m, int n, int expected) {

        expect:
        P9_GCD.gcd(m, n) == expected

        where:
        [m, n, expected] << [
                [5,3,1],
                [3,5,1],
                [6,3,3],
                [1,1,1]
        ]

    }

}
