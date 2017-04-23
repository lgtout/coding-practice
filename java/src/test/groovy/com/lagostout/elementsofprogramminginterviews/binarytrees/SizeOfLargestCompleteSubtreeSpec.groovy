package com.lagostout.elementsofprogramminginterviews.binarytrees

import spock.lang.Specification
import spock.lang.Unroll

class SizeOfLargestCompleteSubtreeSpec extends Specification {

    @Unroll("tree: #tree expected: #expected")
    'finds size of largest complete subtree'(
            Map<Integer, List<Integer>> tree, Integer expected) {

        expect:
        new SizeOfLargestCompleteSubtree().getSize(tree) == expected

        where:
        [tree, expected] << [
                [[0:[null,null]], 1],
                [[0:[1,null]], 2],
                [[0:[null,1]], 1],
                [[0:[1,2]], 3],
                [[0:[null,1], 1:[null,2]], 1],
                [[0:[1,null], 1:[2,null]], 2],
                [[0:[1,null], 1:[null,2]], 1],
                [[0:[1,2], 2:[null,3]], 1],
                [[0:[1,2], 2:[3,null]], 2],
                [[0:[1,2], 2:[3,4]], 3],
                [[0:[1,2], 2:[3,4], 3:[5,null]], 4],
                [[0:[1,2], 2:[3,4], 3:[5,6]], 5],
                [[0:[1,2], 2:[3,4], 3:[5,6], 4:[7,null]], 6],
                [[0:[1,2], 2:[3,4], 3:[5,6], 4:[7,8]], 7],
                [[0:[1,null], 1:[2,3]], 3],
                [[0:[1,null], 1:[2,3], 2:[4,null]], 4],
                [[0:[1,null], 1:[2,3], 2:[4,5]], 5],
                [[0:[1,null], 1:[2,3], 2:[4,5], 3:[null,6]], 3],
                [[0:[1,null], 1:[2,3], 2:[4,5], 3:[6,7]], 7],
                [[0:[1,2], 1:[3,4], 2:[5,6], 3:[7,null], 5:[8,9]], 5],
                [[0:[1,2], 1:[3,4], 2:[5,6], 3:[7,8], 4:[null,9], 5:[10,11],
                  6:[12,null]], 6],
                [[0:[1,2], 1:[3,4], 2:[5,6], 3:[7,8], 4:[9,null], 6:[10,11],
                  10:[12,13], 11:[14,15]], 7],
                [[0:[1,2], 1:[3,4], 2:[5,6], 3:[7,8], 4:[9,10], 6:[11,12],
                  11:[13,14], 12:[15,16]], 7],
                [[0:[1,2], 2:[3,4], 3:[5,6], 4:[7,8], 5:[9,10], 7:[11,12],
                  8:[13,null]], 6]
        ]
    }

}
