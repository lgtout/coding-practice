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

    override fun toString(): String {
        return "BinaryTreeNode(value=$value, " +
                "parent=${Companion.stringify(parent)}, " +
                "left=${Companion.stringify(left)}, " +
                "right=${Companion.stringify(right)})"
    }

    companion object {

        fun <T : Comparable<T>> toRawBinaryTreeNodes(
                rawTree: List<List<Any>>): List<RawBinaryTreeNode<T>> {
            return rawTree.map {
                RawBinaryTreeNode(it[0] as Int?, it[1] as Int?,
                        (if (it.size == 4) it[2] else null) as Int?, it[3] as T)
            }
        }

        fun <T : Comparable<T>> buildBinaryTrees(
                rawTree: List<List<Any>>,
                tree: MutableMap<Int, BinaryTreeNode<T>>) {
            rawTree.forEachIndexed { index, _ ->
                tree[index] ?: run {
                    buildBinaryTree(index, toRawBinaryTreeNodes(rawTree), tree)
                }
            }
        }

        fun <T : Comparable<T>> buildBinaryTree(
                rawTree: List<RawBinaryTreeNode<T>>):
                Pair<BinaryTreeNode<T>?, List<BinaryTreeNode<T>>> {
            val nodes = mutableMapOf<Int, BinaryTreeNode<T>>()
            buildBinaryTree(0, rawTree, nodes)
            nodes.values.forEach { parent ->
                listOf(parent.left, parent.right).filterNotNull().forEach { childNode ->
                    childNode.parent ?: run { childNode.parent = parent }
                }
            }
            return Pair(nodes[0], nodes.toSortedMap().values.toList())
        }

        fun <T : Comparable<T>> buildBinaryTree(
                rawTree: List<List<Any>>, tree: MutableMap<Int, BinaryTreeNode<T>>) {
            buildBinaryTree(0, toRawBinaryTreeNodes(rawTree), tree)
        }

        fun <T : Comparable<T>> buildBinaryTree(
                rootNodeIndex: Int,
                rawTree: List<RawBinaryTreeNode<T>>,
                nodes: MutableMap<Int, BinaryTreeNode<T>>) {
            if (rawTree.isEmpty()) return
            val rawNode = rawTree[rootNodeIndex]
            val node = BinaryTreeNode(value = rawNode.value)
            rawNode.parentIndex?.let {
                // Configure parent
                val parentNode = nodes[it]
                node.parent = parentNode
            }
            nodes[rootNodeIndex] = node
            val leftChildIndex = rawNode.leftChildIndex
            if (leftChildIndex != null) {
                buildBinaryTree(leftChildIndex, rawTree, nodes)
                node.left = nodes[leftChildIndex]
            }
            val rightChildIndex = rawNode.rightChildIndex
            if (rightChildIndex != null) {
                buildBinaryTree(rightChildIndex, rawTree, nodes)
                node.right = nodes[rightChildIndex]
            }
        }

        private fun <T> stringify(node: BinaryTreeNode<T>?): String {
            return node?.run {
                "BinaryTreeNode(value=$value)"
            } ?: "null"
        }
    }
}
