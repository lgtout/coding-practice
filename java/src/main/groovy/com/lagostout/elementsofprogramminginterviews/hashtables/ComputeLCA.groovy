package com.lagostout.elementsofprogramminginterviews.hashtables

import com.lagostout.common.BinaryTreeNode

/**
 * Problem 13.4 p219
 */
class ComputeLCA {

    static BinaryTreeNode findLCA(BinaryTreeNode node1, BinaryTreeNode node2) {
        def visitedNodes = [] as Set
        def lowestCommonAncestor
        while (true) {
            if (node1) {
                if (visitedNodes.contains(node1)) {
                    lowestCommonAncestor = node1
                    break
                }
                visitedNodes.add(node1)
                node1 = node1.parent
            }
            if (node2) {
                if (visitedNodes.contains(node2)) {
                    lowestCommonAncestor = node2
                    break
                }
                visitedNodes.add(node2)
                node2 = node2.parent
            }
        }
        lowestCommonAncestor
    }

}
