package com.lagostout.common

data class RawBinaryTreeNode<out T>(val leftChildIndex: Int? = null,
                                    val rightChildIndex: Int? = null,
                                    val parentIndex: Int? = null, val value: T)
