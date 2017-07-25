package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class PowerSpec extends Specification {

    @Unroll('#base #exponent #expected')
    def 'raise to power'(double base, int exponent, double expected) {

        expect:
//        P4_Power.power1(base, exponent) == expected
//        P4_Power.power2(base, exponent) == expected
        P4_Power.power3(base, exponent) == expected

        where:
        [base,  exponent, expected] << [
                [2, 0, 1],
                [2, 2, Math.pow(2, 2)],
                [2.5, 2, Math.pow(2.5, 2)]
        ]

    }

}
