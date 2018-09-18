package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.common.MultilineShortPrefixRecursiveToStringStyle
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ReflectionToStringBuilder

data class LinkedListNode<T>(
        var data: T? = null,
        var next: LinkedListNode<T>? = null,
        var previous: LinkedListNode<T>? = null) {

    private val id = nextId

    val hasNext: Boolean
        get() = next != null

    val hasPrevious: Boolean
        get() = previous != null

    override fun toString(): String {
        return ReflectionToStringBuilder(this,
            MultilineShortPrefixRecursiveToStringStyle())
                .toString()
    }

    override fun hashCode(): Int =
            HashCodeBuilder().append(id).toHashCode()

    override fun equals(other: Any?): Boolean = when (other) {
        null -> false
        !is LinkedListNode<*> -> false
        else -> other.id == id
    }

    companion object {

        private var _id:Int = 0

        val nextId: Int
            get() = ++_id

        fun <T> from(list: List<T>): LinkedListNode<T> {
            val nodes = list.map {
                LinkedListNode(it)
            }
            nodes.windowed(2).forEach { (left, right) ->
                left.next = right
            }
            return nodes.first()
        }

    }

}

fun <T> LinkedListNode<T>.advance(distance: Int): LinkedListNode<T> {
    var count = 0
    var node = this
    while (count < distance) {
        node.next?.let {
            node = it
            ++count
        } ?: break
    }
    return node
}

fun <T> LinkedListNode<T>.count(): Int {
    var count = 1
    var node: LinkedListNode<T> = this
    while (node.hasNext) {
        node = node.next!!
        count += 1
    }
    return count
}

val <T> LinkedListNode<T>.tail: LinkedListNode<T>
    get() {
        var lastNode = this
        while (true) {
            lastNode.next?.let {
                lastNode = it
            } ?: break
        }
        return lastNode
    }

@Suppress("NAME_SHADOWING")
fun <T> LinkedListNode<T>.toList(): List<LinkedListNode<T>> {
    var node: LinkedListNode<T>? = this
    return mutableListOf<LinkedListNode<T>>().apply {
        val explored = mutableSetOf(node)
        while (true) {
            node = node?.let {
                add(it)
                it.next?.let {
                    if (it in explored) null
                    else it.also { explored.add(it) }
                }
            } ?: break
        }
    }
}

fun <T> LinkedListNode<T>.values(): List<T?> {
    return toList().map { it.data }
}
