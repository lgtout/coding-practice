package com.lagostout.datastructures

open class BinaryTreeNode<T>(var parent: BinaryTreeNode<T>? = null,
                             var left: BinaryTreeNode<T>? = null,
                             var right: BinaryTreeNode<T>? = null,
                             val value: T) {

    override fun hashCode(): Int {
        var result = parent?.hashCode() ?: 0
        result = 31 * result + (left?.hashCode() ?: 0)
        result = 31 * result + (right?.hashCode() ?: 0)
        result = 31 * result + (value?.hashCode() ?: 0)
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BinaryTreeNode<*>) return false

        if (parent != other.parent) return false
        if (left != other.left) return false
        if (right != other.right) return false
        if (value != other.value) return false

        return true
    }

    companion object {

        fun <T : Comparable<T>> buildBinaryTrees(
                rawTree: List<List<Any>>,
                tree: MutableList<BinaryTreeNode<T>>) {
            rawTree.forEachIndexed { index, rawNode ->
                tree.getOrNull(index) ?: run {
                    buildBinaryTree(index, toRawBinaryTreeNodes(rawTree), tree)
                }
            }
        }

        fun <T : Comparable<T>> buildBinaryTree(
                rawTree: List<RawBinaryTreeNode<T>>):
                Pair<BinaryTreeNode<T>?, List<BinaryTreeNode<T>>> {
            val nodes = mutableListOf<BinaryTreeNode<T>>()
            buildBinaryTree(0, rawTree, nodes)
            return Pair(nodes.getOrNull(0), nodes)
        }

        fun <T : Comparable<T>> toRawBinaryTreeNodes(
                rawTree: List<List<Any>>): List<RawBinaryTreeNode<T>> {
            return rawTree.map {
                RawBinaryTreeNode(it[0] as Int, it[1] as Int,
                        (if (it.size == 4) it[2] else null) as Int, it[3] as T)
            }
        }

        fun <T : Comparable<T>> buildBinaryTree(
                rawTree: List<List<Any>>, tree: MutableList<BinaryTreeNode<T>>) {
            buildBinaryTree(0, toRawBinaryTreeNodes<T>(rawTree), tree)
        }

        fun <T : Comparable<T>> buildBinaryTree(
                rootNodeIndex: Int,
                rawTree: List<RawBinaryTreeNode<T>>,
                tree: MutableList<BinaryTreeNode<T>>) {
            if (rawTree.isEmpty()) return
            val rawNode = rawTree[rootNodeIndex]
            val node = BinaryTreeNode(value = rawNode.value)
            rawNode.parentIndex?.let {
                // Configure parent
                val parentNode = tree[it]
                node.parent = parentNode
            }
            tree[rootNodeIndex] = node
            val leftChildIndex = rawNode.leftChildIndex
            if (leftChildIndex != null) {
                buildBinaryTree(leftChildIndex, rawTree, tree)
                node.left = tree[leftChildIndex]
            }
            val rightChildIndex = rawNode.rightChildIndex
            if (rightChildIndex != null) {
                buildBinaryTree(rightChildIndex, rawTree, tree)
                node.right = tree[rightChildIndex]
            }
        }
    }
}
