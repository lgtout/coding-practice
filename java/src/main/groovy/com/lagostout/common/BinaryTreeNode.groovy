package com.lagostout.common

import org.apache.commons.lang3.builder.ReflectionToStringBuilder
import org.apache.commons.lang3.tuple.Pair

class BinaryTreeNode<T extends Comparable<T>> {

    BinaryTreeNode<T> parent
    BinaryTreeNode<T> left
    BinaryTreeNode<T> right
    T value

    BinaryTreeNode(T value) {
        this.value = value
    }

    List<BinaryTreeNode> children() {
        [left, right]
    }

    boolean isNull() {
        this.value == null
    }

    // TODO Write tests
    /**
     * Pre-order left-to-right DFS: O(n)
     * @param value
     * @return
     */
    BinaryTreeNode<T> find(T value) {
        if (value == null) return null
        if (this.value == value) return this
        BinaryTreeNode<T> node = null
        if (left != null) {
            node = left.find(value)
        }
        if (node == null && right != null) {
            node = right.find(value)
        }
        node
    }

    @Override
    String toString() {
        def style = new MultilineShortPrefixRecursiveToStringStyle()
        new ReflectionToStringBuilder(this, style)
    }

    static BinaryTreeNode<T> createNullNode() {
        new BinaryTreeNode(null)
    }

    @Override
    boolean equals(Object obj) {
        if (obj == null) return false
        if (obj.class != BinaryTreeNode) return false
        obj = (BinaryTreeNode) obj
        if (obj.value != value) return false
        true
    }

    @Override
    int hashCode() {
        return super.hashCode()
    }

    static <T extends Comparable<T>> void buildBinaryTrees(
            List<List<T>> rawTree,
            List<BinaryTreeNode<T>> tree) {
        rawTree.eachWithIndex { List<T> rawNode, int index ->
            if (!tree[index]) {
                buildBinaryTree(index, toRawBinaryTreeNodes(rawTree), tree)
            }
        }
    }

    static <T extends Comparable<T>> void buildBinaryTree(
            List<List> rawTree,
            List<BinaryTreeNode<T>> tree) {
        buildBinaryTree(0, toRawBinaryTreeNodes(rawTree), tree)
    }

    static <T extends Comparable<T>> Pair<BinaryTreeNode<T>,
            List<BinaryTreeNode<T>>> buildBinaryTree(
            List<RawBinaryTreeNode<T>> rawTree) {
        def nodes = [] as List<BinaryTreeNode>
        buildBinaryTree(0, rawTree, nodes)
        return Pair.of(nodes.isEmpty() ? null : nodes[0], nodes)
    }

    private static <T extends Comparable> List<
            RawBinaryTreeNode<T>> toRawBinaryTreeNodes(
            List<List> rawTree) {
        rawTree.collect {
            new RawBinaryTreeNode<T>(
                    it[0] as Integer,
                    it[1] as Integer,
                    (it.size() == 4 ? it[2] : null) as Integer,
                    it[3] as T)
        }
    }

    static <T extends Comparable<T>> void buildBinaryTree(
            int rootNodeIndex,
            List<RawBinaryTreeNode<T>> rawTree,
            List<BinaryTreeNode<T>> tree) {
        if (rawTree.isEmpty()) return
        RawBinaryTreeNode<T> rawNode = rawTree.get(rootNodeIndex)
        BinaryTreeNode node
        node = new BinaryTreeNode(rawNode.value as Comparable<T>)
        if (rawNode.parentIndex != null) { // Configure parent
            BinaryTreeNode parentNode = tree[rawNode.parentIndex]
            node.parent = parentNode
        }
        tree[rootNodeIndex] = node
        def leftChildIndex = rawNode.leftChildIndex
        if (leftChildIndex != null) {
            buildBinaryTree(leftChildIndex, rawTree, tree)
            node.left = tree[leftChildIndex]
        }
        def rightChildIndex = rawNode.rightChildIndex
        if (rightChildIndex != null) {
            buildBinaryTree(rightChildIndex, rawTree, tree)
            node.right = tree[rightChildIndex]
        }
    }

}

