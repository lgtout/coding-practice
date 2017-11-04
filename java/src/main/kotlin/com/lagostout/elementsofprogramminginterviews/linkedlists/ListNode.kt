package com.lagostout.elementsofprogramminginterviews.linkedlists

import org.apache.commons.lang3.builder.*

data class ListNode<T>(var data: T? = null, var next: ListNode<T>? = null) {

    private val id = nextId

    val hasNext: Boolean
        get() = next != null

    override fun toString(): String {
        return ReflectionToStringBuilder(
                this, ToStringStyle.SHORT_PREFIX_STYLE).toString()
    }

    override fun hashCode(): Int {
        return HashCodeBuilder().append(id).toHashCode()
    }

    // TODO Is this right?
    override fun equals(other: Any?): Boolean {
        return EqualsBuilder.reflectionEquals(this, other)
    }

    companion object {
        private var _id:Int = 0
        val nextId: Int
            get() = ++_id
    }

}

fun <T> toLinkedList(values: List<T>): ListNode<T> {
    return values.map {
        ListNode(it)
    }.apply {
        forEachIndexed { index, listNode ->
            listNode.next = if (index < lastIndex) get(index + 1)
            else null
        }
    }.first()
}

@Suppress("NAME_SHADOWING")
fun <T> ListNode<T>.toList(): List<ListNode<T>> {
    var node: ListNode<T>? = this
    return mutableListOf<ListNode<T>>().apply {
        while (true) {
            node?.let {
                add(it)
                node = it.next
            } ?: break
        }
    }
}
