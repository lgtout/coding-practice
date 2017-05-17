package com.lagostout.practicingrecursion.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class ConvertNBSpec extends Specification {
    @Unroll('#n #b #expected')
    def 'convert'(int n, int b, String expected) {
        expect:
        P24_ConvertNB.convert(n, b) == expected
        where:
        [n, b, expected] << [
                [10, 2, '1010'],
                [16, 3, '121'],
                [20, 8, '24']
        ]
    }
}
