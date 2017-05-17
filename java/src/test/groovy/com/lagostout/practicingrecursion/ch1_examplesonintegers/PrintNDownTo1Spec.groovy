package com.lagostout.practicingrecursion.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class PrintNDownTo1Spec extends Specification {

    @Unroll('#n #expected')
    def "print n down to 1"(int n, List<Integer> expected) {

        expect:
        P3_PrintNDownTo1.printNDownTo1(n) == expected

        where:
        [n, expected] << [
                [3,[3,2,1]],
                [1,[1]],
        ]

    }

}
