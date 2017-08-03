package com.lagostout.common

import org.apache.commons.math3.random.RandomDataGenerator
import com.lagostout.datastructures.BinaryTreeNode

fun <T> List<T>.takeIfNotLast() = this.take(this.size - 1)

fun <T> List<T>.takeFrom(index: Int) = this.takeLast(this.size - index)

fun RandomDataGenerator.nextInt(range: IntRange) =
        nextInt(range.start, range.endInclusive)

val <T : Comparable<T>> BinaryTreeNode<T>.isLeftChild: Boolean
    get() = this == parent?.left

val <T : Comparable<T>> BinaryTreeNode<T>.isRightChild: Boolean
    get() = this == parent?.right

val <T : Comparable<T>> BinaryTreeNode<T>.isRoot: Boolean
    get() = parent == null

val <T : Comparable<T>> BinaryTreeNode<T>.rightAncestor: BinaryTreeNode<T>?
    get() = if (isLeftChild) parent else null

val <T : Comparable<T>> BinaryTreeNode<T>.leftAncestor: BinaryTreeNode<T>?
    get() = if (isRightChild) parent else null

