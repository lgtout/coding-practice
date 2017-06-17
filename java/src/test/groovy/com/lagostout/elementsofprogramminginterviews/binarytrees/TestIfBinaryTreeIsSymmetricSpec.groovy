package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import spock.lang.Specification
import spock.lang.Unroll

class TestIfBinaryTreeIsSymmetricSpec extends Specification {

    @Unroll
    'Tests if a binary tree is symmetric'(
            BinaryTreeNode tree, boolean expected) {

        expect:
        TestIfBinaryTreeIsSymmetric.isSymmetric(tree) == expected

        where:
        [tree, expected] << [
                [[[null, null, 'A']], true], // 1
                [[[1, null, 'A'], [null, null, 'B']], false], // 2
                [[[null, 1, 'A'], [null, null, 'B']], false], // 3
                [[[null, 1, 'A'], [null, null, 'A']], false], // 4
                [[[1, 2, 'A'], [null, null, 'B'], [null, null, 'B']], true], // 5
                [[[1, 2, 'A'], [null, null, 'B'], [null, null, 'C']], false], // 6
                [[[1, 2, 'A'], [null, null, 'B'], [null, null, 'A']], false], // 9
                [[[1, 2, 'A'], [null, null, 'A'], [null, null, 'A']], true], // 7
                [[[1, 2, 'A'], [null, 3, 'B'], [null, null, 'B'],
                  [null, null, 'C']], false], // 8
                [[[1, 2, 'A'], [null, null, 'B'], [null, null, 'A']], false], // 9
                [[[1, 2, 'A'], [3, null, 'B'], [4, null, 'B'], [null, null, 'C'],
                  [null, null, 'C']], false], // 10
                [[[1, 2, 'A'], [3, null, 'B'], [null, 4, 'B'], [null, null, 'C'],
                  [null, null, 'C']], true], // 11
                [[[1, 2, 'A'], [null, 3, 'B'], [4, null, 'B'], [null, null, 'C'],
                  [null, null, 'C']], true], // 12
                [[[1, 2, 'A'], [null, 3, 'B'], [4, 5, 'B'], [null, null, 'C'],
                  [null, null, 'C'], [null, null, 'D']], false], // 13
                [[[1, 2, 'A'], [3, 4, 'B'], [5, 6, 'B'], [null, null, 'C'],
                  [null, null, 'D'], [null, null, 'D'], [null, null, 'C']], true], // 14
                [[[1, 2, 'A'], [3, 4, 'B'], [5, 6, 'B'], [null, null, 'C'],
                  [null, null, 'C'], [null, null, 'C'], [null, null, 'C']], true], // 15
                [[[1, 2, 'A'], [null, 3, 'B'], [4, 5, 'B'], [null, null, 'C'],
                  [null, null, 'C'], [null, null, 'C']], false], // 16
        ].collect { List params ->
            def nodeTree = []
            BinaryTreeNode.buildBinaryTree(params[0] as List, nodeTree)
            [nodeTree[0], params[1]]
        }
    }

}
