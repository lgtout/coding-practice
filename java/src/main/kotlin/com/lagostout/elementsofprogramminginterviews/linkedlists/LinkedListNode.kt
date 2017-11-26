package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.common.MultilineShortPrefixRecursiveToStringStyle
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ReflectionToStringBuilder

data class LinkedListNode<T>(var data: T? = null,
                             var next: LinkedListNode<T>? = null) {

    private val id = nextId

    val hasNext: Boolean
        get() = next != null

    override fun toString(): String {
        return ReflectionToStringBuilder(
                this, MultilineShortPrefixRecursiveToStringStyle()).toString()
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
    }

}

val <T> LinkedListNode<T>.last: LinkedListNode<T>
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
