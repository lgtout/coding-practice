package com.lagostout.elementsofprogramminginterviews.binarytrees

import spock.lang.Specification
import spock.lang.Unroll

class TestIfABinaryTreeIsHeightBalancedSpec extends Specification {

    @Unroll
    'tests if binary tree is height balanced'(
            HashMap<Integer, List<Integer>> tree, boolean isBalanced) {

        expect:
        def balanceTester = new TestIfABinaryTreeIsHeightBalanced()
        balanceTester.isBalanced(tree) == isBalanced

        where:
        [tree, isBalanced] << [
                [[], true],
                [[0:[1]], true],
                [[0:[1], 1:[2]], false],
                [[0:[1,2]], true],
                [[0:[1,2], 1:[3]], true],
                [[0:[1,2], 1:[3,4], 3:[5]], false],
        ]

    }

}
