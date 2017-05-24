package com.lagostout.elementsofprogramminginterviews.binarytrees
import com.lagostout.common.BinaryTreeNode

/**
 * Problem 10.3 p
 */
class ComputeLCAInBinaryTree {

    static <T> BinaryTreeNode<T> computeLCA(BinaryTreeNode<T> root,
                                            BinaryTreeNode<T> firstNode,
                                            BinaryTreeNode<T> secondNode) {
        def lca = null
        if (root == null || firstNode == null || secondNode == null)
            return lca
        Deque<Frame<T>> frames = [] as ArrayDeque
        def descendants = [firstNode, secondNode] as Set
        frames.push([nextStep:1, node:root] as Frame)
        while (!frames.isEmpty()) {
            def frame = frames.peek()
            def node = frame.node
            def descendantMatches = frame.descendantMatches
            switch (frame.nextStep) {
                case 1:
                    descendantMatches.addAll(
                            descendants.findAll { it == node })
                    if (descendantMatches.size() == descendants.size()) {
                        lca = node
                        break
                    }
                    frame.nextStep = 2
                    frame.resultFrame = []
                    if (frame.node.left) {
                        def nextFrame =
                               [nextStep:1, node: frame.node.left] as Frame
                        frame.resultFrame = nextFrame
                        frames.push(nextFrame)
                    }
                    break
                case 2:
                    descendantMatches.addAll(
                            frame.resultFrame.descendantMatches)
                    if (descendantMatches.size() == descendants.size()) {
                        lca = node
                        break
                    }
                    frame.nextStep = 3
                    frame.resultFrame = []
                    if (frame.node.right) {
                        def nextFrame =
                                [nextStep:1, node:frame.node.right] as Frame
                        frame.resultFrame = nextFrame
                        frames.push(nextFrame)
                    }
                    break
                case 3:
                    descendantMatches.addAll(
                            frame.resultFrame.descendantMatches)
                    if (descendantMatches.size() == descendants.size()) {
                        lca = node
                        break
                    }
                    frames.pop()

            }
            if (lca != null) break
        }
        lca
    }

    static class Frame<T> {
        int nextStep
        BinaryTreeNode<T> node
        Set<BinaryTreeNode<T>> descendantMatches = []
        Frame<T> resultFrame
    }

}
