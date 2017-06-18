package com.lagostout.common

import org.apache.commons.lang3.builder.ReflectionToStringBuilder

class BinaryTreeNode<T> {

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

    static <T> void buildBinaryTrees(
            List<List<T>> rawTree,
            List<BinaryTreeNode<T>> tree) {
        rawTree.eachWithIndex { List<T> rawNode, int index ->
            if (!tree[index]) {
                buildBinaryTree(index, rawTree, tree)
            }
        }
    }

    static <T> void buildBinaryTree(
            List<List> rawTree,
            List<BinaryTreeNode<T>> tree) {
        buildBinaryTree(0, rawTree, tree)
    }

    static <T> void buildBinaryTree(
            int rootNodeIndex, List<List<?>> rawTree,
            List<BinaryTreeNode<T>> tree) {
        if (rawTree.isEmpty()) return
        List rawNode = rawTree.get(rootNodeIndex)
        BinaryTreeNode node
        if (rawNode.size() == 4) { // Configure parent
            def parentNodeIndex = rawNode[2] as Integer
            BinaryTreeNode parentNode = parentNodeIndex != null ?
                    tree[parentNodeIndex] : null
            node = new BinaryTreeNode(rawNode[3] as T)
            node.parent = parentNode
        } else { // No parent
            node = new BinaryTreeNode(rawNode[2] as T)
        }
        tree[rootNodeIndex] = node
        def leftIndex = rawNode[0] as Integer
        if (leftIndex != null) {
            buildBinaryTree(leftIndex, rawTree, tree)
            node.left = tree[leftIndex]
        }
        def rightIndex = rawNode[1] as Integer
        if (rightIndex != null) {
            buildBinaryTree(rightIndex, rawTree, tree)
            node.right = tree[rightIndex]
        }
    }

}

