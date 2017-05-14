package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.elementsofprogramminginterviews.common.Node

import static com.lagostout.elementsofprogramminginterviews.common.Node.createNullNode

/**
 * Problem 10.2 p155
 */
class TestIfBinaryTreeIsSymmetric {

    static boolean isSymmetric(Node<Character> root) {
        def isSymmetric = true
        def queue = [] as ArrayDeque<Node>
        queue.add(root)
        def levelNodeCount = 1
        while (!queue.isEmpty()) {
            // Add next level to queue
            def nextLevelNodeCount = 0
            while (levelNodeCount > 0) {
                def node = queue.remove()
                if (!node.isNull()) {
                    def children = node.children()
                            .collect { it == null ? createNullNode() : it }
                    queue.addAll(children)
                    nextLevelNodeCount += children.size()
                }
                levelNodeCount--
            }
            levelNodeCount = nextLevelNodeCount
            // Verify symmetry
            int i = 0, j = levelNodeCount - 1
            while (i < j) {
                def leftNode = queue[i++]
                def rightNode = queue[j--]
                def nodesAreEqual = leftNode.value == rightNode.value
                if (!nodesAreEqual) {
                    isSymmetric = false
                    break
                }
            }
        }
        isSymmetric
    }

}
