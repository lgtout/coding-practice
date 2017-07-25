package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class Print1ToNSpec extends Specification {

    @Unroll('#n #expected')
    def "print 1 to n"(int n, List<Integer> expected) {

        expect:
        P2_Print1ToN.print1ToN(n)

        where:
        [n, expected] << [
                [1, [1]],
                [2, [1,2]],
                [3, [1,2,3]]
        ]

    }
}
