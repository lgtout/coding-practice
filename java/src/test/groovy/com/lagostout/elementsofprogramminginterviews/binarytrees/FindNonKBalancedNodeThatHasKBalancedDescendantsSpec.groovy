package com.lagostout.elementsofprogramminginterviews.binarytrees

import spock.lang.Specification
import spock.lang.Unroll

class FindNonKBalancedNodeThatHasKBalancedDescendantsSpec extends Specification {

    @Unroll
    'finds non-k-balanced node that has k-balanced descendants'(
            int k, Map<Integer, List<Integer>> tree, Integer expected) {

        expect:
        def node = FindNonKBalancedNodeThatHasKBalancedDescendants.find(tree, k)
        node == expected

        where:
        [k, tree, expected] << [
            [0, [0:[]], null],
            [1, [0:[]], null],
            [0, [0:[1,2]], null],
            [1, [0:[1,2]], null],
            [0, [0:[1,null]], 0],
            [0, [0:[null,1]], 0],
            [1, [0:[null,1]], null],
            [1, [0:[1,null]], null],
            [0, [0:[1,2], 1:[3, null]], 1],
            [1, [0:[1,2], 1:[3, null]], null],
            [0, [0:[1,2], 1:[3,4], 2:[5,6]], null],
            [1, [0:[1,2], 1:[3,4], 2:[5,6]], null],
            [1, [0:[1,2], 1:[3,4]], 0],
            [1, [0:[1,2], 1:[3,4]], 0],
            [1, [0:[1,2], 1:[3,4], 3:[5,6], 4:[7,null]], 0],
            [1, [0:[1,2], 1:[3,4], 3:[5,6], 4:[7,null], 5:[8,9]], 3], // 2
            [2, [0:[1,2], 1:[3,4], 3:[5,6], 4:[7,null], 5:[8,9]], 1], // 2
            [3, [0:[1,2], 1:[3,4], 3:[5,6], 4:[7,null], 5:[8,9]], 0], // 2
            [3, [0:[1,2], 1:[3,4], 3:[5,6], 4:[7,null], 5:[8,9], 6:[10,null]], 1], // 3
            [4, [0:[1,2], 1:[3,4], 3:[5,6], 4:[7,null], 5:[8,9], 6:[10,null]], 0], // 3
            [5, [0:[1,2], 1:[3,4], 3:[5,6], 4:[7,null], 5:[8,9], 6:[10,null]], 0], // 3
            [2, [0:[1,2], 1:[3,null], 3:[4,5], 4:[6,7], 5:[8,null]], 1], // 4
            [2, [0:[1,2], 2:[3,null], 3:[4,5], 5:[6,7]], 2], // 5
            [3, [0:[1,2], 2:[3,null], 3:[4,5], 5:[6,7]], 2], // 5
            [5, [0:[1,2], 2:[3,null], 3:[4,5], 5:[6,7]], null], // 5
            [5, [0:[null,2], 2:[3,null], 3:[4,5], 5:[6,7]], 0], // 6
            [2, [0:[1,2], 1:[3,4], 4:[5,6], 5:[7,8], 8:[null,9],
                 2:[10,11], 11:[12,13], 13:[null,14]], 4], // 7
            [2, [0:[1,2], 1:[3,4], 4:[5,6], 5:[null,7], 7:[8,9],
                 2:[10,11], 11:[12,13], 13:[null,14]], 5],
            [4, [0:[1,5], 1:[null,2], 2:[null,3], 3:[null,4],
                 5:[12,6], 6:[7,13], 7:[8,null], 8:[9,null],
                 9:[null,10], 10:[11,null]], 5] // 8
        ]
    }

}
