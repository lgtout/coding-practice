package com.lagostout.practicingrecursion.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class NContainsKSpec extends Specification {

    @Unroll('#n #k')
    def 'n contains k'(int n, int k, boolean expected) {

        expect:
        P8_NContainsK.nContainsK(n, k) == expected

        where:
        [n, k, expected] << [
                [1234, 4, true],
                [1234, 5, false],
                [0000, 0, true],
                [0000, 1, false],
        ]

    }

}
