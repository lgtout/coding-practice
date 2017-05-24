package com.lagostout.common

import spock.lang.Specification
import spock.lang.Unroll

class GraphsSpec extends Specification {

    @Unroll
    'detects whether a binary tree is complete'(List tree, boolean expected) {

        expect:
        Graphs.isComplete(tree) == expected

        where:
        [tree, expected] << [
                [[1, null, null], false],
                [[null, 1, null], false],
                [[1, 2, 3, 4, null, 5], false],
                [[1, 2, 3, 4, 5], true],
                [[1], true],
                [{ def t = []; t[2] = 1; t }(), false],
        ]
    }

    @Unroll
    'detects whether a binary tree satisfies the min heap property'(
            List tree, boolean expected) {

        expect:
        Graphs.satisfiesMinHeapProperty(tree)

        where:
        [tree, expected] << [
                []
        ]

    }

    @Unroll
    'detects whether a binary tree satisfies the max heap property'(
            List tree, boolean expected) {

        expect:
        Graphs.satisfiesMaxHeapProperty(tree)

        where:
        [tree, expected] << [
                []
        ]

    }

}
