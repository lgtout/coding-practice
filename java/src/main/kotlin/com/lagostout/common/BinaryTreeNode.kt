package com.lagostout.common

data class RawBinaryTreeNode<T: Comparable<T>>(val leftChildIndex: Int? = null,
                                               val rightChildIndex: Int? = null,
                                               val parentIndex: Int? = null,
                                               val value: T)
