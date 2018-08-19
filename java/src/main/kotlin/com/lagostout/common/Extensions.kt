package com.lagostout.common

import com.google.common.collect.BoundType
import com.google.common.collect.Range
import com.lagostout.datastructures.BinaryTreeNode
import org.apache.commons.math3.random.RandomDataGenerator
import org.javatuples.Quartet

// Range

val Range<Int>.length: Int
    get() = upperEndpoint() - lowerEndpoint() +
            if (lowerBoundType() == BoundType.CLOSED)
                if (upperBoundType() == BoundType.CLOSED) 1
                else 0
            else
                if (upperBoundType() == BoundType.CLOSED) 0
                else -1

// Iterable

fun <T> Iterable<T>.takeFrom(startIndexInclusive: Int) = this.toList().takeFrom(startIndexInclusive).asIterable()
fun <T> Iterable<T>.subtractFirstOfEachFrom(iterable: Iterable<T>): Iterable<T> =
        fold(iterable) { acc, curr ->
            acc.minus(curr)
        }

// List

fun <T> List<T>.takeExceptLast() = this.take(this.size - 1)
fun <T> List<T>.takeFrom(startIndexInclusive: Int) =
        if (startIndexInclusive >= size) emptyList()
        else this.takeLast(this.size - startIndexInclusive)
fun <T> List<T>.takeFromOrNull(startIndexInclusive: Int) =
        if (startIndexInclusive >= size) null
        else this.takeLast(this.size - startIndexInclusive)
fun <T> List<T>.second() = get(1)
fun <T> List<T>.third() = get(1)
fun <T> List<T>.secondOrNull() = if (count() >= 1) get(1) else null
fun <T> List<T>.thirdOrNull() = if (count() >= 2) get(2) else null
fun <T> List<T>.offsetFromLast(offset: Int): T = get(lastIndex - offset)
fun List<Int>.productOrNull(): Int? =
        if (isEmpty()) null else fold(1) { acc, curr -> acc * curr }
fun List<Int>.product(): Int = fold(1) { acc, curr -> acc * curr }
fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val temp = get(index1)
    set(index1, get(index2))
    set(index2, temp)
}

// Map

fun <K, V> Map<K, V>.mergeReduce(other: Map<K, V>, reduce: (V, V) -> V = { a, b -> b }): Map<K, V> {
    val result = LinkedHashMap<K, V>(this.size + other.size)
    result.putAll(this)
    for ((key, value) in other) {
        result[key] = result[key]?.let { reduce(value, it) } ?: value
    }
    return result
}

fun Map<Int, Int>.printValuesAsBinaryStrings() = {
    forEach { t: Int, u: Int ->
        println("${t.toBinaryString()}: ${u.toBinaryString()}")
    }
}

// RandomDataGenerator

fun RandomDataGenerator.nextInt(range: Pair<Int, Int>): Int =
        nextInt(range.first, range.second)

fun RandomDataGenerator.nextInt(range: IntRange): Int =
        nextInt(range.start, range.endInclusive)

fun RandomDataGenerator.nextInt(range: CharRange): Char =
        nextInt(range.start.toInt(), range.endInclusive.toInt()).toChar()

fun RandomDataGenerator.nextEvenInt(range: IntRange): Int =
    range.filter { it % 2 == 0 }.let {
        it[nextInt(0, it.lastIndex)]
    }

fun RandomDataGenerator.nextBoolean(): Boolean = nextInt(0, 1) == 1

fun RandomDataGenerator.nextBoolean(trueFrequency: Float): Boolean {
    return nextInt(0, 100) in (0..(trueFrequency * 100).toInt())
}

fun RandomDataGenerator.nextDouble(range: Pair<Double, Double>): Double {
    return (randomGenerator.nextDouble() * range.second - range.first) +
            range.first
}

// Comparable

val <T : Comparable<T>> BinaryTreeNode<T>.isLeftChild: Boolean
    get() = this === parent?.left

val <T : Comparable<T>> BinaryTreeNode<T>.isRightChild: Boolean
    get() = this === parent?.right

val <T : Comparable<T>> BinaryTreeNode<T>.isRoot: Boolean
    get() = parent == null

val <T : Comparable<T>> BinaryTreeNode<T>.rightAncestor: BinaryTreeNode<T>?
    get() = if (isLeftChild) parent else null

val <T : Comparable<T>> BinaryTreeNode<T>.leftAncestor: BinaryTreeNode<T>?
    get() = if (isRightChild) parent else null

// Long

fun Long.toBinaryString(): String = java.lang.Long.toBinaryString(this)

// Int

fun Int.toBinaryString(): String = java.lang.Integer.toBinaryString(this)

fun Int.positionOfMostSignificantBit(): Int {
    var i = 0
    var number = this
    while (number != 0) {
        ++i
        number = number ushr 1
    }
    return i
}

val Int.isOdd: Boolean
    get() = this % 2 == 1

val Int.isEven: Boolean
    get() = !isOdd

// Quartet

// This is interesting... But impractical: Required to import all 4
// separately whenever I want to destructure Quartet.
operator fun <A, B, C, D> Quartet<A, B, C, D>.component1(): A = this.value0
operator fun <A, B, C, D> Quartet<A, B, C, D>.component2(): B = this.value1
operator fun <A, B, C, D> Quartet<A, B, C, D>.component3(): C = this.value2
operator fun <A, B, C, D> Quartet<A, B, C, D>.component4(): D = this.value3

// Pair

val Pair<Int, Int>.right
    get() = copy(second = second + 1)

val Pair<Int, Int>.left
    get() = copy(second = second - 1)

val Pair<Int, Int>.up
    get() = copy(first = first - 1)

val Pair<Int, Int>.down
    get() = copy(first = first + 1)

val Pair<Int, Int>.count: Int
    get() = second - first + 1

// String

fun String.removeChar(index: Int): String = removeRange(index, index + 1)