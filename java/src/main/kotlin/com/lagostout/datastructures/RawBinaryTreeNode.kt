package com.lagostout.datastructures

// This is only used in Kotlin, and should stay that way.
data class RawBinaryTreeNode<T: Comparable<T>>(val leftChildIndex: Int? = null,
                                               val rightChildIndex: Int? = null,
                                               val parentIndex: Int? = null,
                                               val value: T) {
    companion object {
        fun <T : Comparable<T>> rbt(left: Int? = null, right: Int? = null,
                                    parent: Int? = null, value: T): RawBinaryTreeNode<T> =
                RawBinaryTreeNode(left, right, parent, value)

        fun <T : Comparable<T>> rbt(value: T, left: Int? = null, right: Int? = null,
                                    parent: Int? = null): RawBinaryTreeNode<T> =
                RawBinaryTreeNode(left, right, parent, value)
    }
}
