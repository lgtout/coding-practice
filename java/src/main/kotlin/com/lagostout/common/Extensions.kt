package com.lagostout.common

import org.apache.commons.math3.random.RandomDataGenerator

fun <T> List<T>.takeIfNotLast(): List<T> {
    return this.take(this.size - 1)
}

fun RandomDataGenerator.nextInt(range: IntRange): Int {
    return nextInt(range.start, range.endInclusive)
}

fun <T : Comparable<T>> BinaryTreeNode<T>.isLeftChild(): Boolean {
    return this == parent.left
}

fun <T : Comparable<T>> BinaryTreeNode<T>.isRightChild(): Boolean {
    return this == parent.right
}
