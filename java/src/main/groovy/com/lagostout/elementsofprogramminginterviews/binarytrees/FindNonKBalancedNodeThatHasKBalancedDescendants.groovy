package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * Problem 10.1.3
 *
 * A node is k-balanced if the difference in the number of nodes in its left
 * and right subtrees is no more than k.
 */
class FindNonKBalancedNodeThatHasKBalancedDescendants {

    static Integer find(Map<Integer, List<Integer>> tree, int k) {
        def result = find(0, tree, k)
        result.isKBalanced ? null : result.node
    }

    // Could use a stack instead of recursion, so we could exit immediately
    // after finding a node that meets our requirement instead of having
    // to unwind recursion first, in the recursive implementation below.
    static Result find(Integer node,
                       Map<Integer, List<Integer>> tree,
                       int k) {
//        println "node $node"
        if (node == null) return [node, 0, true]
        Integer leftChild, rightChild
        (leftChild, rightChild) = tree.get(node) ?: [null, null]
        def leftSubtreeResult = find(leftChild, tree, k)
//        println "leftSubtreeResult $leftSubtreeResult"
        if (!leftSubtreeResult.isKBalanced)
            return leftSubtreeResult
        def rightSubtreeResult = find(rightChild, tree, k)
//        println "rightSubtreeResult $rightSubtreeResult"
        if (!rightSubtreeResult.isKBalanced)
            return rightSubtreeResult
        def result = leftSubtreeResult.merge(node, rightSubtreeResult, k)
//        println "result $result"
        result
    }

    static class Result {
        Integer node
        int size = 0
        boolean isKBalanced = true
        Result(Integer node, int size, boolean isKBalanced) {
            this.node = node
            this.size = size
            this.isKBalanced = isKBalanced
        }
        Result merge(int node, Result otherResult, int k) {
            [node, size + otherResult.size + (node != null ? 1 : 0),
             (Math.abs(size - otherResult.size) <= k)]
        }

        @Override
        String toString() {
            Gson.newInstance().toJsonTree(this)
        }
    }

}
