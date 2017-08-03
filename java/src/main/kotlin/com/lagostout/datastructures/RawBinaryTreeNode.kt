package com.lagostout.datastructures

// This is only used in Kotlin, and should stay that way.
data class RawBinaryTreeNode<T: Comparable<T>>(val leftChildIndex: Int? = null,
                                               val rightChildIndex: Int? = null,
                                               val parentIndex: Int? = null,
                                               val value: T)
