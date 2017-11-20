package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.common.MultilineShortPrefixRecursiveToStringStyle
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ReflectionToStringBuilder

data class ListNode<T>(var data: T? = null, var next: ListNode<T>? = null) {

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
        !is ListNode<*> -> false
        else -> other.id == id
    }

    companion object {
        private var _id:Int = 0
        val nextId: Int
            get() = ++_id
    }

}

@Suppress("NAME_SHADOWING")
fun <T> ListNode<T>.toList(): List<ListNode<T>> {
    var node: ListNode<T>? = this
    return mutableListOf<ListNode<T>>().apply {
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
