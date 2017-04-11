package com.lagostout.elementsofprogramminginterviews.binarytrees

class TestIfABinaryTreeIsHeightBalanced {

    Map<Integer, List<Integer>> tree

    boolean isBalanced(Map<Integer, List<Integer>> tree) {
        this.tree = tree
        def balance = getBalanceAndHeight(0).first
        return balance
    }

    static Tuple2<Boolean, Integer> LEAF = new Tuple2(true, 0)
    static Tuple2<Boolean, Integer> NULL_NODE = new Tuple2(true, -1)

    Tuple2<Boolean, Integer> getBalanceAndHeight(int i) {
        if (!tree.containsKey(i))
            return LEAF

        def subtreeRoots = tree.get(i)

        def leftTreeBalanceAndHeight = subtreeRoots.size() > 0 ?
                getBalanceAndHeight(subtreeRoots[0]) : NULL_NODE
        if (!leftTreeBalanceAndHeight.first)
            return leftTreeBalanceAndHeight

        def rightTreeBalanceAndHeight = subtreeRoots.size() > 1 ?
                getBalanceAndHeight(subtreeRoots[1]) : NULL_NODE
        if (!rightTreeBalanceAndHeight.first)
            return rightTreeBalanceAndHeight

        def isBalanced = (leftTreeBalanceAndHeight.first &&
                rightTreeBalanceAndHeight.first) &&
                (Math.abs(leftTreeBalanceAndHeight.second -
                        rightTreeBalanceAndHeight.second) < 2)
        def height = Math.max(
                leftTreeBalanceAndHeight.second,
                rightTreeBalanceAndHeight.second) + 1
        return [isBalanced, height]
    }

}
