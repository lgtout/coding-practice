package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import spock.lang.Specification
import spock.lang.Unroll

class ComputeLCAInBinaryTreeSpec extends Specification {

    @Unroll
    'computes LCA of two nodes of a binary tree'(
            BinaryTreeNode<Character> root,
            BinaryTreeNode<Character> firstNode,
            BinaryTreeNode<Character> secondNode,
            BinaryTreeNode<Character> expected) {

        expect:
        ComputeLCAInBinaryTree.computeLCA(
                root, firstNode, secondNode) == expected

        where:
        [root, firstNode, secondNode, expected] << [
                // Empty tree or null descendants cases
                [[], null, null, null],
                [[[null, null, 'A']], null, null, null],
                [[[null, null, 'A']], 0, null, null],
                [[[null, null, 'A']], null, 0, null],
                // Other cases
                [[[null, null, 'A']], 0, 0, 0], // 1
                [[[null, null, 'A'],[null, null, 'B']], 0, 1, null], // 2
                [[[null, 1, 'A'], [null, null, 'B']], 0, 1, 0], // 3
                [[[1, null, 'A'], [null, null, 'B']], 1, 0, 0], // 4
                // 4 - reversed order descendants provided
                [[[1, null, 'A'], [null, null, 'B']], 0, 1, 0],
                [[[1, null, 'A'], [null, null, 'B'], [null, null, 'C']], 1, 2, null], // 5
                [[[1, 2, 'A'], [null, null, 'B'], [null, null, 'C']], 1, 2, 0], // 6
                [[[1, null, 'A'], [null, null, 'B']], 1, 1, 1], // 7
                [[[1, 2, 'A'], [null, null, 'B'], [null, null, 'C'],
                  [null, null, 'D'], [null, null, 'E']], 3, 4, null], // 8
                [[[1, null, 'A'], [2, null, 'B'], [null, null, 'C']], 1, 2, 1], // 9
                [[[null, 1, 'A'], [null, 2, 'B'],
                  [null, null, 'C']], 2, 1, 1], // 10
                [[[1, 2, 'A'], [null, null, 'B'], [null, null, 'C'],
                  [null, null, 'D']], 1, 3, null], // 11
                [[[1, 2, 'A'], [null, null, 'B'], [null, 3, 'C'],
                  [null, null, 'D']], 1, 3, 0], // 12
                [[[null, null, 'A'], [null, null, 'B'], [3, null, 'C'],
                  [null, null, 'D']], 1, 3, null], // 13
                [[[1, 2, 'A'], [null, null, 'B'], [3, 4, 'C'],
                  [null, null, 'D'], [null, 5, 'E'],
                  [null, null, 'F']], 3, 5, 2], // 14
                [[[1, 2, 'A'], [3, 4, 'B'], [5, 6, 'C'],
                  [null, null, 'D'], [null, null, 'E'],
                  [null, null, 'F'], [null, null, 'G']], 6, 1, 0], // 15
                [[[1, 2, 'A'], [null, null, 'B'], [3, 4, 'C'],
                  [5, 6, 'D'], [7, 8, 'E'],
                  [null, null, 'F'], [null, null, 'G'],
                  [9, 10, 'H'], [null, null, 'I'],
                  [null, null, 'J'], [null, null, 'K']],
                 2, 7, 2], // 16
        ].collect { List<List<Character>> rawTree,
                    Integer _firstNodeIndex,
                    Integer _secondNodeIndex,
                    Integer _expected ->
            List<BinaryTreeNode<Character>> nodeTree = []
            BinaryTreeNode.buildBinaryTrees(rawTree, nodeTree)
            [nodeTree.isEmpty() ? null : nodeTree[0],
             _firstNodeIndex == null ? null : nodeTree[_firstNodeIndex],
             _secondNodeIndex == null ? null : nodeTree[_secondNodeIndex],
             _expected == null ? null : nodeTree[_expected]]
        }
    }

}
