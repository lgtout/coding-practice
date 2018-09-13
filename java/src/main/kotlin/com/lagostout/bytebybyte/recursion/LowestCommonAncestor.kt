package com.lagostout.bytebybyte.recursion

data class Node(val value: Int, var left: Node? = null, var right: Node? = null)

/* We'll assume the two descendant nodes are always in the tree.
From that, it follows that there'll always be at least one node in
the tree. We'll allow the possibility that the descendant nodes
may actually be two pointers to the same node instance. */

fun lowestCommonAncestor(root: Node, n1: Node, n2: Node): Node {
    fun compute(root: Node?, nodesToSearchFor: MutableList<Node>): Node? {
        if (root == null || nodesToSearchFor.isEmpty()) return null
        val countOfNodesToSearchFor = nodesToSearchFor.size
        nodesToSearchFor.removeAll(listOf(root))
        val commonAncestorByGoingLeftOrRight =
                listOfNotNull(compute(root.left, nodesToSearchFor),
                    compute(root.right, nodesToSearchFor)).firstOrNull()
        return commonAncestorByGoingLeftOrRight ?: (if (countOfNodesToSearchFor == 2
                && nodesToSearchFor.isEmpty()) root else null)
    }
    return compute(root, mutableListOf(n1, n2))!!
}