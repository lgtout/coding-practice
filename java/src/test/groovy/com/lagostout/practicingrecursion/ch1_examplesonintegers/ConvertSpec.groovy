package com.lagostout.practicingrecursion.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class ConvertSpec extends Specification {

    @Unroll('#n #expected')
    def 'convert'(int n, String expected) {
        expect:
        P23_Convert.convert(n) == expected
        where:
        [n, expected] << [
                [0, '0'],
                [1, '1'],
                [2, '10'],
                [3, '11'],
                [13, '1101']
        ]
    }

}
