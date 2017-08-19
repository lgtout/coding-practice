package com.lagostout.common

import com.google.common.collect.Range
import org.apache.commons.math3.random.RandomDataGenerator
import com.lagostout.datastructures.BinaryTreeNode

val Range<Int>.length: Int
    get() = upperEndpoint() - lowerEndpoint()

fun <T> List<T>.takeIfNotLast() = this.take(this.size - 1)

fun <T> List<T>.takeFrom(indexInclusive: Int) = this.takeLast(this.size - indexInclusive)

fun <T> List<T>.offsetFromLast(offset: Int): T = get(lastIndex - offset)

fun <K, V> Map<K, V>.mergeReduce(other: Map<K, V>, reduce: (V, V) -> V = { a, b -> b }): Map<K, V> {
    val result = LinkedHashMap<K, V>(this.size + other.size)
    result.putAll(this)
    for ((key, value) in other) {
        result[key] = result[key]?.let { reduce(value, it) } ?: value
    }
    return result
}

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
